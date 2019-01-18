package halo.android.qq.impl;

import halo.android.qq.model.QQUserInfo;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQUserInfoListener extends QQApiListener {
    void onQQUserInfoSuccess(QQUserInfo userInfo);
}
