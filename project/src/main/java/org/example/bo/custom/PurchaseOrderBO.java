package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.OrderDTO;

import java.sql.SQLException;

public interface PurchaseOrderBO extends SuperBO {
    public boolean purchaseOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
