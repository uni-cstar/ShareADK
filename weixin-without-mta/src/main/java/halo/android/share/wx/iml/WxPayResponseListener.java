package halo.android.share.wx.iml;

import android.app.Activity;

import com.tencent.mm.opensdk.modelpay.PayResp;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxPayResponseListener extends WxResponseListener {

    /**
     *
     * @param activity WxCallBackDelegate中包含的activity，通常指包名wxapi目录下定义的微信所需的activity
     * @param resp
     */
    void onWxPaySuccess(Activity activity, PayResp resp);

}
