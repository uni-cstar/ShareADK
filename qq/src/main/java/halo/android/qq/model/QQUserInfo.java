package halo.android.qq.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lucio on 17/5/15.
 */

public class QQUserInfo {
    public String nickname;
    public String figureurl;

    /**
     * 性别 ： 男 or 女 or '未知'
     */
    public String gender;

    public static QQUserInfo fromJsonObject(JSONObject jsonObject) throws JSONException {
        QQUserInfo uinfo = new QQUserInfo();
        uinfo.nickname = jsonObject.getString("nickname");
        uinfo.figureurl = jsonObject.getString("figureurl");
        uinfo.gender = jsonObject.getString("gender");
        return uinfo;
    }

}
