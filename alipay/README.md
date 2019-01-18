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
            compile('halo.android.integration:alipay:1.3')
            //额外说明：工程以provided的形式依赖了以下两个配置，所以在使用的时候，需要compile形式依赖同样的依赖，这样方便版本号统一管理
            compile 'com.android.support:support-annotations:{your project use version}'
            compile 'com.android.support:support-compat:{your project use version}'
        ```
    # 混淆
        已经添加了混淆规则，因此使用的时候不需要对此库做任何混淆规则处理

    # 支付及h5拦截支付，见AliPay文件定义的方法或使用仿造AliH5PayWebViewClient即可

# [另附App支付官网文档](https://docs.open.alipay.com/204/105296/)

# [附H5转Native支付官方文档](https://docs.open.alipay.com/203/106493)

