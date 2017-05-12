package easy.share.wx.model;

/**
 * Created by Lucio on 17/5/12.
 */

public class WxAccessToken extends WxApiResult{
    /**
     * 接口调用凭证
     * access_token是调用授权关系接口的调用凭证，
     * 由于access_token有效期（目前为2个小时）较短，
     * 当access_token超时后，可以使用refresh_token进行刷新，
     * access_token刷新结果有两种：
     * 1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
     * 2. 若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
     */
    public String access_token;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    public String expires_in;

    /**
     * 用户刷新access_token
     */
    public String refresh_token;

    /**
     * 授权用户唯一标识
     */
    public String openid;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    public String scope;

    /**
     * 当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
     */
    public String unionid;
}
