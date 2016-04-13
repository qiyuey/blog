package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/8.
 */
public class RepositoryException extends BaseException {

    private static final long serialVersionUID = -6867770689834480504L;

    public RepositoryException() {
        super(101, "未知错误。", 500);
    }
}
