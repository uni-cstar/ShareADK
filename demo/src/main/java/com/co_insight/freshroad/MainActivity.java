package com.co_insight.freshroad;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import halo.android.integration.wx.WxShare;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WxShare.addShareListener(new WxShareResponseListener() {
//            @Override
//            public void onWxShareSuccess(SendMessageToWX.Resp resp) {
//                showToast("分享成功");
//            }
//
//            @Override
//            public void onWxCallBackError(int code, String msg) {
//                showToast("分享失败 code=" + code + "msg=" + msg);
//            }
//        });

        SendMessageToWX.Req req = WxShare.buildWebPageShare("http://www.baidu.com","测试分享","测试描述",
                BitmapFactory.decodeResource(this.getResources(),R.drawable.wx_img),true,WxShare.ShareTo.SESSION);
//        try {
//            WxShare.senReq(this,"wx89b05d2c974cf4f4",req);
//        } catch (WxNotInstalledException e) {
//            e.printStackTrace();
//        } catch (WxNotSupportVersionException e) {
//            e.printStackTrace();
//        }
    }

    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
