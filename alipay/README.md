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
        见AliPay中提供的方法，支付信息出于安全性，必须来源于服务器，因此本地处理的事情较少。
        AliPayH5WebViewClient：用于H5支付转Native支付，在{@link #shouldOverrideUrlLoadingAfterAliPayCheck(WebView, String)}方法中处理本该在{@link #shouldOverrideUrlLoading(WebView, String)}执行的逻辑
        此方法有一个问题：h5开始支付之后，会显示loading状态，在支付界面如果取消支付，网页会无法或者支付状态导致一直处于loading状态
