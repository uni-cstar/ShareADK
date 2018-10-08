package halo.android.share.wx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import halo.android.share.wx.iml.IWxEntry;
import halo.android.share.wx.iml.WxLoginResponseListener;
import halo.android.share.wx.iml.WxPayResponseListener;
import halo.android.share.wx.iml.WxShareResponseListener;

/**
 * Created by Lucio on 17/5/12.
 */

class WxCallBackDelegate implements IWXAPIEventHandler, IWxEntry {

    Activity mActivity;
    IWXAPI api;
    String mAppId;
    static final String TAG = "WxCallBackDelegate";

    /**
     * 在WxEntryActivity的OnCreate进行实例化
     *
     * @param activity
     * @param appId
     */
    public WxCallBackDelegate(Activity activity, String appId) {
        mActivity = activity;
        mAppId = appId;
    }

    /**
     * 在WxEntryActivity的{@link Activity#onCreate(Bundle)}方法中调用
     */
    @Override
    public void onActivityCreate() {
        api = WXAPIFactory.createWXAPI(mActivity, mAppId);
        api.handleIntent(mActivity.getIntent(), this);
    }

    /**
     * 在WxEntryActivity的 {@link Activity#onNewIntent(Intent)}方法中调用
     *
     * @param intent
     */
    public void onNewIntent(Intent intent) {
        mActivity.setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d(TAG, "onResp:" + baseResp.errCode);
        try {

            int type = baseResp.getType();

            if (type == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
                //微信分享
                onSendResp(baseResp);
            } else if (type == ConstantsAPI.COMMAND_PAY_BY_WX) {
                //微信支付
                onPayResp(baseResp);
            } else if (type == ConstantsAPI.COMMAND_SENDAUTH) {
                //微信登陆
                onLoginResp(baseResp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onPayResp(BaseResp baseResp) {
        if (!(baseResp instanceof PayResp)) {
            throw new IllegalArgumentException("arguments error:" + baseResp.getClass().getName());
        }
        PayResp pay = (PayResp) baseResp;

        int errorCode = pay.errCode;
        String errorMsg = pay.errStr;
        if (errorCode == BaseResp.ErrCode.ERR_OK) {
            //支付成功
            CallBackHolder.getInstance().notifyPaySuccess(mActivity, pay);
        } else {
            CallBackHolder.getInstance().notifyPayError(mActivity, errorCode, errorMsg);
        }

    }

    private void onSendResp(BaseResp baseResp) {
        if (!(baseResp instanceof SendMessageToWX.Resp)) {
            throw new IllegalArgumentException("arguments error:" + baseResp.getClass().getName());
        }
        SendMessageToWX.Resp sendResp = (SendMessageToWX.Resp) baseResp;

        int errorCode = sendResp.errCode;
        String errorMsg = sendResp.errStr;
        if (errorCode == BaseResp.ErrCode.ERR_OK) {
            //分享成功
            CallBackHolder.getInstance().notifyShareSuccess(mActivity, sendResp);
        } else {
            CallBackHolder.getInstance().notifyShareError(mActivity, errorCode, errorMsg);
        }
    }

    private void onLoginResp(BaseResp baseResp) {
        if (!(baseResp instanceof SendAuth.Resp)) {
            throw new IllegalArgumentException("arguments error:" + baseResp.getClass().getName());
        }
        SendAuth.Resp authResp = (SendAuth.Resp) baseResp;

        int errorCode = authResp.errCode;
        String errorMsg = authResp.errStr;

        if (errorCode == BaseResp.ErrCode.ERR_OK) {
            //用户同意
//            String code = authResp.code;
//            String state = authResp.state;
            CallBackHolder.getInstance().notifyAuthSuccess(mActivity, authResp);
        } else {
            CallBackHolder.getInstance().notifyAuthError(mActivity, errorCode, errorMsg);
        }
    }


    /**
     * 回调管理
     */
    static class CallBackHolder {

        private List<WxLoginResponseListener> mLoginListeners;
        private List<WxShareResponseListener> mShareListeners;
        private List<WxPayResponseListener> mPayListeners;

        private CallBackHolder() {
        }

        private static class SingleTone {
            private static final CallBackHolder instance = new CallBackHolder();
        }

        static CallBackHolder getInstance() {
            return SingleTone.instance;
        }


        void addAuthListener(WxLoginResponseListener listener) {
            if (mLoginListeners == null) {
                mLoginListeners = new ArrayList<>();
            }
            if (mLoginListeners.contains(listener))
                return;
            mLoginListeners.add(listener);
        }

        void removeAuth(WxLoginResponseListener listener) {
            if (mLoginListeners == null)
                return;
            mLoginListeners.remove(listener);
        }

        void notifyAuthSuccess(Activity activity, SendAuth.Resp data) {
            for (WxLoginResponseListener item : mLoginListeners) {
                item.onWxLoginSuccess(activity, data);
            }
        }

        void notifyAuthError(Activity activity, int code, String msg) {
            for (WxLoginResponseListener item : mLoginListeners) {
                item.onWxCallBackError(activity, code, msg);
            }
        }


        void addShareListener(WxShareResponseListener listener) {
            if (mShareListeners == null) {
                mShareListeners = new ArrayList<>();
            }
            if (mShareListeners.contains(listener))
                return;
            mShareListeners.add(listener);
        }

        void removeShareListener(WxShareResponseListener listener) {
            if (mShareListeners == null)
                return;
            mShareListeners.remove(listener);
        }

        void notifyShareSuccess(Activity activity, SendMessageToWX.Resp data) {
            for (WxShareResponseListener item : mShareListeners) {
                item.onWxShareSuccess(activity, data);
            }
        }

        void notifyShareError(Activity activity, int code, String msg) {
            for (WxShareResponseListener item : mShareListeners) {
                item.onWxCallBackError(activity, code, msg);
            }
        }

        void addPayListener(WxPayResponseListener listener) {
            if (mPayListeners == null) {
                mPayListeners = new ArrayList<>();
            }
            if (mPayListeners.contains(listener))
                return;
            mPayListeners.add(listener);
        }

        void removePayListener(WxPayResponseListener listener) {
            if (mPayListeners == null)
                return;
            mPayListeners.remove(listener);
        }

        void notifyPaySuccess(Activity activity, PayResp data) {
            for (WxPayResponseListener item : mPayListeners) {
                item.onWxPaySuccess(activity, data);
            }
        }

        void notifyPayError(Activity activity, int code, String msg) {
            for (WxPayResponseListener item : mPayListeners) {
                item.onWxCallBackError(activity, code, msg);
            }
        }
    }
}
