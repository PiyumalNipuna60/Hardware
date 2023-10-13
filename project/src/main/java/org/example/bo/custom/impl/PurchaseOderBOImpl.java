package org.example.bo.custom.impl;

import org.example.bo.custom.PurchaseOrderBO;
import org.example.dao.custome.OrderDAO;
import org.example.dao.custome.OrderDetailsDAO;
import org.example.dao.custome.ProductDAO;
import org.example.dao.custome.impl.OrderDAOImpl;
import org.example.dao.custome.impl.OrderDetailsDAOImpl;
import org.example.dao.custome.impl.ProductDAOImpl;
import org.example.db.DBConnection;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.dto.ProductDTO;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.entity.tm.PlaceOrderTm;

import java.sql.Connection;
import java.sql.SQLException;

public class PurchaseOderBOImpl implements PurchaseOrderBO {

    OrderDAO orderDAO=new OrderDAOImpl();

    OrderDetailsDAO orderDetailsDAO=new OrderDetailsDAOImpl();

    ProductDAO productDAO=new ProductDAOImpl();
    @Override
    public boolean purchaseOrder(OrderDTO orderDTO) {

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = orderDAO.Save(new OrderDTO(orderDTO.getOrderId(),orderDTO.getTotal(),orderDTO.getDate(),orderDTO.getTotalDiscount()));

            if (!b1){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (PlaceOrderTm d:orderDTO.getOrderDetails()){
                OrderDetailsDTO orderDetails=new OrderDetailsDTO(d.getProductId(), orderDTO.getOrderId(), d.getDiscount(),d.getQty());
                boolean b2 = orderDetailsDAO.Save(orderDetails);

                if (!b2){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                ProductDTO productDTO = productDAO.search(d.getProductId());
                int newQty=productDTO.getQty()-d.getQty();
                boolean b3 = productDAO.updateQty(d.getProductId(), newQty);

                if(!b3){
                    connection.rollback();
                    connection.setAutoCommit(false);
                    return false;
                }


                //update Item
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
