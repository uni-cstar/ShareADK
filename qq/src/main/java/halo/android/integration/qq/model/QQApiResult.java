package halo.android.integration.qq.model;

import com.tencent.tauth.UiError;

/**
 * Created by Lucio on 17/5/15.
 */

public class QQApiResult extends UiError {

    public QQApiResult(int errorCode, String errorMessage, String errorDetail) {
        super(errorCode, errorMessage, errorDetail);
    }

    public static QQApiResult fromUiError(UiError error) {
        return new QQApiResult(error.errorCode, error.errorMessage, error.errorDetail);
    }
}
