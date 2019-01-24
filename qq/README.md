[官方地址](http://wiki.open.qq.com/wiki/%E6%8E%A5%E5%85%A5QQ)，对官网的文档的实时性以及内容描述，真心不敢恭维，这可是三大巨头之一的对外开放平台文档啊，真令人大跌眼镜。


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
            compile 'halo.android.integration:qq:1.4'
        ```

        3.在应用的AndroidManifest.xml增加配置的<application>节点下增加以下配置（注：不配置将会导致无法调用API）
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
                  //注意tencent是必须的
                  <data android:scheme="tencent222222" />
              </intent-filter>
          </activity>

        ```

    # 混淆
        不用混淆，已经传递混淆
    # 使用
        所有相关的方法在QQ文件中已定义
        在需要使用分享／登录的Activity或Fragment中，调用`QQ.buildQQAction(Activity activity, String appId)`或调用`QQ.buildQQAction(Fragment fragment, String appId)`
        方法得到一个QQAction的实例，在Activity/Fragment的onActivityResult(int requestCode, int resultCode, Intent data)方法中
        调用QQAction的onActivityResult(int requestCode, int resultCode, Intent data)方法（这方法很重要，必须调用，否则无法回调）

    # 分享
        分享到QQ空间:`QQAction.shareToQZone(Activity activity, Bundle params, QQShareListener listener);`,目前只支持图文分享
        分享到QQ联系人:`QQAction.void shareToQQ(Activity activity, Bundle params, QQShareListener listener);`
        分享所需的Bundle在QQ文件中的方法定义
    # 登陆
         获取用户Token:`QQAction.login(QQLoginListener listener);`
         获取用户信息:`void loginForUserInfo(QQUserInfoListener listener);`


    #备注  QQ不能分享纯文本和纯图片
