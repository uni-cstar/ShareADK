package easy.share.alipay;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;

/**
 * Created by Lucio on 17/5/4.
 * 支付宝支付，实现H5转Native支付
 */

public class AliPayH5WebViewClient extends WebViewClient {

    Activity mActivity;

    PayTask mPayCheckTask;

    public AliPayH5WebViewClient(Activity activity) {
        mActivity = activity;
        mPayCheckTask = new PayTask(activity);
    }

    /**
     * 在{@link #shouldOverrideUrlLoadingAfterAliPayCheck(WebView, String)}
     * 方法中处理本该在{@link #shouldOverrideUrlLoading(WebView, String)}执行的逻辑
     *
     * @param view
     * @param url
     * @return
     */
    @Deprecated
    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, String url) {
        if (!(url.startsWith("http") || url.startsWith("https"))) {
            return shouldOverrideUrlLoadingAfterAliPayCheck(view, url);
        }
        final String payUrl = mPayCheckTask.fetchOrderInfoFromH5PayUrl(url);
        if (TextUtils.isEmpty(payUrl)) {
            return shouldOverrideUrlLoadingAfterAliPayCheck(view, url);
        } else {
            PayTask payTask = new PayTask(mActivity);
            new MyH5PayTask(payUrl, view).execute(payTask);
            return true;
        }
//        return checkAliPayH5Url(mActivity, view, url) || shouldOverrideUrlLoadingAfterAliPayCheck(view, url);
    }

    public boolean shouldOverrideUrlLoadingAfterAliPayCheck(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    /**
     * 支付宝支付  h5转native支付
     *
     * @param activity
     * @param view
     * @param url
     * @return true:是h5支付地址，false: otherwise，客户端自己处理url
     */
    public static boolean checkAliPayH5Url(final Activity activity, final WebView view, String url) {
        if (!(url.startsWith("http") || url.startsWith("https"))) {
            return false;
        }
        final PayTask payTask = new PayTask(activity);
        final String payUrl = payTask.fetchOrderInfoFromH5PayUrl(url);
        if (TextUtils.isEmpty(payUrl)) {
            return false;
        } else {
            new MyH5PayTask(payUrl, view).execute(payTask);
            return true;
        }
    }

    private static class MyH5PayTask extends AsyncTask<PayTask, Integer, H5PayResultModel> {

        String payUrl;
        WebView mWebView;

        MyH5PayTask(String url, WebView webView) {
            this.payUrl = url;
            this.mWebView = webView;
        }

        @Override
        protected H5PayResultModel doInBackground(PayTask... params) {
            return params[0].h5Pay(payUrl, true);
        }

        @Override
        protected void onPostExecute(H5PayResultModel h5PayResultModel) {
            if (!TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
                this.mWebView.loadUrl(h5PayResultModel.getReturnUrl());
            }
        }
    }
}
