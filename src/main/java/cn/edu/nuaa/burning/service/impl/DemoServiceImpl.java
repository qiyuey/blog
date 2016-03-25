package cn.edu.nuaa.burning.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import cn.edu.nuaa.burning.repository.DemoRepository;
import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.service.DemoService;

/**
 * Created by yuchuan.
 * pomelo test
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public Demo get() {
        Demo demo = new Demo();
        demo.setName(new Date().toString());
        Long id = demoRepository.save(demo).getId();
        return demoRepository.findById(id);
    }

    @Override
    public Slice<Demo> getAll() {
        Pageable pageable = new PageRequest(0, 10);
        return demoRepository.findAll(pageable);
    }

}
