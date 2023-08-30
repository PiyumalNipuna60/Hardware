package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.dao.custome.impl.CustomerDAOImpl;
import org.example.dao.custome.impl.OrderDAOImpl;
import org.example.dto.CustomerDTO;
import org.example.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    public final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean Save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.Save(dto);
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }
}
