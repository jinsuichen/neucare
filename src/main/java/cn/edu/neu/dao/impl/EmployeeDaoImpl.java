package cn.edu.neu.dao.impl;

import cn.edu.neu.commom.Algorithm;
import cn.edu.neu.dao.EmployeeDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Employee;
import cn.edu.neu.pojo.EmployeeType;
import cn.edu.neu.pojo.Patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class EmployeeDaoImpl implements EmployeeDao {

    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return 查询得到的Employee对象。当用户不存在时返回null。
     */
    @Override
    public Employee queryEmployeeByUsername(String username) {

        //TODO 取消算法 并考虑isDeleted
        Collections.sort(DataBase.employeeData);
        int index = Algorithm.binarySearch(DataBase.employeeData, new Employee(username));
        if (index == -1) {
            return null;
        } else {
            return DataBase.employeeData.get(index);
        }
    }

    /**
     * 根据员工类型查询所有员工
     * @return 员工集合
     * @param employeeType 员工类型
     */
    @Override
    public List<Employee> queryEmployeesByType(EmployeeType employeeType) {
        List<Employee> list = new ArrayList<>();
        for(Employee e : DataBase.employeeData){
            if(!e.isDeleted() && e.getType().equals(employeeType)){
                list.add(e);
            }
        }
        return list;

    }

    /**
     * 查找所有员工
     * @return 员工集合
     */
    @Override
    public List<Employee> queryAllEmployees() {
        List<Employee> list = new ArrayList<>();
        for(Employee e : DataBase.employeeData){
            if(!e.isDeleted()){
                list.add(e);
            }
        }
        return list;
    }


    /**
     * 根据员工的姓名模糊查询员工
     * @param keyword 关键词
     * @return 员工集合
     */
    @Override
    public List<Employee> fuzzyQueryEmployees(String keyword) {
        List<Employee> list = new ArrayList<>();
        for(Employee employee : DataBase.employeeData){
            if(!employee.isDeleted() && employee.getName().contains(keyword)){
                list.add(employee);
            }
        }
        return list;
    }

    /**
     * 根据员工的ID删除员工
     * @param eid 员工的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteEmployeeByEid(int eid) {
        for(Employee e : DataBase.employeeData){
            if(!e.isDeleted() && e.getEid() == eid){
                e.setDeleted(true);
                return true;
            }
        }
        return false;
    }

    /**
     * 添加员工
     * @param employee 员工
     * @return 是否添加成功
     */
    @Override
    public boolean createEmployee(Employee employee) {
        if (employee.getUsername() != null && employee.getPassword() != null && (queryEmployeeByUsername(employee.getUsername()) == null)) {
            int maxId = 0;
            for (Employee e : DataBase.employeeData) {
                maxId = max(maxId, e.getEid());
            }
            employee.setEid(maxId + 1);
            employee.setDeleted(false);
            DataBase.employeeData.add(employee);
            return true;
        } else {
            System.err.println("无法正确添加员工");
            return false;
        }
    }
}
