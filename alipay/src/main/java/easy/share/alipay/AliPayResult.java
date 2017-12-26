package easy.share.alipay;

import android.text.TextUtils;

import java.util.Map;

public class AliPayResult extends Result{

    private String memo;
    private String result;

    /**
     * 处理V2的结果
     *
     * @param rawResult 原始支付宝支付结果
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
     * @param rawResult 原始支付宝支付返回结果
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
