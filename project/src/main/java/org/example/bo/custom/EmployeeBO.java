package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;
import org.example.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String  id) throws SQLException, ClassNotFoundException;

    public EmployeeDTO search(String  id) throws SQLException, ClassNotFoundException;

    public boolean exist(String  id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
