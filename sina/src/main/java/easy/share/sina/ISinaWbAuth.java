package easy.share.sina;

import android.content.Intent;

/**
 * Created by Lucio on 17/5/16.
 */

public interface ISinaWbAuth {

    /**
     * 微博登陆需要
     * 在{@link android.app.Activity#onActivityResult(int, int, Intent)}中调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);


    /**
     * 获取微博凭证
     */
    void getSinaWbAuth(SinaWbAuthListener listener);

    /**
     * 请求其它OpenApi
     * OpenApi openApi已经不在微博sdk中维护，如果你想使用OpenApi,请参考开发者网站使用自己的网络接入
     * 地址：http://open.weibo.com/wiki/微博API
     */
    void requestOpenApi();
}
