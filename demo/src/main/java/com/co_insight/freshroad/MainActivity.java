package com.co_insight.freshroad;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.utils.Log;

import andmy.integration.wx.WxNotInstalledException;
import andmy.integration.wx.WxShare;
import andmy.integration.wx.iml.WxShareResponseListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            WxShare.addShareListener(new WxShareResponseListener() {
                @Override
                public void onWxCallBackError(Activity activity, int code, String msg) {
                    Log.d("WXShare","onWxCallBackError:"+activity +"\ncode="+code + " msg="+msg);
                    showToast("分享失败 code=" + code + "msg=" + msg);

                }

                @Override
                public void onWxShareSuccess(Activity activity, SendMessageToWX.Resp resp) {
                    Log.d("WXShare","onWxShareSuccess:"+ activity + " "+resp.toString());
                    showToast("分享成功");

                }
            });

            SendMessageToWX.Req req = WxShare.buildWebPageShare("http://www.baidu.com", "测试分享", "测试描述", BitmapFactory.decodeResource(this.getResources(), R.drawable.wx_img), true, WxShare.ShareTo.SESSION);
            WxShare.senReq(this, "wx89b05d2c974cf4f4", req);
        } catch (WxNotInstalledException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
