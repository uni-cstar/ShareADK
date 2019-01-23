package halo.android.integration.wx.model;

/**
 * Created by Lucio on 17/5/12.
 */

public class WxUserInfo extends WxApiResult {

    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    public String openid;

    /**
     * 普通用户昵称
     */
    public String nickname;

    /**
     * 普通用户性别，1为男性，2为女性
     */
    public int sex;

    /**
     * 普通用户个人资料填写的省份
     */
    public String province;

    /**
     * 普通用户个人资料填写的城市
     */
    public String city;

    /**
     * 国家，如中国为CN
     */
    public String country;

    /**
     * "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    public String headimgurl;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    public String unionid;

    /**
     * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
     */
    public String[] privilege;

}
