package cn.edu.nuaa.burning.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import cn.edu.nuaa.burning.entity.Demo;

/**
 * Created by yuchuan.
 */
public interface DemoService {

    Demo addDemo(Demo demo);

    Demo getDemo(String id);

    Slice<Demo> getDemoSlice(Pageable pageable);

    Demo updateDemo(Demo demo);
}
