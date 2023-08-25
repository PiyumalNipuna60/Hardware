package org.example.bo.custom.impl;

import org.example.bo.custom.ProductBO;
import org.example.dao.custome.impl.ProductDAOImpl;
import org.example.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.getAll();
    }

    @Override
    public boolean Save(ProductDTO dto) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.Save(dto);
    }

    @Override
    public boolean update(ProductDTO dto) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.delete(id);
    }

    @Override
    public ProductDTO search(String id) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.search(id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        return productDAO.generateNewId();
    }
}
