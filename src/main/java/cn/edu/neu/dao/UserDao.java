package cn.edu.neu.dao;

import cn.edu.neu.pojo.Employee;

public interface UserDao {


    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     */
    public Employee finUserByUsername(String username);



}
