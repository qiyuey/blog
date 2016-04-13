package cn.edu.nuaa.burning.exception;

/**
 * Created by yuchuan.
 */
public class PermissionException extends BaseException {

    private static final long serialVersionUID = -1160081947016192561L;

    public PermissionException() {
        super(104, "当前用户没有权限。", 403);
    }
}
