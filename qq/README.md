
## 使用步骤
    # 配置
        在应用的AndroidManifest.xml增加配置的<application>节点下增加以下配置（注：不配置将会导致无法调用API）
        ```
          <activity
              android:name="com.tencent.tauth.AuthActivity"
              android:noHistory="true"
              android:launchMode="singleTask" >
              <intent-filter>
                  <action android:name="android.intent.action.VIEW" />
                  <category android:name="android.intent.category.DEFAULT" />
                  <category android:name="android.intent.category.BROWSABLE" />
                  //222222为您申请的appid
                  <data android:scheme="tencent222222" />
              </intent-filter>
          </activity>

        ```


    # 混淆
        ```
        -keep class com.tencent.open.TDialog$*
        -keep class com.tencent.open.TDialog$* {*;}
        -keep class com.tencent.open.PKDialog
        -keep class com.tencent.open.PKDialog {*;}
        -keep class com.tencent.open.PKDialog$*
        -keep class com.tencent.open.PKDialog$* {*;}

        -keep public class easy.share.qq.**{
           public *;
           protected *;
        }
        ```
    # 使用
        所有相关的方法在QQ文件中已定义
        在需要使用分享／登录的Activity或Fragment中，调用`QQ.buildQQAction(Activity activity, String appId)`或调用`QQ.buildQQAction(Fragment fragment, String appId)`
        方法得到一个QQAction的实例，在Activity/Fragment的onActivityResult(int requestCode, int resultCode, Intent data)方法中调用QQAction的onActivityResult(int requestCode, int resultCode, Intent data)方法

    # 分享
        分享到QQ空间:`QQAction.shareToQZone(Activity activity, Bundle params, QQShareListener listener);`,目前只支持图文分享
        分享到QQ联系人:`QQAction.void shareToQQ(Activity activity, Bundle params, QQShareListener listener);`
        分享所需的Bundle在QQ文件中的方法定义
    # 登陆
         获取用户Token:`QQAction.login(QQLoginListener listener);`
         获取用户信息:`void loginForUserInfo(QQUserInfoListener listener);`