package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;
import org.example.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String  id) throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String  id) throws SQLException, ClassNotFoundException;

    public boolean exist(String  id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
