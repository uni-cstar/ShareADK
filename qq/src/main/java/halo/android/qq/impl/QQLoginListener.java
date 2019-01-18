package halo.android.qq.impl;

import halo.android.qq.model.QQAuthInfo;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQLoginListener extends QQApiListener {

    void onQQLoginSuccess(QQAuthInfo authInfo);

}
