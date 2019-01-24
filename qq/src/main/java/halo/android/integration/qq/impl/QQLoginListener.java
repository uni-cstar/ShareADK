package halo.android.integration.qq.impl;

import halo.android.integration.qq.model.QQAuthInfo;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQLoginListener extends QQApiListener {

    void onQQLoginSuccess(QQAuthInfo authInfo);

}
