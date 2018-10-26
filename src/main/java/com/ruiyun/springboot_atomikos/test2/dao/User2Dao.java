package com.ruiyun.springboot_atomikos.test2.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface User2Dao {
    @Insert("insert into user values(null,#{username},#{age})")
    public void insert(@Param("username") String username, @Param("age") int age);
}
