package cn.edu.nuaa.burning.service;

import org.springframework.data.domain.Slice;

import cn.edu.nuaa.burning.entity.Demo;

/**
 * Created by yuchuan.
 */
public interface DemoService {

    Demo get();

    Slice<Demo> getAll();
}
