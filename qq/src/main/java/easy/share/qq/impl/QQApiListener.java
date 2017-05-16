package easy.share.qq.impl;

import easy.share.qq.model.QQApiResult;

/**
 * Created by Lucio on 17/5/15.
 */

interface QQApiListener {
    void onQQApiFailed(QQApiResult error);
    void onQQApiCancel();
}
