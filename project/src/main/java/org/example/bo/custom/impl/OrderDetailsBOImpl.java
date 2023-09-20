package org.example.bo.custom.impl;

import org.example.bo.custom.OrderBO;
import org.example.bo.custom.OrderDetailsBO;
import org.example.dao.custome.impl.OrderDAOImpl;
import org.example.dao.custome.impl.OrderDetailsDAOImpl;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private final  OrderDetailsDAOImpl orderDAO = new OrderDetailsDAOImpl();
    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }

    @Override
    public boolean Save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.Save(dto);
    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDetailsDTO search(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

}
