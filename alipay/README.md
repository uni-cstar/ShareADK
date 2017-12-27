## 使用步骤（latest_version = 1.1）
    # 工程配置
        1、在工程的build.gradle文件中配置以下语句
        ```
            allprojects {
                repositories {
                    jcenter()
                    maven {
                        url 'https://dl.bintray.com/supluo/maven'
                    }
                }

            }
        ```
        2.在（app）module的build.gradle文件中配置依赖
        ```
            compile('easy.share:alipay:{latest_version}')
        ```
    # 混淆
        ```
            -keep class com.alipay.android.app.IAlixPay{*;}
            -keep class com.alipay.android.app.IAlixPay$Stub{*;}
            -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
            -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
            -keep class com.alipay.sdk.app.PayTask{ public *;}
            -keep class com.alipay.sdk.app.AuthTask{ public *;}
            -keep class com.alipay.sdk.app.H5PayCallback {
                <fields>;
                <methods>;
            }
            -keep class com.alipay.android.phone.mrpc.core.** { *; }
            -keep class com.alipay.apmobilesecuritysdk.** { *; }
            -keep class com.alipay.mobile.framework.service.annotation.** { *; }
            -keep class com.alipay.mobilesecuritysdk.face.** { *; }
            -keep class com.alipay.tscenter.biz.rpc.** { *; }
            -keep class org.json.alipay.** { *; }
            -keep class com.alipay.tscenter.** { *; }
            -keep class com.ta.utdid2.** { *;}
            -keep class com.ut.device.** { *;}


            # easy 库文件
            -keep public easy.share.alipay.**{
               public *;
               protected *;
            }
        ```
    # 支付及h5拦截支付，见AliPay文件定义的方法或使用仿造AliH5PayWebViewClient即可

