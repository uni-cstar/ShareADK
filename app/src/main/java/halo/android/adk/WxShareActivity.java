package halo.android.adk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import halo.android.share.wx.WxNotInstalledException;
import halo.android.share.wx.WxPay;

public class WxShareActivity extends AppCompatActivity implements IWXAPIEventHandler {

    ImageView imgv1, imgv2, imgv3, imgv4;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx52d54b3771fb5594", false);
        api.registerApp("wx52d54b3771fb5594");
        api.handleIntent(getIntent(), this);

        setContentView(R.layout.activity_wx_share);
        imgv1 = (ImageView) this.findViewById(R.id.image1);
        imgv2 = (ImageView) this.findViewById(R.id.image2);
        imgv3 = (ImageView) this.findViewById(R.id.image3);
        imgv4 = (ImageView) this.findViewById(R.id.image4);

        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.wx_img);
        Bitmap bmpCps = Bitmap.createScaledBitmap(bmp, 250, 250, true);

        int width = bmp.getWidth();
        int height = bmp.getHeight();

        imgv1.setImageBitmap(bmp);
        imgv2.setImageBitmap(bmpCps);

        BitmapDrawable bd = new BitmapDrawable(getResources(), bmp);
        BitmapDrawable bdCps = new BitmapDrawable(getResources(), bmpCps);

        imgv3.setBackgroundDrawable(bd);
        imgv4.setBackgroundDrawable(bdCps);

        if (i > 0)
            return;
        i++;
//        ShareCompat.sendReq(this, WxShare.buildTextShareReq("nihao",WxShare.SendToSession),"wx52d54b3771fb5594");

        PayReq payReq = WxPay.buildPayReq("wx52d54b3771fb5594", "1486542682", "wx20170805232015860a8024ee0033299295",
                "Sign=WXPay", "tCFT3aACYJD4h0Zd", "1501946415", "16C0EE6C6A065627748D8BAEAC495BE5", WxShareActivity.class);
        try {
            WxPay.senPayReq(this,"wx52d54b3771fb5594",payReq);
//            ShareCompat.sendPayReq(this, payReq, WxShareActivity.class.getName());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("WxShareActivity", baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d("WxShareActivity", baseResp.errCode + baseResp.errStr);
    }
}
