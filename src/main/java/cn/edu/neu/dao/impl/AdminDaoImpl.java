package cn.edu.neu.dao.impl;

import cn.edu.neu.commom.Algorithm;
import cn.edu.neu.dao.AdminDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Admin;

import java.util.Collections;

public class AdminDaoImpl implements AdminDao {


    /**
     * 根据用户名查找超级管理员信息
     * @param username 用户名
     * @return 查询得到的Admin对象。当用户不存在时返回null。
     */
    @Override
    public Admin queryUserByUsername(String username) {

        Collections.sort(DataBase.adminData);
        int index = Algorithm.binarySearch(DataBase.adminData, new Admin(username));
        if(index == -1) {
            return null;
        }else {
            return DataBase.adminData.get(index);
        }

    }


}
