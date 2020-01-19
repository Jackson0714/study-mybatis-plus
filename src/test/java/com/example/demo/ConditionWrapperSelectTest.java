package com.example.demo;

import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionWrapperSelectTest {

    @Autowired
    private UserMapper userMapper;

    /*
     * 描述：例5.
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-19
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByMap() {

    }
}
