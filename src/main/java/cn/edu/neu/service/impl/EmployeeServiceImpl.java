package cn.edu.neu.service.impl;

import cn.edu.neu.dao.EmployeeDao;
import cn.edu.neu.dao.impl.EmployeeDaoImpl;
import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();

    /**
     * 普通员工登录
     * @param username 用户名
     * @param password  密码
     * @return 是否登陆成功
     */
    @Override
    public boolean login(String username, String password) {
        Employee employee = dao.queryEmployeeByUsername(username);
        return employee != null && employee.getPassword().equals(password);
    }

    /**
     * 查找所有员工
     * @return 员工集合
     */
    @Override
    public List<Employee> getAllEmployees() {
        return dao.queryAllEmployees();
    }


    /**
     * 根据员工ID查找员工
     * @param eid 员工ID
     * @return 员工
     */
    @Override
    public Employee getEmployeeByEid(int eid) {
        return dao.queryEmployeeByEid(eid);
    }


    /**
     * 根据员工的姓名模糊查询员工
     * @param keyword 关键词
     * @return 员工集合
     */
    @Override
    public List<Employee> fuzzyQueryEmployees(String keyword) {
        return dao.fuzzyQueryEmployees(keyword);
    }


    /**
     * 根据员工类型查询所有员工
     * @return 员工集合
     * @param employeeType 员工类型
     */
    @Override
    public List<Employee> getEmployeesByType(EmployeeType employeeType) {
        return dao.queryEmployeesByType(employeeType);
    }


    /**
     * 根据员工的ID删除员工
     * @param eid 员工的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteEmployeeByEid(int eid) {
        return dao.deleteEmployeeByEid(eid);
    }

    /**
     * 添加员工
     * @param employee 员工
     * @return 是否添加成功
     */
    @Override
    public boolean addEmployee(Employee employee) {
        return dao.createEmployee(employee);
    }


}
