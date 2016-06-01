package cn.edu.nuaa.burning.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author qiyuey
 */
public abstract class PageUtils {

    public static Pageable doCheck(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        return pageable;
    }
}
