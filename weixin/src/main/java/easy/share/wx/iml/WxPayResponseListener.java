package easy.share.wx.iml;

import com.tencent.mm.opensdk.modelpay.PayResp;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxPayResponseListener extends WxResponseListener {

    void onWxPaySuccess(PayResp resp);

}
