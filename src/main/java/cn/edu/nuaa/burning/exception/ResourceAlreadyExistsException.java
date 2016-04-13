package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/9.
 */
public class ResourceAlreadyExistsException extends BaseException {

    private static final long serialVersionUID = 136028355489507276L;

    public ResourceAlreadyExistsException() {
        super(102, "指定的资源已存在。", 409);
    }
}
