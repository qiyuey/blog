package cn.edu.nuaa.burning.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import cn.edu.nuaa.burning.dao.TestDao;
import cn.edu.nuaa.burning.model.Test;
import cn.edu.nuaa.burning.service.TestService;

/**
 * Created by yuchuan.
 * pomelo test
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public Test get() {
        Test test = new Test();
        test.setName(new Date().toString());
        String id = testDao.save(test).getId();
        return testDao.findById(id);
    }

    @Override
    public Slice<Test> getAll() {
        Pageable pageable = new PageRequest(0, 10);
        return testDao.findAll(pageable);
    }

}
