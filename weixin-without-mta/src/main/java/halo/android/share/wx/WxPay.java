package halo.android.share.wx;

import android.content.Context;
import android.webkit.WebView;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.HashMap;
import java.util.Map;

import halo.android.share.wx.iml.WxPayResponseListener;

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
        return new WxPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign);
    }

    /**
     * 创建支付请求
     *
     * @param appId         微信开放平台审核通过的应用APPID
     * @param partnerId     微信支付分配的商户号
     * @param prepayId      预支付交易会话ID,微信返回的支付交易会话ID
     * @param packageValue  扩展字段，暂填写固定值Sign=WXPay
     * @param nonceStr      随机字符串，不长于32位。推荐随机数生成算法
     * @param timeStamp     时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     * @param sign          签名
     * @param callBackClass 回调activity，可以为空，则WxPayEntryActivity触发回调，也可以制定自定义activity名字
     * @return
     */
    public static PayReq buildPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, Class callBackClass) {
        return buildPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClass.getName());
    }

    /**
     * 创建支付请求
     *
     * @param appId             微信开放平台审核通过的应用APPID
     * @param partnerId         微信支付分配的商户号
     * @param prepayId          预支付交易会话ID,微信返回的支付交易会话ID
     * @param packageValue      扩展字段，暂填写固定值Sign=WXPay
     * @param nonceStr          随机字符串，不长于32位。推荐随机数生成算法
     * @param timeStamp         时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     * @param sign              签名
     * @param callBackClassName 回调activity名字，可以为空，则WxPayEntryActivity触发回调，也可以制定自定义activity名字
     * @return
     */
    public static PayReq buildPayReq(String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, String callBackClassName) {
        return new WxPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClassName);
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
     */
    public static boolean senPayReq(Context context, String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign) throws WxNotInstalledException {
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
     * @param callBackClass 回调activity名字，可以为空，则WxEntryActivity触发回调，也可以制定自定义activity名字
     * @throws WxNotInstalledException      微信未安装
     */
    public static boolean senPayReq(Context context, String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, Class callBackClass) throws WxNotInstalledException {
        PayReq payReq = buildPayReq(appId, partnerId, prepayId, packageValue, nonceStr, timeStamp, sign, callBackClass);
        return senPayReq(context, appId, payReq);
    }

    /**
     * 发送支付请求
     *
     * @param context
     * @param appId
     * @param req
     * @return
     * @throws WxNotInstalledException
     */
    public static boolean senPayReq(Context context, String appId, BaseReq req) throws WxNotInstalledException {
        return WeiXin.senReq(context, appId, req);
    }

    /**
     * 拦截h5地址支付
     *
     * @param url     目标链接
     * @param referer 商户申请H5时提交的授权域名
     * @param webView
     */
    public static void h5PayUrlIntercept(String url, String referer, WebView webView) {
        h5PayUrlIntercept(url, referer, webView, new HashMap<String, String>());
    }

    /**
     * @param url          目标链接
     * @param referer      商户申请H5时提交的授权域名
     * @param webView
     * @param extraHeaders 客户端本身要添加的header
     */
    public static void h5PayUrlIntercept(String url, String referer, WebView webView, Map<String, String> extraHeaders) {
        extraHeaders.put("Referer", referer);// "http://wxpay.wxutil.com");
        webView.loadUrl(url, extraHeaders);
    }

    /**
     * 是否是h5支付协议
     *
     * @param url
     * @return
     */
    public static boolean isH5PaySchema(String url) {
        return url.toLowerCase().startsWith("weixin://wap/pay?");
    }
}
