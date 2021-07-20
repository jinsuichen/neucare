package cn.edu.neu.service;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface EmployeeService {

    /**
     * 普通员工登录
     * @param username 用户名
     * @param password  密码
     * @return 是否登陆成功
     */
    public boolean login(String username, String password);


    /**
     * 查找所有员工
     * @return 员工集合
     */
    public List<Employee> getAllEmployees();

    /**
     * 根据员工ID查找员工
     * @param eid 员工ID
     * @return 员工
     */
    public Employee getEmployeeByEid(int eid);

    /**
     * 根据员工的姓名模糊查询员工
     * @param keyword 关键词
     * @return 员工集合
     */
    public List<Employee> fuzzyQueryEmployees(String keyword);

    /**
     * 根据员工类型查询所有员工
     * @return 员工集合
     * @param employeeType 员工类型
     */
    public List<Employee> getEmployeesByType(EmployeeType employeeType);

    /**
     * 根据员工的ID删除员工
     * @param eid 员工的ID
     * @return 是否删除成功
     */
    public boolean deleteEmployeeByEid(int eid);

    /**
     * 添加员工
     * @param employee 员工
     * @return 是否添加成功
     */
    public boolean addEmployee(Employee employee);
}
