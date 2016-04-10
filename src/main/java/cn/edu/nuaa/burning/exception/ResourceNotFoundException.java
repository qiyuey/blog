package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/9.
 */
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(103, "指定的资源不存在。", 404);
    }
}
