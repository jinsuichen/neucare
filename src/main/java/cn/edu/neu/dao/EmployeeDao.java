package cn.edu.neu.dao;

import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;

import java.util.List;

public interface EmployeeDao {


    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return 查询得到的Employee对象。当用户不存在时返回null。
     */
    public Employee queryEmployeeByUsername(String username);

    /**
     * 根据员工ID查找员工
     * @param eid 员工ID
     * @return 员工
     */
    public Employee queryEmployeeByEid(int eid);

    /**
     * 根据员工类型查询所有员工
     * @return 员工集合
     * @param employeeType 员工类型
     */
    public List<Employee> queryEmployeesByType(EmployeeType employeeType);

    /**
     * 查找所有员工
     * @return 员工集合
     */
    public List<Employee> queryAllEmployees();

    /**
     * 根据员工的姓名模糊查询员工
     * @param keyword 关键词
     * @return 员工集合
     */
    public List<Employee> fuzzyQueryEmployees(String keyword);

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
    public  boolean createEmployee(Employee employee);

}
