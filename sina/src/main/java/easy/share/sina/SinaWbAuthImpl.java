package easy.share.sina;

import android.app.Activity;
import android.content.Intent;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by Lucio on 17/5/15.
 */

class SinaWbAuthImpl implements ISinaWbAuth, WbAuthListener {

    Activity mActivity;
    SsoHandler mSsoHandler;
    SinaWbAuthListener mCustomCallBack;

    public SinaWbAuthImpl(Activity activity) {
        mActivity = activity;
        mSsoHandler = new SsoHandler(mActivity);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mSsoHandler != null)
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }


    @Override
    public void getSinaWbAuth(SinaWbAuthListener listener) {
        mCustomCallBack = listener;
        mSsoHandler.authorize(this);
    }

    @Override
    public void requestOpenApi() {

    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        if (mCustomCallBack != null) {
            mCustomCallBack.onSuccess(oauth2AccessToken);
            mCustomCallBack = null;
        }
    }

    @Override
    public void cancel() {
        if (mCustomCallBack != null) {
            mCustomCallBack.cancel();
            mCustomCallBack = null;
        }
    }

    @Override
    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        if (mCustomCallBack != null) {
            mCustomCallBack.onFailure(wbConnectErrorMessage);
            mCustomCallBack = null;
        }
    }
}
