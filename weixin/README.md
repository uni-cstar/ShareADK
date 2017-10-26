
## 使用步骤
    # 配置
        在你的包名相应目录下新建一个wxapi目录，并在该wxapi目录下创建一个Activity，名为WXEntryActivity,
        并且在AndroidManifest.xml中为WXEntryActivity的申明增加`android:exported="true"`属性
        ```
        //在AndroidManifest.xml中最终定义
        <activity android:name=".wxapi.WXEntryActivity"
                  android:exported="true"/>

        ```
        在WxEntryActivity的OnCreate方法中调用`WeiXin.createWxEntry(Activity activity, String appId)`得到一个IWxEntry的实例，
        并调用IWxEntry的onActivityCreate方法。
        重写WxEntryActivity的onNewIntent(Intent intent)方法，
        在里面调用IWxEntry的onNewIntent(Intent intent)方法
        完成以上步骤，微信就配置完成了

        // WxEntryActivity的基本内容如下
         public class WXEntryActivity extends AppCompatActivity {

                IWxEntry wxEntry;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    wxEntry = WeiXin.createWxEntry(this, "你的微信AppId");
                    wxEntry.onActivityCreate();
                }

                @Override
                protected void onNewIntent(Intent intent) {
                    super.onNewIntent(intent);
                    wxEntry.onNewIntent(intent);
                }
            }


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
    ** 注意：微信对缩略图大小有限制，因此缩略图过大，会导致分享不成功
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



    # 相关的
    code
    －6     签名错误