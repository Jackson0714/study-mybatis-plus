package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTest {

    @Autowired
    private UserMapper userMapper;

    /*
    * 描述：单表查询所有记录
    * 作者：博客园-悟空聊架构
    * 时间：2019-01-16
    * */
    @Test
    public void testSelect() {
        System.out.println(("----- 查询所有记录------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(6, userList.size()); //表里面的记录总条数是否等于6，如果等于6，则测试通过
        userList.forEach(System.out::println);
    }
}