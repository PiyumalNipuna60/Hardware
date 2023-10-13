package org.example.dao.custome;

import org.example.dao.CrudDAO;
import org.example.dao.SqlUtil;
import org.example.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<ProductDTO,String> {
    public ArrayList<ProductDTO> getAllByName(String name, String nickName) throws SQLException, ClassNotFoundException;

    public boolean updateQty(String id,int qty) throws SQLException, ClassNotFoundException;
}
