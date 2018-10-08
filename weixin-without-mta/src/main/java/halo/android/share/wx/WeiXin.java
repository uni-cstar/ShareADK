package halo.android.share.wx;

import android.app.Activity;
import android.content.Context;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import halo.android.share.wx.iml.IWxEntry;


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

    public static IWXAPI checkWxApp(Context context, String appId) throws WxNotInstalledException {
        IWXAPI api = WXAPIFactory.createWXAPI(context, null);
        api.registerApp(appId);
        if (!api.isWXAppInstalled()) {
            throw new WxNotInstalledException("WeiXin not installed.");
        }
        return api;
    }

    /**
     * 发送请求
     *
     * @param context
     * @param appId
     * @param req
     * @return
     * @throws WxNotInstalledException 微信未安装
     */
    public static boolean senReq(Context context, String appId, BaseReq req) throws WxNotInstalledException {
        IWXAPI api = checkWxApp(context, appId);
        return api.sendReq(req);
    }
}
