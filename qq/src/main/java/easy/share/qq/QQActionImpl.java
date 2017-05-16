package easy.share.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import easy.share.qq.impl.QQAction;
import easy.share.qq.impl.QQCallBackListener;
import easy.share.qq.impl.QQLoginListener;
import easy.share.qq.impl.QQShareListener;
import easy.share.qq.impl.QQUserInfoListener;
import easy.share.qq.model.QQApiResult;
import easy.share.qq.model.QQAuthInfo;
import easy.share.qq.model.QQUserInfo;

/**
 * Created by Lucio on 17/5/15.
 */

class QQActionImpl implements QQAction {

    Context mContext;
    Activity mActivity;
    Fragment mFragment;

    Tencent mTencent;

    QQLoginListener mLoginListener;
    QQUserInfoListener mUserInfoListener;
    QQShareListener mShareListener;

    private String mScope;

    QQActionImpl(Fragment fragment, String appId) {
        mContext = fragment.getContext();
        mTencent = QQ.buildTencent(mContext, appId);
        mFragment = fragment;
        init();
    }

    QQActionImpl(Activity activity, String appId) {
        mContext = activity;
        mTencent = QQ.buildTencent(mContext, appId);
        mActivity = activity;
        init();
    }

    private void init() {
        mScope = QQ.getDefaultScope();
        loadAuthInfoCache();
    }

    /**
     * 加载sp文件中的accesstoken缓存
     */
    private void loadAuthInfoCache() {
        QQAuthInfo authInfo = QQPref.getQQAuthInfo(mContext);
        if (authInfo == null)
            return;
        mTencent.setAccessToken(authInfo.access_token, authInfo.expires_in);
        mTencent.setOpenId(authInfo.openid);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mLoginCallBackListener);
        } else if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mShareCallBackListener);
        } else {
//            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }
    }

    public void setScope(String scope) {
        mScope = scope;
    }

    /**
     * 注销
     */
    public void logout() {
        mTencent.logout(mContext);
    }

    /**
     * 登陆
     */
    public void login(QQLoginListener listener) {
        if (!mTencent.isSessionValid()) {
            if (mActivity != null) {
                mTencent.login(mActivity, mScope, mLoginCallBackListener);
                mLoginListener = listener;
            } else if (mFragment != null) {
                mTencent.login(mFragment, mScope, mLoginCallBackListener);
                mLoginListener = listener;
            } else {
                listener.onQQApiFailed(new QQApiResult(-404, "参数错误", "Activity or Fragment 至少保证一个不能为空"));
            }
        } else {
            listener.onQQLoginSuccess(QQAuthInfo.fromQQToken(mTencent.getQQToken()));
        }
    }

    /**
     * 登陆，并获取用户信息
     *
     * @param listener
     */
    public void loginForUserInfo(QQUserInfoListener listener) {
        if (!mTencent.isSessionValid()) {
            if (mActivity != null) {
                mTencent.login(mActivity, mScope, mLoginCallBackListener);
                mUserInfoListener = listener;
            } else if (mFragment != null) {
                mTencent.login(mFragment, mScope, mLoginCallBackListener);
                mUserInfoListener = listener;
            } else {
                listener.onQQApiFailed(new QQApiResult(-404, "参数错误", "Activity or Fragment 至少保证一个不能为空"));
            }
        } else {
            getUserInfo(mContext, listener);
        }
    }

    /**
     * 获取用户信息
     *
     * @param context
     */
    private void getUserInfo(Context context, final QQUserInfoListener listener) {
        UserInfo info = new UserInfo(context, mTencent.getQQToken());
        info.getUserInfo(new QQCallBackListener() {
            @Override
            public void onComplete(Object o) {
                try {
                    JSONObject jObj = (JSONObject) o;
                    if (listener != null) {
                        listener.onQQUserInfoSuccess(QQUserInfo.fromJsonObject(jObj));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                if (listener != null) {
                    listener.onQQApiFailed(QQApiResult.fromUiError(uiError));
                }
            }

            @Override
            public void onCancel() {
                if (listener != null) {
                    listener.onQQApiCancel();
                }
            }
        });
    }

    /**
     * 登陆回调
     */
    private QQCallBackListener mLoginCallBackListener = new QQCallBackListener() {
        @Override
        public void onComplete(Object o) {
            try {
                JSONObject jObj = (JSONObject) o;
                QQAuthInfo authInfo = QQAuthInfo.fromJsonObject(jObj);
                mTencent.setOpenId(authInfo.openid);
                mTencent.setAccessToken(authInfo.access_token, authInfo.expires_in);
                QQPref.saveQQAuthInfo(mContext, authInfo);
                if (mLoginListener != null) {
                    mLoginListener.onQQLoginSuccess(authInfo);
                    mLoginListener = null;
                }

                if (mUserInfoListener != null) {
                    getUserInfo(mContext, mUserInfoListener);
                    mUserInfoListener = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                QQPref.clear(mContext);
                mTencent.setOpenId("");
                mTencent.setAccessToken("", "");
                onApiError(new QQApiResult(-404, "AccessToken parse failed ：" + o.toString(), e.getMessage()));
            }
        }

        private void onApiError(QQApiResult apiResult) {
            if (mLoginListener != null) {
                mLoginListener.onQQApiFailed(apiResult);
                mLoginListener = null;
            }

            if (mUserInfoListener != null) {
                mUserInfoListener.onQQApiFailed(apiResult);
                mUserInfoListener = null;
            }
        }

        @Override
        public void onError(UiError uiError) {
            onApiError(QQApiResult.fromUiError(uiError));
        }

        @Override
        public void onCancel() {
            if (mLoginListener != null) {
                mLoginListener.onQQApiCancel();
                mLoginListener = null;
            }

            if (mUserInfoListener != null) {
                mUserInfoListener.onQQApiCancel();
                mUserInfoListener = null;
            }
        }
    };


    /**
     * 分享到QQ空间
     *
     * @param params
     * @param listener
     */
    public void shareToQZone(Activity activity, Bundle params, QQShareListener listener) {
        mTencent.shareToQzone(activity, params, mShareCallBackListener);
        mShareListener = listener;
    }

    /**
     * 分享到QQ
     *
     * @param activity
     * @param params
     * @param listener
     */
    public void shareToQQ(Activity activity, Bundle params, QQShareListener listener) {
        mTencent.shareToQQ(activity, params, mShareCallBackListener);
        mShareListener = listener;
    }

    /**
     * 分享回调
     */
    private QQCallBackListener mShareCallBackListener = new QQCallBackListener() {
        @Override
        public void onComplete(Object o) {
            try {
                if (mShareListener != null) {
                    mShareListener.onQQShareSuccess();
                    mShareListener = null;
                }
            } finally {
                mShareListener = null;
            }
        }

        @Override
        public void onError(UiError uiError) {
            try {
                if (mShareListener != null) {
                    mShareListener.onQQApiFailed(QQApiResult.fromUiError(uiError));
                    mShareListener = null;
                }
            } finally {
                mShareListener = null;
            }
        }

        @Override
        public void onCancel() {
            try {
                if (mShareListener != null) {
                    mShareListener.onQQApiCancel();

                }
            } finally {
                mShareListener = null;
            }
        }
    };

}
