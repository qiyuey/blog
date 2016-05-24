package cn.edu.nuaa.burning.exception;

import lombok.Getter;

/**
 * qiyuey on 2016/4/8.
 */
@Getter
public class BaseException extends RuntimeException {//运行时异常

    private int id;
    private String content;
    private int httpStatusCode;

    BaseException(int id, String content, int httpStatusCode) {
        this.id = id;//this 对象，它可以在类里面来引用这个类的属性和方法
        this.content = content;
        this.httpStatusCode = httpStatusCode;
    }
}
