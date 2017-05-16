
## 使用步骤
    # 配置
        在项目根目录的build.gradle中设置中央仓库
        maven { url "https://dl.bintray.com/thelasterstar/maven/" }
        在需要引入SDK的module目录的build.gradle中引入sdk-core依赖
        如下：
        ```
        buildscript {

            repositories {
                jcenter()
                //添加以下两句
                mavenCentral();
                maven { url "https://dl.bintray.com/thelasterstar/maven/" }
            }
            ...
        }
        allprojects {
                    repositories {
                        jcenter()
                        //添加以下两句
                        mavenCentral();
                        maven { url "https://dl.bintray.com/thelasterstar/maven/" }
                    }
        }

        ```

        在需要引入SDK的module目录的build.gradle中引入依赖
        ```
        dependencies {
            compile 'com.sina.weibo.sdk:core:2.0.3:openDefaultRelease@aar'
        }
        ```

        //```
        //dependencies {
        //    compile 'com.sina.weibo.sdk:core:2.0.3:openDefaultRelease@aar'
        //}
        //```

    # 注册
        在使用微博相关功能之前，必须进行注册，在合适的地方调用`SinaWb.register(Context context, String appKey, String redirectUrl, String scope)`

    # 分享
        分享相关的操作均在文件ISinaWbShare中定义
        1.在Activity的合适地方调用`SinaWb.buildWbShare(Activity activity)`得到ISinaWbShare实例
        2.重写对应Activity的onNewIntent方法，在此方法中调用ISinaWbShare的onNewIntent(Intent it)方法
        3.使用ISinaWbShare提供的相关方法进行分享，SinaWb提供了创建分享对象的一些缺省方法，新版本只支持文本，图片，网页等内容的组合，不再支持老版本的一些分享内容。

    # 登陆
        登陆相关的操作均在文件ISinaWbAuth中提供
        1.在Activity的合适地方调用`SinaWb.buildWbAuth(Activity activity)`得到ISinaWbAuth实例
        2.重写对应Activity的onActivityResult(int requestCode, int resultCode, Intent data)方法，
        在此方法中调用ISinaWbAuth的onActivityResult(int requestCode, int resultCode, Intent data)方法
        3.调用ISinaWbAuth的getSinaWbAuth(SinaWbAuthListener listener)获取用户AccessToken
        4.获取AccessToken成功之后，获取其它的用户信息之类的OpenApi，需要客户端自己实现网络请求，具体详见http://open.weibo.com/wiki/微博API
        以上步骤为登录获取用户accesstoken过程，获取token之后获取用户信息等可以使用WxAut提供的getXXXXUrl进行访问

    # 混淆
        ```
        -keep public class easy.share.sina.**{
            public *;
            protected *;
        }
        ```