package easy.share.wx.iml;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

/**
 * Created by Lucio on 17/5/12.
 */

public interface WxShareResponseListener extends WxResponseListener {
    void onWxShareSuccess(SendMessageToWX.Resp resp);
}
