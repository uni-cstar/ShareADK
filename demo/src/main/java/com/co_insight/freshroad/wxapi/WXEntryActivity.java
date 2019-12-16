package com.co_insight.freshroad.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import halo.android.integration.wx.Wx;
import halo.android.integration.wx.iml.IWxEntry;

public class WXEntryActivity extends AppCompatActivity {

    IWxEntry wxEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxEntry = Wx.createWxEntry(this, "wx89b05d2c974cf4f4");
        wxEntry.onActivityCreate();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxEntry.onNewIntent(intent);
    }

}
