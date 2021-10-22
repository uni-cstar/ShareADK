package halo.android.integration.qq.impl;

import halo.android.integration.qq.model.QQApiResult;

/**
 * Created by Lucio on 17/5/15.
 */

public interface QQApiListener {
    void onQQApiFailed(QQApiResult error);
    void onQQApiCancel();
}
