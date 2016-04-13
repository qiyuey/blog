package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/9.
 */
public class ResourceNotFoundException extends BaseException {

    private static final long serialVersionUID = -7495933202664764663L;

    public ResourceNotFoundException() {
        super(103, "指定的资源不存在。", 404);
    }
}
