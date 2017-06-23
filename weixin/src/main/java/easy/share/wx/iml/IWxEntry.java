package easy.share.wx.iml;

import android.content.Intent;

/**
 * Created by Lucio on 17/5/16.
 */

public interface IWxEntry {

    void onActivityCreate();

    void onNewIntent(Intent intent);
}
