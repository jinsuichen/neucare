package cn.edu.neu.dao;

import cn.edu.neu.pojo.Admin;
import cn.edu.neu.pojo.Employee;

public interface AdminDao {

    /**
     * 根据用户名查找超级管理员信息
     * @param username 用户名
     * @return 查询得到的Employee对象。当用户不存在时返回null。
     */
    public Admin finUserByUsername(String username);

}
