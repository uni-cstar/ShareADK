package easy.share.alipay;

/**
 * Created by Lucio on 17/12/21.
 */
class Result {

    protected String resultStatus;

    /**
     * 本次操作的状态返回值，标识本次调用的结果
     *
     * @return code
     * 9000 订单支付成功
     * 8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     * 4000	订单支付失败
     * 5000——重复请求
     * 6001	用户中途取消
     * 6002	网络连接出错
     * 6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     * 其它	其它支付错误
     */
    public String getResultStatus() {
        return resultStatus;
    }
}
