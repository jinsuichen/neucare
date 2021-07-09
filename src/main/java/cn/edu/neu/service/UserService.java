package cn.edu.neu.service;

import cn.edu.neu.pojo.Employee;

public interface UserService {

    /**
     * 普通用户登录
     * @param username 用户名
     * @param password  密码
     * @return 是否登陆成功
     */
    public boolean login(String username, String password);


}
