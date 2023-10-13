package org.example.dao.custome.impl;

import org.example.dao.SqlUtil;
import org.example.dao.custome.OrderDAO;
import org.example.dao.custome.OrderDetailsDAO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> AllOrder = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM OrderDetails");
        while (resultSet.next()){
            AllOrder.add(
                    new OrderDetailsDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                    ));
        }
        return AllOrder;
    }



    @Override
    public boolean Save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into OrderDetails values(?,?,?,?)",
                dto.getProductId(),
                dto.getOrderId(),
                dto.getDiscount(),
                dto.getQtyOnHand());
    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE OrderDetails SET productId=?,discount=?,qtyOnHand=? WHERE orderId=?",
                dto.getProductId(),
                dto.getDiscount(),
                dto.getQtyOnHand(),
                dto.getOrderId()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM OrderDetails WHERE orderId=?", s);
    }

    @Override
    public OrderDetailsDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM OrderDetails WHERE orderId=?", s);
        OrderDetailsDTO orderDTO1=null;
        if (resultSet.next()){
            orderDTO1 = new OrderDetailsDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4));
        }
        return orderDTO1;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT id FROM OrderDetails WHERE orderId=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
