package halo.android.integration.qq.impl;

import halo.android.integration.qq.model.QQUserInfo;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQUserInfoListener extends QQApiListener {
    void onQQUserInfoSuccess(QQUserInfo userInfo);
}
