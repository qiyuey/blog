package cn.edu.nuaa.burning.util;

import cn.edu.nuaa.burning.exception.NotLoginException;
import cn.edu.nuaa.burning.exception.PermissionException;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限验证工具类
 * @author qiyuey
 */
public abstract class PermissionUtils {

    /**
     * 检查session中的id是否合法
     * @param request request
     * @param id 期望id
     */
    public static void check(HttpServletRequest request, String id) {
        String sessionId = (String) request.getSession().getAttribute("id");
        if (sessionId == null) {
            throw new NotLoginException();
        } else if (!sessionId.equals(id)) {
            throw new PermissionException();
        }
    }

    public static String findId(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute("id");
        if (sessionId == null) {
            throw new NotLoginException();
        }
        return sessionId;
    }

    public static Boolean checkAdmin(HttpServletRequest request) {
        return (Boolean) request.getSession().getAttribute("admin");
    }
}
