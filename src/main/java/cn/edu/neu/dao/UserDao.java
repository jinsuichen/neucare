package cn.edu.neu.dao;

import cn.edu.neu.pojo.Employee;

public interface UserDao {


    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return 查询得到的Employee对象。当用户不存在时返回null。
     */
    public Employee finUserByUsername(String username);



}
