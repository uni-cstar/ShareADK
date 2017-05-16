package easy.share.wx;

import android.app.Activity;

import com.tencent.mm.opensdk.openapi.IWXAPI;

import easy.share.wx.iml.IWxEntry;

/**
 * Created by Lucio on 17/5/16.
 */

public class WeiXin {

    /**
     * 创建微信Entry的委托实现
     *
     * @param activity
     * @param appId
     * @return
     */
    public static IWxEntry createWxEntry(Activity activity, String appId) {
        return new WxCallBackDelegate(activity, appId);
    }

}
