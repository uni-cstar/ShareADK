package easy.share.sina;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.utils.Utility;

/**
 * Created by Lucio on 17/5/15.
 */

public class SinaWb {

    /**
     * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    /**
     * 注册
     *
     * @param context
     * @param appKey      应用的 APP_KEY
     * @param redirectUrl 当前应用的回调页，第三方应用可以使用自己的回调页。
     *                    建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * @param scope       应用对应的权限，可直接设置成空即可。
     *                    详情请查看{@link #SCOPE} 中对应的注释。
     */
    public static void register(Context context, String appKey, String redirectUrl, String scope) {
        WbSdk.install(context.getApplicationContext(), new AuthInfo(context.getApplicationContext(), appKey, redirectUrl, scope));
    }

    /**
     * 返回微博分享帮助类
     *
     * @param activity
     * @return
     */
    public static ISinaWbShare buildWbShare(Activity activity) {
        return new SinaWbShareImpl(activity);
    }

    /**
     * 返回微博鉴权类
     * @param activity
     * @return
     */
    public static ISinaWbAuth buildWbAuth(Activity activity){
        return new SinaWbAuthImpl(activity);
    }

    /**
     * 创建文本对象
     *
     * @param content 内容，长度0-1024
     * @return
     */
    public static TextObject buildTextObj(String content) {
        TextObject textObject = new TextObject();
        textObject.text = content;
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @param bitmap 分享的图片，注意：最终压缩过的缩略图大小不得超过 32kb。
     * @return 图片消息对象。
     */
    public static ImageObject buildImageObj(Bitmap bitmap) {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    /**
     * 本地文件路径
     *
     * @param imagePath
     * @return
     */
    public static ImageObject buildImageObj(String imagePath) {
        ImageObject imageObject = new ImageObject();
        imageObject.imagePath = imagePath;
        return imageObject;
    }

    /**
     * @param imageData
     * @return
     */
    public static ImageObject buildImageObj(byte[] imageData) {
        ImageObject imageObject = new ImageObject();
        imageObject.imageData = imageData;
        return imageObject;
    }

    private static WebpageObject buildWebPageObj(String title, String desc, String targetUrl, String defaultText) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;
        mediaObject.description = desc;
        mediaObject.actionUrl = targetUrl;
        mediaObject.defaultText = defaultText;
        return mediaObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @param title       标题
     * @param desc        描述
     * @param targetUrl   跳转地址
     * @param defaultText 默认文本（可以使用程序名字）
     * @param thumbBmp    设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
     * @return
     */
    public static WebpageObject buildWebPageObj(String title, String desc, String targetUrl, String defaultText, Bitmap thumbBmp) {
        WebpageObject mediaObject = buildWebPageObj(title, desc, targetUrl, defaultText);
        // 设置 Bitmap 类型的图片到视频对象里
        mediaObject.setThumbImage(thumbBmp);
        return mediaObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @param title       标题
     * @param desc        描述
     * @param targetUrl   跳转地址
     * @param defaultText 默认文本（可以使用程序名字）
     * @param thumbData   设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
     * @return
     */
    public static WebpageObject buildWebPageObj(String title, String desc, String targetUrl, String defaultText, byte[] thumbData) {
        WebpageObject mediaObject = buildWebPageObj(title, desc, targetUrl, defaultText);
        // 设置 Bitmap 类型的图片到视频对象里
        mediaObject.thumbData = thumbData;
        return mediaObject;
    }

}
