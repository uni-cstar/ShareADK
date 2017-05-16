
## 使用步骤
    # 配置
        在你的包名相应目录下新建一个wxapi目录，并在该wxapi目录下新增一个WXEntryActivity类，该类继承自Activity,并且在AndroidManifest.xml中为WXEntryActivity增加`android:exported="true"`属性
        ```
        //在AndroidManifest.xml中最终定义
        <activity android:name=".wxapi.WXEntryActivity"
                  android:exported="true"/>

        ```
        在WxEntryActivity的OnCreate方法中调用`WeiXin.createWxEntry(Activity activity, String appId)`得到一个IWxEntry的实例
        重写WxEntryActivity的onNewIntent(Intent intent)方法，在里面调用IWxEntry的onNewIntent(Intent intent)方法

    # 混淆
        ```
        -keep class com.tencent.mm.opensdk.** {
           *;
        }
        -keep class com.tencent.wxop.** {
           *;
        }
        -keep class com.tencent.mm.sdk.** {
           *;
        }

        -keep public class easy.share.wx.**{
            public *;
            protected *;
        }
        ```

    # 分享
        分享相关的操作均在文件WxShare中提供
        1.根据需要，调用`WxShare.addShareListener(WxShareResponseListener listener)`增加监听
        2.调用WxShare.ShareXXXX方法，得到一个SendMessageToWX.Req请求
        3.使用WxShare.senReq(Context context, String appId, BaseReq req)发送2步骤创建的请求
        4.根据需要，调用`WxShare.removeShareListener(WxShareResponseListener listener)`移除监听

    # 支付
        支付相关的操作均在文件WxPay中提供
        1.根据需要，调用`WxPay.addPayListener(WxShareResponseListener listener)`增加监听
        2.调用WxPay.buildPayReqXXXX方法，得到一个支付请求
        3.使用WxPay.senReq发送2步骤创建的支付请求
        4.根据需要，调用`WxPay.removePayListener(WxShareResponseListener listener)`移除监听

    # 登陆
        登陆相关的操作均在文件WxAuth中提供
        1.根据需要，调用`WxAuth.addAuthListener(WxLoginResponseListener listener)`增加监听
        2.调用`WxAuth.login(Context context, String appId, String scope, String state)`方法发起登录，如果是为了获取用户信息，scope参数可以使用WxAuth.ScopeForUserInfo作为参数
        3.根据需要，调用`WxAuth.removeAuthListener(WxLoginResponseListener listener)`移除监听
        以上步骤为登录获取用户accesstoken过程，获取token之后获取用户信息等可以使用WxAut提供的getXXXXUrl进行访问