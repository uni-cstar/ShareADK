package andmy.integration.wx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import andmy.integration.wx.iml.IWxEntry;


/**
 * Created by Lucio on 17/12/26.
 */
public abstract class BaseWxEntryActivity extends Activity {

    IWxEntry wxEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxEntry = Wx.createWxEntry(this, getWxAppID());
        wxEntry.onActivityCreate();
    }

    protected abstract String getWxAppID();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxEntry.onNewIntent(intent);
    }
}
