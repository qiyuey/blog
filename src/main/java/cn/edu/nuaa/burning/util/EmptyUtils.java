package cn.edu.nuaa.burning.util;

import java.util.Objects;

/**
 * 初始值为空处理工具类
 * @author qiyuey
 */
public abstract class EmptyUtils {

    /**
     * Integer类为空转化
     * @param integer 传入值
     * @return 返回值
     */
    public static Integer convert(Integer integer) {
        if (integer == null) {
            return 0;
        } else {
            return integer;
        }
    }

    /**
     * String类为空转化
     * @param string 传入值
     * @return 返回值
     */
    public static String convert(String string) {
        if (string == null) {
            return "";
        } else {
            return string;
        }
    }

    /**
     * 判断字符串是否为空
     * @param string 传入值
     * @return 判断结果
     */
    public static boolean check(String string) {
        return string == null || Objects.equals(string, "");
    }
}
