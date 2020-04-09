package com.stan.event.service;

import com.stan.event.event.UserRegisterEvent;
import com.stan.event.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author：stanzeng
 * @Description：
 * @Date ：Created in 2020/4/9 2:57 下午
 * @Modified By：
 */
@Service
public class UserRegisterService {

    @Resource
    ApplicationContext applicationContext;

    public void register(User user){
        System.out.println("UserRegisterService,用户注册：" + user.getName() + " ," + user.getEmail());

        applicationContext.publishEvent(new UserRegisterEvent(this, user));
    }
}
