package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/8.
 */
public class RepositoryException extends BaseException {

    public RepositoryException() {
        super(101, "未知错误。", 500);
    }
}
