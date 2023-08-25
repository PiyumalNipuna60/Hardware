package org.example.dao.custome.impl;

import com.sun.org.apache.regexp.internal.RE;
import org.example.dao.SqlUtil;
import org.example.dao.custome.ProductDAO;
import org.example.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> AllProduct = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Product");
        while (resultSet.next()) {
            AllProduct.add(
                    new ProductDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getDate(7)));
        }
        return AllProduct;
    }

    @Override
    public boolean Save(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return  SqlUtil.executeUpdate("insert into Product values(?,?,?,?,?,?,?)",
                dto.getProductId(),
                dto.getProductName(),
                dto.getNickName(),
                dto.getState(),
                dto.getCost(),
                dto.getQty(),
                dto.getAddedDate()
                );
    }

    @Override
    public boolean update(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Product SET productName=?,nickName=?,state=?, cost=?, qty=?, addedDate=? WHERE productId=?",
                dto.getProductName(),
                dto.getNickName(),
                dto.getState(),
                dto.getCost(),
                dto.getQty(),
                dto.getAddedDate(),
                dto.getProductId()
                );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Product WHERE productId=?", s);
    }

    @Override
    public ProductDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Product WHERE productId=?", s);
        ProductDTO product1=null;
        if (resultSet.next()){
            product1 = new ProductDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getDate(7));
        }
        return product1;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT id FROM Product WHERE productId=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
