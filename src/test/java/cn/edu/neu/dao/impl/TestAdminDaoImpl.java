package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.AdminDao;
import junit.framework.TestCase;
import org.junit.Test;


public class TestAdminDaoImpl extends TestCase {

    AdminDao dao = new AdminDaoImpl();

    public void testTestFindUserByUsername(){

        System.out.println(dao.queryUserByUsername("admin1"));

    }
}
