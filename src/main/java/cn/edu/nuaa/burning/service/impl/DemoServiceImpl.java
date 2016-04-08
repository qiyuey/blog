package cn.edu.nuaa.burning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.repository.DemoRepository;
import cn.edu.nuaa.burning.service.DemoService;

/**
 * Created by yuchuan.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public Demo addDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    @Override
    public Demo getDemo(String id) {
        return demoRepository.findById(id);
    }

    @Override
    public Slice<Demo> getDemoSlice(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        return demoRepository.findAll(pageable);
    }

    @Override
    public Demo updateDemo(Demo demo) {
        return demoRepository.save(demo);
    }
}
