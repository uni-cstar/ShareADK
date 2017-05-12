package easy.share.wx;

import android.os.Bundle;
import android.text.TextUtils;

import com.tencent.mm.opensdk.modelpay.PayReq;

/**
 * Created by Lucio on 17/5/11.
 * 支付请求
 */
class WxPayReq extends PayReq {

    public WxPayReq() {
        super();
    }


    public WxPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) {
        this(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, (String) null);
    }

    /**
     * @param appId             微信开放平台审核通过的应用APPID
     * @param partnerId         微信支付分配的商户号
     * @param prepayId          预支付交易会话ID,微信返回的支付交易会话ID
     * @param packageValue      扩展字段，暂填写固定值Sign=WXPay
     * @param nonceStr          随机字符串，不长于32位。推荐随机数生成算法
     * @param timeStamp         时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     * @param sign              签名
     * @param callBackClassName 回调类名
     */
    public WxPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, String callBackClassName) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.packageValue = packageValue;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.sign = sign;

        //设置回调
        if (!TextUtils.isEmpty(callBackClassName)) {
            Bundle bundle = new Bundle();
            this.toBundle(bundle);
            bundle.putString("_wxapi_payoptions_callback_classname", callBackClassName);
            //bundle.putInt("_wxapi_payoptions_callback_flags", Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            this.fromBundle(bundle);
        }
    }

    public WxPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, Class callBackClassName) {
        this(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClassName.getName());
    }


}

