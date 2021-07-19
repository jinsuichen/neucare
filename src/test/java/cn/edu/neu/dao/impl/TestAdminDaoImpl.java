package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.AdminDao;
import junit.framework.TestCase;


public class TestAdminDaoImpl extends TestCase {

    AdminDao dao = new AdminDaoImpl();

    public void testTestFindUserByUsername(){

        System.out.println(dao.queryAdminByUsername("admin1"));

    }
}
