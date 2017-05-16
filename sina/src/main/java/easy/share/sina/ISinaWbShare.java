package easy.share.sina;

import android.content.Intent;
import android.graphics.Bitmap;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;

/**
 * Created by Lucio on 17/5/16.
 */

public interface ISinaWbShare {

    /**
     * 微博分享需要
     * 在{@link android.app.Activity#onNewIntent(Intent)}中调用此方法
     *
     * @param it
     */
    void onNewIntent(Intent it);

    void shareMessage(WeiboMultiMessage msg, WbShareCallback callback);

    /**
     * 分享文字
     *
     * @param text
     */
    void shareText(String text, WbShareCallback callback);

    /**
     * 分享图文消息
     *
     * @param text
     * @param imageObject
     */
    void shareImageText(String text, ImageObject imageObject, WbShareCallback callback);


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
    void shareWebPageObj(String title, String desc, String targetUrl, String defaultText, Bitmap thumbBmp, WbShareCallback callback);

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
    void shareWebPageObj(String title, String desc, String targetUrl, String defaultText, byte[] thumbData, WbShareCallback callback);
}
