package cn.edu.neu.service.impl;

import cn.edu.neu.dao.AdminDao;
import cn.edu.neu.dao.impl.AdminDaoImpl;
import cn.edu.neu.pojo.Admin;
import cn.edu.neu.service.AdminService;

public class AdminServiceImpl implements AdminService {

    AdminDao dao = new AdminDaoImpl();

    @Override
    public boolean login(String username, String password) {
        Admin admin = dao.queryAdminByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }
}
