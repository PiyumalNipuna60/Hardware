package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(OrderDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String  id) throws SQLException, ClassNotFoundException;

    public OrderDTO search(String  id) throws SQLException, ClassNotFoundException;

    public boolean exist(String  id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
