package easy.share.alipay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

/**
 * Created by Lucio on 17/5/4.
 */
public final class AliPay {

    /**
     * 支付宝v1 版本支付
     * 强烈建议商户直接依赖服务端的异步通知，忽略同步返回
     *
     * @param activity
     * @param orderInfo 必须来源于服务器， app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&#38;#38;连接。
     * @return <p>同步结果，https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&#38;#38;treeId=59&#38;#38;articleId=103665&#38;#38;docType=1</p>
     */
    @Deprecated
    public static AliPayResult payV1(Activity activity, String orderInfo) {
        return payV1(activity, orderInfo, true);
    }

    /**
     * 支付宝v1 版本支付
     * 强烈建议商户直接依赖服务端的异步通知，忽略同步返回
     *
     * @param activity
     * @param orderInfo   必须来源于服务器， app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&#38;连接。
     * @param showLoading
     * @return 同步结果，https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&#38;treeId=59&#38;articleId=103665&#38;docType=1
     */
    @Deprecated
    public static AliPayResult payV1(Activity activity, String orderInfo, boolean showLoading) {
        PayTask payTask = new PayTask(activity);
        return AliPayResult.wrap(payTask.pay(orderInfo, showLoading));
    }

    /**
     * 支付宝v2 版本支付
     * 商户可以将同步结果仅仅作为一个支付结束的通知（忽略执行校验），实际支付是否成功，完全依赖服务端异步通知。
     * 需要异步调用
     *
     * @param activity
     * @param orderInfo 必须来源于服务器， app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&#38;连接。
     * @return
     */
    public static AliPayResult payV2(Activity activity, String orderInfo) {
        return payV2(activity, orderInfo, true);
    }

    /**
     * 支付宝v2 版本支付
     * 商户可以将同步结果仅仅作为一个支付结束的通知（忽略执行校验），实际支付是否成功，完全依赖服务端异步通知。
     *
     * @param activity
     * @param orderInfo   必须来源于服务器， app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&#38;连接。
     * @param showLoading 用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，
     *                    这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，
     *                    直到唤起H5支付页面或者唤起外部的钱包付款页面loading才消失。（
     *                    建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）
     * @return App支付同步通知参数
     * https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.xN1NnL&#38;treeId=204&#38;articleId=105302&#38;docType=1
     */
    public static AliPayResult payV2(Activity activity, String orderInfo, boolean showLoading) {
        PayTask payTask = new PayTask(activity);
        return AliPayResult.wrap(payTask.payV2(orderInfo, showLoading));
    }

}
