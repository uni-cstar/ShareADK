package easy.share.qq.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQAction {

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void setScope(String scope);

    void login(QQLoginListener listener);

    void loginForUserInfo(QQUserInfoListener listener);

    void logout();

    void shareToQZone(Activity activity, Bundle params, QQShareListener listener);

    void shareToQQ(Activity activity, Bundle params, QQShareListener listener);
}
