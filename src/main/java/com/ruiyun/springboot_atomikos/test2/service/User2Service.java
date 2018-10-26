package com.ruiyun.springboot_atomikos.test2.service;


import com.ruiyun.springboot_atomikos.test2.dao.User2Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User2Service {
    @Autowired
    public User2Dao user2Dao;

    public void insert(String username,int age) {
        user2Dao.insert(username, age);
    }
}
