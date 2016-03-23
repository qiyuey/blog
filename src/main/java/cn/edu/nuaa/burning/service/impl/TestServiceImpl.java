package cn.edu.nuaa.burning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
        test.setName("测试");
        String id = testDao.save(test).getId();
        return testDao.findTestById(id);
    }
}
