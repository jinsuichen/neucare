package cn.edu.neu.service;

public interface EmployeeService {

    /**
     * 普通员工登录
     * @param username 用户名
     * @param password  密码
     * @return 是否登陆成功
     */
    public boolean login(String username, String password);
}
