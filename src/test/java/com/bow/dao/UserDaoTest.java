package com.bow.dao;

import com.bow.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author vv
 * @since 2017/3/1.
 */
public class UserDaoTest {

    private UserDao dao;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-mybatis.xml");
        dao = context.getBean(UserDao.class);
    }

    @Test
    public void getUser() throws Exception {
        User user = dao.getUser(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void getUser2() throws Exception {
        List<User> users = dao.getUserByName("vv2");
        System.out.println(users.size());
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("vv");
        dao.addUser(user);
    }

    @Test
    public void addBatchUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername("vv" + i);
            users.add(user);
        }
        dao.addBatchUsers(users);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3L);
        user.setUsername("william");
        dao.updateUser(user);
    }

    @Test
    public void updateUser2() {
        User user = new User();
        user.setId(3L);
        user.setUsername(null);
        user.setLocked(null);
        boolean flag = false;
        try{
            dao.updateUser(user);
        }catch (Exception e){
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void deleteUser(){
        dao.deleteUser(3L);
    }

}