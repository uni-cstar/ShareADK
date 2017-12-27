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

-ignorewarnings
-optimizationpasses 5
-verbose
-dontskipnonpubliclibraryclasses

#不混淆QQ的jar包
#-libraryjars libs/open_sdk_r5923_lite_3.3.0.jar

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

#壳子中的混淆规则
-keepclassmembernames class easy.share.qq.**{
    public *;
    protected *;
}

-keep public class easy.share.qq.**{
   public *;
   protected *;
}

