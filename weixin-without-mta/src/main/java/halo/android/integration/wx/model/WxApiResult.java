package halo.android.integration.wx.model;

/**
 * Created by Lucio on 17/5/12.
 */

class WxApiResult {

    public int errcode;

    public String errmsg;

    public boolean isSuccess() {
        return errcode == 0;
    }

}
