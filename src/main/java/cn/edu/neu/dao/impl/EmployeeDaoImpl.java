package cn.edu.neu.dao.impl;

import cn.edu.neu.commom.Algorithm;
import cn.edu.neu.dao.EmployeeDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Admin;
import cn.edu.neu.pojo.Employee;
import cn.edu.neu.service.EmployeeService;

import java.util.Collections;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee queryUserByUsername(String username) {

        Collections.sort(DataBase.employeeData);
        int index = Algorithm.binarySearch(DataBase.employeeData, new Employee(username));
        if (index == -1) {
            return null;
        } else {
            return DataBase.employeeData.get(index);
        }
    }
}
