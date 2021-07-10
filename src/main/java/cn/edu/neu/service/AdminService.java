package cn.edu.neu.service;

public interface AdminService {

    /**
     * 超级管理员登录
     * @param username 用户名
     * @param password  密码
     * @return 是否登陆成功
     */
    public boolean login(String username, String password);
}
