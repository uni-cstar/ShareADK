package easy.share.wx;

import android.content.Context;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Lucio on 17/5/11.
 */

public class WxUtil {


    public static IWXAPI checkWxApp(Context context, String appId) throws WxNotInstalledException, WxNotSupportVersionException {
        IWXAPI api = WXAPIFactory.createWXAPI(context, null);
        api.registerApp(appId);
        if (!api.isWXAppInstalled()) {
            throw new WxNotInstalledException("Weixin not installed.");
        } else if (!api.isWXAppSupportAPI()) {
            throw new WxNotSupportVersionException("Not support version");
        }
        return api;
    }


    /**
     * 发送请求
     * @param context
     * @param appId
     * @param req
     * @return
     * @throws WxNotInstalledException
     * @throws WxNotSupportVersionException
     */
    public static boolean senReq(Context context, String appId, BaseReq req) throws WxNotInstalledException, WxNotSupportVersionException {
        IWXAPI api = WxUtil.checkWxApp(context, appId);
        return api.sendReq(req);
    }


}
