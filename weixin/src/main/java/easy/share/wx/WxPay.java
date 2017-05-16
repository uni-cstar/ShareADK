package easy.share.wx;

import android.content.Context;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;

import easy.share.wx.iml.WxPayResponseListener;

/**
 * Created by Lucio on 17/5/11.
 * 微信支付
 */

public class WxPay {

    /**
     * 添加回调监听
     *
     * @param listener
     */
    public static void addPayListener(WxPayResponseListener listener) {
        WxCallBackDelegate.CallBackHolder.getInstance().addPayListener(listener);
    }

    /**
     * 移除回调监听
     *
     * @param listener
     */
    public static void removePayListener(WxPayResponseListener listener) {
        WxCallBackDelegate.CallBackHolder.getInstance().removePayListener(listener);
    }

    /**
     * 创建支付请求
     *
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param packageValue
     * @param nonceStr
     * @param timeStamp
     * @param sign
     * @return
     */
    public static PayReq buildPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) {
        PayReq req = new WxPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign);
        return req;
    }

    /**
     * 创建支付请求
     *
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param packageValue
     * @param nonceStr
     * @param timeStamp
     * @param sign
     * @param callBackClassName
     * @return
     */
    public static PayReq buildPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, Class callBackClassName) {
        PayReq req = new WxPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClassName.getName());
        return req;
    }

    /**
     * 发起支付请求
     *
     * @param appId        微信开放平台审核通过的应用APPID
     * @param partnerId    微信支付分配的商户号
     * @param prepayId     预支付交易会话ID,微信返回的支付交易会话ID
     * @param packageValue 扩展字段，暂填写固定值Sign=WXPay
     * @param nonceStr     随机字符串，不长于32位。推荐随机数生成算法
     * @param timeStamp    时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     * @param sign         签名
     * @throws WxNotInstalledException      微信未安装
     * @throws WxNotSupportVersionException 微信版本不支持
     */
    public static boolean senPayReq(Context context, String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) throws WxNotInstalledException, WxNotSupportVersionException {
        PayReq payReq = buildPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign);
        return senPayReq(context, appId, payReq);
    }

    /**
     * 发起支付请求
     *
     * @param appId         微信开放平台审核通过的应用APPID
     * @param partnerId     微信支付分配的商户号
     * @param prepayId      预支付交易会话ID,微信返回的支付交易会话ID
     * @param packageValue  扩展字段，暂填写固定值Sign=WXPay
     * @param nonceStr      随机字符串，不长于32位。推荐随机数生成算法
     * @param timeStamp     时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     * @param sign          签名
     * @param callBackClass 回调类名
     * @throws WxNotInstalledException      微信未安装
     * @throws WxNotSupportVersionException 微信版本不支持
     */
    public static boolean senPayReq(Context context, String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, Class callBackClass) throws WxNotInstalledException, WxNotSupportVersionException {
        PayReq payReq = buildPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClass);
        return senPayReq(context, appId, payReq);
    }

    public static boolean senPayReq(Context context, String appId, BaseReq req) throws WxNotInstalledException, WxNotSupportVersionException {
        return WxUtil.senReq(context,appId,req);
    }
}
