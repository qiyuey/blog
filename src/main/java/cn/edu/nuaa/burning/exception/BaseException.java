package cn.edu.nuaa.burning.exception;

import lombok.Getter;

/**
 * qiyuey on 2016/4/8.
 */
@Getter
public class BaseException extends RuntimeException {

    private int id;
    private String content;
    private int httpStatusCode;

    BaseException(int id, String content, int httpStatusCode) {
        this.id = id;
        this.content = content;
        this.httpStatusCode = httpStatusCode;
    }
}
