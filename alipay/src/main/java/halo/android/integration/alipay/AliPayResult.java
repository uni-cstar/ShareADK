package halo.android.integration.alipay;

import android.text.TextUtils;

import java.util.Map;

public class AliPayResult extends Result{

    private static final String KEY_RESULT_STATUS = "resultStatus";
    private static final String KEY_RESULT = "result";
    private static final String KEY_MEMO = "memo";

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
            if (TextUtils.equals(key, KEY_RESULT_STATUS)) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, KEY_RESULT)) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, KEY_MEMO)) {
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
            if (resultParam.startsWith(KEY_RESULT_STATUS)) {
                resultStatus = getV1Value(resultParam, KEY_RESULT_STATUS);
            }
            if (resultParam.startsWith(KEY_RESULT)) {
                result = getV1Value(resultParam, KEY_RESULT);
            }
            if (resultParam.startsWith(KEY_MEMO)) {
                memo = getV1Value(resultParam, KEY_MEMO);
            }
        }
    }

    //获取v1结果中的值
    private String getV1Value(String content, String key) {
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
        return String.format("%s={%s};\n%s={%s}\n%s={%s}",KEY_RESULT_STATUS,resultStatus,KEY_RESULT,result,KEY_MEMO,memo);
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
