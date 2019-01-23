package halo.android.integration.wx;

/**
 * Created by Lucio on 17/5/11.
 */

public class WxNotInstalledException extends Exception{
    public WxNotInstalledException() {
        super();
    }

    public WxNotInstalledException(String detailMessage) {
        super(detailMessage);
    }

    public WxNotInstalledException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WxNotInstalledException(Throwable throwable) {
        super(throwable);
    }
}
