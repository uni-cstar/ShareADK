package halo.android.qq;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.connect.common.Constants;

import halo.android.qq.model.QQAuthInfo;

/**
 * Created by Lucio on 17/5/15.
 */

class QQPref {

    static final String FILE_NAME = "qq_auth_pref";


    public static void saveQQAuthInfo(Context context, QQAuthInfo authInfo) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.PARAM_OPEN_ID, authInfo.openid);
        editor.putString(Constants.PARAM_EXPIRES_IN, authInfo.expires_in);
        editor.putString(Constants.PARAM_ACCESS_TOKEN, authInfo.access_token);
        editor.apply();
    }

    public static QQAuthInfo getQQAuthInfo(Context context) {
        QQAuthInfo authInfo = new QQAuthInfo();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        authInfo.access_token = sp.getString(Constants.PARAM_ACCESS_TOKEN, null);
        if(authInfo.access_token == null){
            clear(context);
            return null;
        }

        authInfo.expires_in = sp.getString(Constants.PARAM_EXPIRES_IN,null);
        authInfo.openid = sp.getString(Constants.PARAM_OPEN_ID,null);
        return authInfo;
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
