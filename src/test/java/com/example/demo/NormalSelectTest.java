package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NormalSelectTest {

    @Autowired
    private UserMapper userMapper;

    /*
     * 描述：例1.单表查询所有记录
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-16
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelect() {
        System.out.println(("----- 2.1 单表查询所有记录------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(6, userList.size()); //表里面的记录总条数是否等于6，如果等于6，则测试通过
        userList.forEach(System.out::println);
    }

    /*
     * 描述：例2.单表根据主键id查询单条记录
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-16
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectById() {
        System.out.println(("----- 单表根据主键id查询单条记录 ------"));
        User user = userMapper.selectById(2);
        System.out.println(user);
    }

    /*
     * 描述：例3.单表根据 id list 批量查询
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-16
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByIds() {
        System.out.println(("----- 单表根据 id list 批量查询 ------"));
        List<Long> idsList = Arrays.asList(2L,4L,6L);
        List<User> userList= userMapper.selectBatchIds(idsList);
        userList.forEach(System.out::println);
    }

    /*
     * 描述：例4.单表根据条件查询
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-19
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByMap() {
        System.out.println(("----- 单表根据条件查询 ------"));
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("name", "Jack");
        conditions.put("age", 20);
        List<User> userList= userMapper.selectByMap(conditions);
        userList.forEach(System.out::println);
    }

}