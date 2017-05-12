package easy.share.wx.iml;

import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxLoginResponseListener extends WxResponseListener {

    void onWxLoginSuccess(SendAuth.Resp resp);

}
