# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Lucio/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#不混淆QQ的jar包

# [START] qq-sdk 混淆规则
#忽略提醒
-dontwarn com.tencent.connect.**
-dontwarn com.tencent.open.**
-dontwarn com.tencent.tauth.**

#保留qq的jar包不混淆
-keep class com.tencent.connect.**{
    *;
}
-keep class com.tencent.open.**{
    *;
}
-keep class com.tencent.tauth.**{
    *;
}
# [END] qq-sdk 混淆规则

# [START] integration-qq 混淆规则

-keep public class halo.android.integration.qq.**{
   public *;
   protected *;
}

-keepclassmembernames class halo.android.integration.qq.**{
    public *;
    protected *;
}

# [END] integration-qq 混淆规则

