package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.bo.custom.EmployeeBO;
import org.example.dao.custome.impl.CustomerDAOImpl;
import org.example.dao.custome.impl.EmployeeDAOImpl;
import org.example.dto.CustomerDTO;
import org.example.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    public final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean Save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.Save(dto);
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewId();
    }
}
