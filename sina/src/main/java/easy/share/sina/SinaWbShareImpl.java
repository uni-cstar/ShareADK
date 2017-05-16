package easy.share.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

/**
 * Created by Lucio on 17/5/16.
 */

class SinaWbShareImpl implements ISinaWbShare, WbShareCallback {

    WbShareCallback mCallBack;
    WbShareHandler mShareHandler;
    Context mContext;

    public SinaWbShareImpl(Activity activity) {
        mShareHandler = new WbShareHandler(activity);
        mShareHandler.registerApp();
        mContext = activity;
    }

    @Override
    public void onNewIntent(Intent it) {
        mShareHandler.doResultIntent(it, this);
    }

    @Override
    public void shareMessage(WeiboMultiMessage msg, WbShareCallback callback) {
        mCallBack = callback;
        try {
            mShareHandler.shareMessage(msg, false);
        } catch (Exception e) {
            e.printStackTrace();
            if (mCallBack != null) {
                mCallBack.onWbShareFail();
            }
            mCallBack = null;
        }

    }

    @Override
    public void shareText(String text, WbShareCallback callback) {
        WeiboMultiMessage msg = new WeiboMultiMessage();
        msg.textObject = SinaWb.buildTextObj(text);
        shareMessage(msg, callback);
    }

    @Override
    public void shareImageText(String text, ImageObject imageObject, WbShareCallback callback) {
        WeiboMultiMessage msg = new WeiboMultiMessage();
        msg.textObject = SinaWb.buildTextObj(text);
        msg.imageObject = imageObject;
        shareMessage(msg, callback);
    }

    @Override
    public void shareWebPageObj(String title, String desc, String targetUrl, String defaultText, Bitmap thumbBmp, WbShareCallback callback) {
        WeiboMultiMessage msg = new WeiboMultiMessage();
        msg.mediaObject = SinaWb.buildWebPageObj(title, desc, targetUrl, defaultText, thumbBmp);
        shareMessage(msg, callback);
    }

    @Override
    public void shareWebPageObj(String title, String desc, String targetUrl, String defaultText, byte[] thumbData, WbShareCallback callback) {
        WeiboMultiMessage msg = new WeiboMultiMessage();
        msg.mediaObject = SinaWb.buildWebPageObj(title, desc, targetUrl, defaultText, thumbData);
        shareMessage(msg, callback);
    }


    @Override
    public void onWbShareSuccess() {
        if (mCallBack != null) {
            mCallBack.onWbShareSuccess();
            mCallBack = null;
        } else {
            Toast.makeText(mContext, "分享成功.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWbShareCancel() {
        if (mCallBack != null) {
            mCallBack.onWbShareCancel();
            mCallBack = null;
        } else {
            Toast.makeText(mContext, "取消分享.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWbShareFail() {
        if (mCallBack != null) {
            mCallBack.onWbShareFail();
            mCallBack = null;
        } else {
            Toast.makeText(mContext, "分享失败.", Toast.LENGTH_SHORT).show();
        }
    }
}
