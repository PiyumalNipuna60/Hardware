package org.example.dao.custome.impl;

import org.example.dao.SqlUtil;
import org.example.dao.custome.CustomerDAO;
import org.example.dao.custome.EmployeeDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> AllEmployee = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Employee");
        while (resultSet.next()){
            AllEmployee.add(
                    new EmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    ));
        }
        return AllEmployee;
    }

    @Override
    public boolean Save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into Employee values(?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getSalary()
                );
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Employee SET empName=?,address=?,contact=?,salary=? WHERE empId=?",
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getSalary(),
                dto.getId()
                );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Employee WHERE empId=?", s);
    }

    @Override
    public EmployeeDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Employee WHERE empId=?", s);
        EmployeeDTO employeeDTO=null;
        if (resultSet.next()){
            employeeDTO=new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
        }
        return employeeDTO;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT empId FROM Employee WHERE empId=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
