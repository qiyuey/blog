package cn.edu.nuaa.burning.exception;

/**
 * Created by yuchuan.
 */
public class NotLoginException extends BaseException {

    private static final long serialVersionUID = -5801673218731482299L;

    public NotLoginException() {
        super(105, "用户未登录。", 401);
    }
}
