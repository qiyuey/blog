package cn.edu.nuaa.burning.exception;

/**
 * qiyuey on 2016/4/8.
 */
public class InvalidInputException extends BaseException {

    public InvalidInputException() {
        super(100, "请求输入之一无效。", 400);
    }
}
