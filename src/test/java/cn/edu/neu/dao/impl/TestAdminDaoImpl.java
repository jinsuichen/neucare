package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.AdminDao;
import org.junit.Assert;
import org.junit.Test;


public class TestAdminDaoImpl {

    AdminDao dao = new AdminDaoImpl();

    @Test
    public void TestFindUserByUsername(){

        System.out.println(dao.findUserByUsername("admin1"));

    }
}
