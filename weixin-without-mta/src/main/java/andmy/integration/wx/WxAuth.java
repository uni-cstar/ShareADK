package andmy.integration.wx;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;

import andmy.integration.wx.model.WxAccessToken;
import andmy.integration.wx.model.WxUserInfo;
import andmy.integration.wx.iml.WxLoginResponseListener;

/**
 * Created by Lucio on 17/5/12.
 * 微信授权，登陆，获取用户信息
 */

public class WxAuth {

    public static String ScopeForUserInfo = "snsapi_userinfo";

    /**
     * 添加Auth监听
     * @param listener 参数
     */
    public static void addAuthListener(WxLoginResponseListener listener) {
        WxCallBackDelegate.CallBackHolder.getInstance().addAuthListener(listener);
    }

    /**
     * 移除Auth监听
     * @param listener
     */
    public static void removeAuthListener(WxLoginResponseListener listener) {
        WxCallBackDelegate.CallBackHolder.getInstance().removeAuth(listener);
    }

    /**
     * @param context
     * @param appId
     * @param scope 应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
     * @param state 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
     * @throws WxNotInstalledException 微信未安装
     */
    public static void login(Context context, String appId, String scope, String state) throws WxNotInstalledException {

        final SendAuth.Req req = new SendAuth.Req();
        req.scope = scope;
        req.state = state;
        Wx.senReq(context, appId, req);
    }


    /**
     * 获取微信AccessToken的url
     * api请求 Get方式 返回结果参考{@link WxAccessToken}
     * 请求是否成功 {@link WxAccessToken#isSuccess()}
     *
     * @param appId  应用唯一标识，在微信开放平台提交应用审核通过后获得
     * @param secret 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     * @param code   用户换取access_token的code，仅在ErrCode为0时有效
     * @return
     */
    public static String getAccessTokenUrl(String appId, String secret, String code) {
        return String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, secret, code);
    }

    /**
     * 刷新access_token有效期
     * 1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
     * 2. 若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
     * api请求 Get方式 返回结果参考{@link WxAccessToken}
     * 请求是否成功 {@link WxAccessToken#isSuccess()}
     *
     * @param appId        应用唯一标识
     * @param refreshToken 填写通过access_token获取到的refresh_token参数
     * @return
     */
    public static String getRefreshTokenUrl(String appId, String refreshToken) {
        return String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s", appId, refreshToken);
    }

    /**
     * 获取微信用户信息
     * api请求 Get方式 返回结果参考{@link WxUserInfo}
     * 请求是否成功 {@link WxUserInfo#isSuccess()}
     *
     * @param accessToken 调用凭证
     * @param openid      普通用户的标识，对当前开发者帐号唯一 {@link WxUserInfo}
     * @return
     */
    public static String getUserInfoUrl(String accessToken, String openid) {
        return String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", accessToken, openid);
    }

    /**
     * @param accessToken
     * @param openid
     * @param lang        语言，默认中文
     * @return
     */
    public static String getUserInfoUrl(String accessToken, String openid, String lang) {
        return String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s", accessToken, openid, lang);
    }
}
