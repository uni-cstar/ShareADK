package halo.android.integration.qq.model;

import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lucio on 17/5/15.
 */

public class QQAuthInfo {
//    public String pf;
    public String expires_in;
    public String openid;
    public String access_token;

    public static QQAuthInfo fromJsonObject(JSONObject jObj) throws JSONException {
        QQAuthInfo info = new QQAuthInfo();
        info.access_token = jObj.getString(Constants.PARAM_ACCESS_TOKEN);
        info.expires_in = jObj.getString(Constants.PARAM_EXPIRES_IN);
        info.openid = jObj.getString(Constants.PARAM_OPEN_ID);
//        info.pf = jObj.getString(Constants.PARAM_PLATFORM_ID);
        return info;
    }

    public static QQAuthInfo fromQQToken(QQToken token) {
        QQAuthInfo info = new QQAuthInfo();
        info.access_token = token.getAccessToken();
        info.openid = token.getOpenId();

        info.expires_in = (token.getExpireTimeInSecond() - System.currentTimeMillis()) / 1000L + "";
        return info;
    }
}
