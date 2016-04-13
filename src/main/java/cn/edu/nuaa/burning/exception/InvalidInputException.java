package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/8.
 */
public class InvalidInputException extends BaseException {

    private static final long serialVersionUID = -8506536163702307630L;

    public InvalidInputException() {
        super(100, "请求输入之一无效。", 400);
    }
}
