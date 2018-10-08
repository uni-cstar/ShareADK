package halo.android.share.wx.iml;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxShareResponseListener extends WxResponseListener {

    /**
     * @param activity WxCallBackDelegate中包含的activity，通常指包名wxapi目录下定义的微信所需的activity
     * @param resp
     */
    void onWxShareSuccess(Activity activity, SendMessageToWX.Resp resp);

}
