package halo.android.integration.qq.impl;

import halo.android.integration.qq.model.QQApiResult;

/**
 * Created by Lucio on 17/5/15.
 */

interface QQApiListener {
    void onQQApiFailed(QQApiResult error);
    void onQQApiCancel();
}
