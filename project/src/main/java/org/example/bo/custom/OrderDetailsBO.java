package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String  id) throws SQLException, ClassNotFoundException;

    public OrderDetailsDTO search(String  id) throws SQLException, ClassNotFoundException;

    public boolean exist(String  id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
