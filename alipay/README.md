## 使用步骤

    # 混淆
        ```
            -libraryjars libs/alipaySdk-20170407.jar

            -keep class com.alipay.android.app.IAlixPay{*;}
            -keep class com.alipay.android.app.IAlixPay$Stub{*;}
            -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
            -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
            -keep class com.alipay.sdk.app.PayTask{ public *;}
            -keep class com.alipay.sdk.app.AuthTask{ public *;}

            -keep public easy.share.alipay.**{
               public *;
               protected *;
            }
        ```
    # 支付
        见AliPay
        AliPayH5WebViewClient：用于H5支付转Native支付，在{@link #shouldOverrideUrlLoadingAfterAliPayCheck(WebView, String)}方法中处理本该在{@link #shouldOverrideUrlLoading(WebView, String)}执行的逻辑
