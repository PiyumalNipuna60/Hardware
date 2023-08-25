package org.example.bo.custom.impl;

import org.example.bo.custom.OrderBO;
import org.example.dao.custome.impl.OrderDAOImpl;
import org.example.dto.OrderDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.getAll();
    }

    @Override
    public boolean Save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.Save(dto);
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.search(id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.generateNewId();
    }
}
