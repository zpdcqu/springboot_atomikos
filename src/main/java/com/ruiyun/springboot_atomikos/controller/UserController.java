package com.ruiyun.springboot_atomikos.controller;

import com.ruiyun.springboot_atomikos.test1.service.User1Service;
import com.ruiyun.springboot_atomikos.test2.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*"com.ruiyun.springboot_atomikos.atomikosconfig","com.ruiyun.springboot_atomikos.config"
注意扫描包路径添加正确，否则会启动失败，缺少bean
*/

@SpringBootApplication
@ComponentScan(basePackages={"com.ruiyun.springboot_atomikos.atomikosconfig","com.ruiyun.springboot_atomikos.config","com.ruiyun.springboot_atomikos.test1","com.ruiyun.springboot_atomikos.test2"})
@RestController
public class UserController {
    @Autowired
    public User1Service user1Service;
    @Autowired
    public User2Service user2Service;
    @RequestMapping("/add")
    public String insert(String username,int age) {
        user1Service.insert(username, age);
        user2Service.insert(username, age);
        return "insert success";
    }
    @RequestMapping("/addUser1AndUser2")
    public String addUser1AndUser2(String username,int age) {
        try {
        //    user1Service.addUser1AndUser2(username, age);
        } catch (Exception e) {
            e.printStackTrace();
            return "insert failure";
        }

        return "insert success";
    }

    @RequestMapping("/addAll")
    public String addUser1andUser2(String username ,int age) {
        try {
            user1Service.addAll(username,age);

        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        return "1";

    }
    public static void main(String[] args) {
        SpringApplication.run(UserController.class, args);
    }
}
