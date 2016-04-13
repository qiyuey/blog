package cn.edu.nuaa.burning.exception;

import cn.edu.nuaa.burning.domain.response.ExceptionResp;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * qiyuey on 2016/4/9.
 */
@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException exception) {
        ExceptionResp exceptionResp = new ExceptionResp(exception);
        return Response.status(exceptionResp.getHttpStatusCode()).entity(exceptionResp).build();
    }
}
