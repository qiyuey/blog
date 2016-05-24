package cn.edu.nuaa.burning.exception;

import cn.edu.nuaa.burning.domain.response.ExceptionResp;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * qiyuey on 2016/4/9.
 */
@Provider  //其中一个功能就是为了处理异常
public class BaseExceptionMapper implements ExceptionMapper<BaseException> { //BaseException代表了mapper能处理的异常的类型

    @Override
    public Response toResponse(BaseException exception) {//所有异常都会进入这里
        ExceptionResp exceptionResp = new ExceptionResp(exception);//新建一个异常响应
        return Response.status(exceptionResp.getHttpStatusCode()).entity(exceptionResp).build();//f返回状态码，http用来表示状态
        //1开头：继续等待 2：成功 3：重定向，资源改到其他地方了 4：客户端请求错误  5：错误，查找失败
    }
}
