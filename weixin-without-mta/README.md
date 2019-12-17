

## 使用步骤
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
            compile('halo.android.share:weixin-without-mta:1.4')
            //依赖了support-annotations，版本号根据自己项目所使用support系列版本确定
            compile "com.android.support:support-annotations:${versions.supportLibrary}"
        ```

        3.在你的包名相应目录下新建一个wxapi目录，并在该wxapi目录下创建一个Activity，名为WXEntryActivity,
        并且在AndroidManifest.xml中为WXEntryActivity的申明增加`android:exported="true"`属性

        ```
        //在AndroidManifest.xml中最终定义
           <activity
               android:name=".wxapi.WXEntryActivity"
               android:label="@string/app_name"
               android:theme="@android:style/Theme.Translucent.NoTitleBar"
               android:exported="true"
               android:taskAffinity="net.sourceforge.simcpux"
               android:launchMode="singleTask">
           </activity>

           //如果用到微信支付，定义支付界面
            <activity
              android:name=".wxapi.WXPayEntryActivity"
              android:exported="true"
              android:launchMode="singleTask">
            </activity>

        ```
        常规使用微信的登录分享等功能，只需让WxEntryActivity 继承BaseWxEntryActivity即可或者
        仿造完成自己所需的特定逻辑。
      
        // WxEntryActivity的基本内容如下
        ```
           public class WXEntryActivity extends BaseWxEntryActivity {

               @Override
               protected void onCreate(@Nullable Bundle savedInstanceState) {
                   super.onCreate(savedInstanceState);
               }

               @Override
               protected String getWxAppID() {
                   return "{your weixin appid}";
               }
           }
        ```
        
        完成以上步骤，微信就配置完成了


    # 混淆
       已经添加了混淆，会传递混淆规则，因此不用在app中配置相关混淆

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
    # h5支付
        见WxPay定义的h5PayUrlIntercept等方法，在webview的内置浏览器client中调用即可


    # 相关的
    code
    －6     签名错误


## DEBUG环境
如果需要运行SDK Sample工程，需要通过指定的debug.keystore来进行签名：

Android Studio环境下：
```
signingConfigs {
    debug {
        storeFile file("../debug.keystore")
    }
}
```

## 附带：[官方链接](https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Access_Guide/Android.html)

[关于微信分享 用户取消分享收到的回调也是成功的原因](https://open.weixin.qq.com/cgi-bin/announce?spm=a311a.9588098.0.0&action=getannouncement&key=11534138374cE6li&version=)