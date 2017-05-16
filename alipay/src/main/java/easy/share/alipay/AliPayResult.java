package easy.share.alipay;

import android.text.TextUtils;

import java.util.Map;

public class AliPayResult {

    private String resultStatus;
    private String result;
    private String memo;

    /**
     * 处理V2的结果
     *
     * @param rawResult
     */
    public AliPayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    /**
     * 处理V1的结果
     *
     * @param rawResult
     */
    public AliPayResult(String rawResult) {
        if (TextUtils.isEmpty(rawResult))
            return;

        String[] resultParams = rawResult.split(";");
        for (String resultParam : resultParams) {
            if (resultParam.startsWith("resultStatus")) {
                resultStatus = gatValue(resultParam, "resultStatus");
            }
            if (resultParam.startsWith("result")) {
                result = gatValue(resultParam, "result");
            }
            if (resultParam.startsWith("memo")) {
                memo = gatValue(resultParam, "memo");
            }
        }

    }

    private String gatValue(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(),
                content.lastIndexOf("}"));
    }

    public static AliPayResult wrap(Map<String, String> rawResult) {
        return new AliPayResult(rawResult);
    }

    public static AliPayResult wrap(String rawResult) {
        return new AliPayResult(rawResult);
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    /**
     * 本次操作的状态返回值，标识本次调用的结果
     *
     * @return code
     * 9000 订单支付成功
     * 8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     * 4000	订单支付失败
     * 6001	用户中途取消
     * 6002	网络连接出错
     * 6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     * 其它	其它支付错误
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * 提示信息
     * @return 保留参数，一般无内容。
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return 本次操作返回的结果数据
     */
    public String getResult() {
        return result;
    }
}
