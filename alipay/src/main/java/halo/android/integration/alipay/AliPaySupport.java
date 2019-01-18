package halo.android.integration.alipay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Lucio on 2019/1/18.
 */
class AliPaySupport {

    //23版本以下的没有[Build.VERSION_CODES.M]变量
    public static final int M = 23;

    /**
     * 是否需要进行权限处理（目标版本是否是23及以上）
     */
    public static boolean isPermissionTargetVersion(Context ctx) {
        try {
            PackageInfo packInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return packInfo.applicationInfo.targetSdkVersion >= M;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
