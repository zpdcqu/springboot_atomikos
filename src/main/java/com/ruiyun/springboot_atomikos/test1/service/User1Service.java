package com.ruiyun.springboot_atomikos.test1.service;

import com.ruiyun.springboot_atomikos.test1.dao.User1Dao;
import com.ruiyun.springboot_atomikos.test2.dao.User2Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1Service {
    @Autowired
    public User1Dao user1Dao;
    @Autowired
    public User2Dao user2Dao;
    @Transactional
    public void insert(String username,int age) {
        user1Dao.insert(username, age);
    }

    @Transactional
    public void addAll(String username, int age) {
        user2Dao.insert(username,age);

        int a =  1/0;
        user1Dao.insert(username,age);
    }

}
