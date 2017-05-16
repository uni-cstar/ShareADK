package easy.share.qq.impl;

import easy.share.qq.model.QQUserInfo;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQUserInfoListener extends QQApiListener {
    void onQQUserInfoSuccess(QQUserInfo userInfo);
}
