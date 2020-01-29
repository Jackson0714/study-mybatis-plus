package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionWrapperSelectTest {

    @Autowired
    private UserMapper userMapper;

    /*
     * 描述：例2.1 查询名字中包含“Ja”并且年龄小于30的用户
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-20
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByQueryWrapper() {
        System.out.println(("----- 查询名字中包含“Ja”并且年龄小于30的用户------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "ja").lt("age", 30);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
     * 描述：例1.2 查询名字中包含“a”并且年龄大于等于15且年龄小于等于35，且email不为空
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-20
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByQueryWrapper2() {
        System.out.println(("----- 查询名字中包含“a”并且年龄大于等于15且年龄小于等于25，且email不为空------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.like("name", "a").ge("age", 15).le("age", 25).isNotNull("email");
        queryWrapper.like("name", "a").between("age", 15,25).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
     * 描述：例1.3 查询名字中“J”开头并且年龄大于26，按照年龄降序排列，年龄相同按照id升序排列
     * SQL语句：name LIKE 'J%' or age > 26 ORDER BY age desc, id ASC
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-20
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByQueryWrapper3() {
        System.out.println(("----- 查询名字中包含“a”并且年龄大于26，按照年龄降序排列，年龄相同按照id升序排列 ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name","J").or().gt("age",26).orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /*
     * 描述：例1.4 查询创建日期为2020年1月15日并且直属上级的名字为“J”开头的
     * SQL语句方案一：SELECT * FROM demo.user where date_format(create_time, '%Y-%m-%d') ='2020-01-15' AND manager_id in (select id from user where name like 'J%');
     * SQL语句方案二：SELECT user1.* FROM demo.user AS user1 INNER JOIN demo.user AS user2 ON user1.manager_id = user2.id WHERE date_format(user1.create_time, '%Y-%m-%d') ='2020-01-15' AND user2.name LIKE 'J%'
     * 作者：博客园-悟空聊架构
     * 时间：2019-01-29
     * Github：https://github.com/Jackson0714/study-mybatis-plus.git
     * 博客园：https://www.cnblogs.com/jackson0714
     * */
    @Test
    public void testSelectByQueryWrapper4() {
        System.out.println(("----- 查询创建日期为2020年1月15日并且直属上级的名字为“J”开头的 ------"));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.apply("date_format(create_time, '%Y-%m-%d')='2020-01-15' or true or true")
        queryWrapper.apply("date_format(create_time, '%Y-%m-%d')={0}","2020-01-15")
                .inSql("manager_id", "select id from user where name like 'J%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
}
