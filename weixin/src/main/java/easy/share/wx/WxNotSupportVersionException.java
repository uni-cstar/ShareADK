package easy.share.wx;

/**
 * Created by Lucio on 17/5/11.
 */

public class WxNotSupportVersionException extends Exception{
    public WxNotSupportVersionException() {
        super();
    }

    public WxNotSupportVersionException(String detailMessage) {
        super(detailMessage);
    }

    public WxNotSupportVersionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WxNotSupportVersionException(Throwable throwable) {
        super(throwable);
    }
}
