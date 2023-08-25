package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(ProductDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(ProductDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public ProductDTO search(String  id) throws SQLException, ClassNotFoundException;

    public boolean exist(String  id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
