package halo.android.integration.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import halo.android.integration.qq.impl.QQAction;


/**
 * Created by Lucio on 17/5/15.
 */

public class QQ {

    public static final String SCOPE_GET_USE_INFO = "get_user_info";
    public static final String SCOPE_ADD_T = "add_t";
    public static final String SCOPE_ALL = "all";

    public static QQAction buildQQAction(Fragment fragment, String appId) {
        return new QQActionImpl(fragment, appId);
    }

    public static QQAction buildQQAction(Activity activity, String appId) {
        return new QQActionImpl(activity, appId);
    }

    public static Tencent buildTencent(Context context, String appId) {
        return Tencent.createInstance(appId, context.getApplicationContext());
    }

    public static String getDefaultScope() {
        return SCOPE_ALL;
    }


    /**
     * @param keyType   必填	Int	分享的类型。图文分享(普通分享)填Tencent.SHARE_TO_QQ_TYPE_DEFAULT
     * @param title     必填 分享的标题, 最长30个字符
     * @param targetUrl 必填	String	这条分享消息被好友点击后的跳转URL。
     * @return
     */
    private static Bundle buildShareBundle(int keyType, String title, String targetUrl) {

        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, keyType);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        return params;
    }

    /**
     * {@link #buildShareBundle(int, String, String)}
     *
     * @param showQZoneShare 可选	Int	分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
     *                       true - QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
     *                       false -QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
     * @return
     */
    private static Bundle buildShareBundle(int keyType, String title, String targetUrl, boolean showQZoneShare) {
        final Bundle params = buildShareBundle(keyType, title, targetUrl);
        if (showQZoneShare) {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        }
        return params;
    }

    private static Bundle buildShareBundle(int shareType, String title, String targetUrl, boolean showQZoneShare, @Nullable String summary, @Nullable String imageUrl, @Nullable String appName) {

        final Bundle params = buildShareBundle(shareType, title, targetUrl, showQZoneShare);
        if (!TextUtils.isEmpty(summary))
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        if (!TextUtils.isEmpty(imageUrl))
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        if (!TextUtils.isEmpty(appName))
            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        return params;
    }

    /**
     * @param title          必填 分享的标题, 最长30个字符
     * @param targetUrl      必填	String	这条分享消息被好友点击后的跳转URL。
     * @param showQZoneShare 可选	Int	分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
     *                       true - QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
     *                       false -QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
     * @param summary        可选	分享的消息摘要，最长40个字。
     * @param imageUrl       可选 分享图片的URL或者本地路径
     * @param appName        可选 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @return
     */
    public static Bundle buildShareBundle(String title, String targetUrl, boolean showQZoneShare, @Nullable String summary, @Nullable String imageUrl, @Nullable String appName) {
        return buildShareBundle(QQShare.SHARE_TO_QQ_TYPE_DEFAULT, title, targetUrl, showQZoneShare, summary, imageUrl, appName);
    }

    /**
     * @param audioUrl       必填 音乐文件的远程链接, 以URL的形式传入, 不支持本地音乐。
     * @param title          必填 分享的标题, 最长30个字符
     * @param targetUrl      必填	String	这条分享消息被好友点击后的跳转URL。
     * @param showQZoneShare 可选	Int	分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
     *                       true - QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
     *                       false -QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
     * @param summary        可选	分享的消息摘要，最长40个字。
     * @param imageUrl       可选 分享图片的URL或者本地路径
     * @param appName        可选 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @return
     */
    public static Bundle buildMusicShareBundle(
            String audioUrl, String title, String targetUrl, boolean showQZoneShare,
            @Nullable String summary, @Nullable String imageUrl, @Nullable String appName) {
        final Bundle params = buildShareBundle(QQShare.SHARE_TO_QQ_TYPE_AUDIO, title, targetUrl, showQZoneShare, summary, imageUrl, appName);
        params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioUrl);
        return params;
    }

    /**
     * QQ分享-APP
     *
     * @param title          必填 分享的标题, 最长30个字符
     * @param targetUrl      必填	String	这条分享消息被好友点击后的跳转URL。
     * @param showQZoneShare 可选	Int	分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
     *                       true - QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
     *                       false -QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
     * @param summary        可选	分享的消息摘要，最长40个字。
     * @param imageUrl       可选 分享图片的URL或者本地路径
     * @param appName        可选 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @return
     */
    public static Bundle buildAppShareBundle(String title, String targetUrl, boolean showQZoneShare,
                                             @Nullable String summary, @Nullable String imageUrl, @Nullable String appName) {
        return buildShareBundle(QQShare.SHARE_TO_QQ_TYPE_APP, title, targetUrl, showQZoneShare, summary, imageUrl, appName);
    }

    /**
     * @param imagePath      必填 图片本地路径
     * @param showQZoneShare 可选	Int	分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
     *                       true - QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
     *                       false -QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮
     * @param appName        可选 手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     * @return
     */
    public static Bundle buildImageShareBundle(String imagePath, boolean showQZoneShare, @Nullable String appName) {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imagePath);
        if (showQZoneShare) {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        }
        if (!TextUtils.isEmpty(appName))
            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);

        return params;
    }

    /**
     * @param keyType   必填	Int	分享的类型。图文分享(普通分享)填Tencent.SHARE_TO_QQ_TYPE_DEFAULT
     * @param title     必填 分享的标题, 最长30个字符
     * @param targetUrl 必填	String	这条分享消息被好友点击后的跳转URL。
     * @return
     */
    private static Bundle buildQZoneShareBundle(int keyType, String title, String targetUrl) {

        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, keyType);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        return params;
    }


    /* *
    * QQ空间分享 - 图文分享
    * @param title
    * @param targetUrl
    * @param summary
    * @param imgList
    * @return
    */
    public static Bundle buildQZoneShareImageTextBundle(String title, String targetUrl, @Nullable String summary, ArrayList<String> imgList) {

        if (imgList == null || imgList.size() == 0) {
            throw new IllegalArgumentException("图片地址不能为空。");
        }

        if (imgList.size() > 9)
            throw new IllegalArgumentException("最多分享9张图片");


        Bundle params = buildQZoneShareBundle(QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT, title, targetUrl);
        if (!TextUtils.isEmpty(summary))
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary);//选填
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgList);
        return params;
    }

}
