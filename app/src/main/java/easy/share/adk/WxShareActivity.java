package easy.share.adk;

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

import easy.share.wx.WxAuth;

public class WxShareActivity extends AppCompatActivity implements IWXAPIEventHandler {

    ImageView imgv1, imgv2, imgv3, imgv4;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx4e4431394178d80d", false);
        api.registerApp("wx4e4431394178d80d");
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
//        ShareCompat.sendReq(this, WxShare.buildTextShareReq("nihao",WxShare.SendToSession),"wx4e4431394178d80d");

        PayReq payReq = WxAuth.WxPay.buildPayReq("wx4e4431394178d80d", "1248812801", "wx20170511231543ebb3dcb7980994220904",
                "Sign=WXPay", "msCr7rRJBIcre7T3DtSxigi85gh8A7gS", "1494515746", "C659BF2DB60BF1EB0DAF64499DACC877", WxShareActivity.class);
//        try {
//            WxPay.senReq(this,"wx4e4431394178d80d",payReq);
//            ShareCompat.sendPayReq(this, payReq, WxShareActivity.class.getName());
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
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
        Log.d("WxShareActivity", baseResp.toString());
    }
}
