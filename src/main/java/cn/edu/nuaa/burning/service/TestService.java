package cn.edu.nuaa.burning.service;

import org.springframework.data.domain.Slice;

import cn.edu.nuaa.burning.model.Test;

/**
 * Created by yuchuan.
 */
public interface TestService {
    Test get();

    Slice<Test> getAll();
}
