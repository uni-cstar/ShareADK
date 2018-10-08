package halo.android.share.wx.iml;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxLoginResponseListener extends WxResponseListener {

    /**
     *
     * @param activity WxCallBackDelegate中包含的activity，通常指包名wxapi目录下定义的微信所需的activity
     * @param resp
     */
    void onWxLoginSuccess(Activity activity, SendAuth.Resp resp);

}
