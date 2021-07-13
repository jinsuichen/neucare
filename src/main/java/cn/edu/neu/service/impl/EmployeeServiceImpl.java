package cn.edu.neu.service.impl;

import cn.edu.neu.dao.EmployeeDao;
import cn.edu.neu.dao.impl.EmployeeDaoImpl;
import cn.edu.neu.pojo.Employee;
import cn.edu.neu.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Override
    public boolean login(String username, String password) {
        Employee employee = dao.queryUserByUsername(username);
        return employee != null && employee.getPassword().equals(password);
    }
}
