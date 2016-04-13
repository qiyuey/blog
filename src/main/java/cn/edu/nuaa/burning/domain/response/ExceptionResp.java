package cn.edu.nuaa.burning.domain.response;

import cn.edu.nuaa.burning.exception.BaseException;
import lombok.Data;

/**
 * qiyuey on 2016/4/9.
 */
@Data
public class ExceptionResp {

    private int id;
    private String content;
    private int httpStatusCode;

    public ExceptionResp(BaseException exception) {
        id = exception.getId();
        content = exception.getContent();
        httpStatusCode = exception.getHttpStatusCode();
    }
}
