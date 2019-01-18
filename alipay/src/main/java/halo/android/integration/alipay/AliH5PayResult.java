package halo.android.integration.alipay;

import com.alipay.sdk.util.H5PayResultModel;

/**
 * Created by Lucio on 17/12/21.
 */
public class AliH5PayResult extends Result {

    private String returnUrl;

    public String getReturnUrl() {
        return returnUrl;
    }

    static AliH5PayResult createFromH5PayResultModel(H5PayResultModel model) {
        if (model == null)
            return null;
        AliH5PayResult result = new AliH5PayResult();
        result.resultStatus = model.getResultCode();
        result.returnUrl = model.getReturnUrl();
        return result;
    }
}
