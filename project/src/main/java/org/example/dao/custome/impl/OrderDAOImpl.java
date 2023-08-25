package org.example.dao.custome.impl;

import org.example.dao.SqlUtil;
import org.example.dao.custome.OrderDAO;
import org.example.dto.OrderDTO;
import org.example.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> AllOrder = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Orders");
        while (resultSet.next()){
            AllOrder.add(
                    new OrderDTO(
                            resultSet.getString(1),
                            resultSet.getInt(2),
                            resultSet.getDate(3),
                            resultSet.getInt(4)
                    ));
        }
        return AllOrder;
    }

    @Override
    public boolean Save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into Orders values(?,?,?,?)",
                dto.getOrderId(),
                dto.getTotal(),
                dto.getDate(),
                dto.getTotalDiscount());
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Orders SET total=?,date=?,totalDiscount=? WHERE orderId=?",
                dto.getTotal(),
                dto.getDate(),
                dto.getTotalDiscount(),
                dto.getOrderId()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Orders WHERE orderId=?", s);
    }

    @Override
    public OrderDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Orders WHERE orderId=?", s);
        OrderDTO orderDTO1=null;
        if (resultSet.next()){
            orderDTO1 = new OrderDTO(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDate(3),
                    resultSet.getInt(4));
        }
        return orderDTO1;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT id FROM Orders WHERE orderId=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
