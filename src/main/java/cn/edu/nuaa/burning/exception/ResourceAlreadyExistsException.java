package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/9.
 */
public class ResourceAlreadyExistsException extends BaseException {

    public ResourceAlreadyExistsException() {
        super(102, "指定的资源已存在。", 409);
    }
}
