package com.jason.mybatis;


import com.jason.mybatis.enums.UserSexEnum;
import com.jason.mybatis.mapper.UserMapper;
import com.jason.mybatis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.server.ExportException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired  // TODO: 报错??
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception{
        userMapper.insert(new User("aa1", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bb1", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("cc1", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws ExportException{
        List<User> users = userMapper.getAll();
        System.out.println(users.toString());
    }

    @Test
    public void testUpdate() throws ExportException{
        User user = userMapper.getOne(6L);
        System.out.println(user.toString());
        user.setNickName("zouzou");
        userMapper.update(user);
        Assert.assertTrue("zouzou".equals(userMapper.getOne(6L).getNickName()));
    }

}
