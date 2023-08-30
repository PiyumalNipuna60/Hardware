package org.example.dao.custome.impl;

import com.sun.org.apache.regexp.internal.RE;
import org.example.dao.SqlUtil;
import org.example.dao.custome.CustomerDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> AllCustomer = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Customer");
        while (resultSet.next()){
            AllCustomer.add(
                    new CustomerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    ));
        }
        return AllCustomer;
    }

    @Override
    public boolean Save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into Customer values(?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact()
                );
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Customer SET cusName=?,address=?,contact=? WHERE cusId=?",
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getId()
                );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Customer WHERE cusId=?", s);
    }

    @Override
    public CustomerDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Customer WHERE cusId=?", s);
         CustomerDTO customerDTO=null;
        if (resultSet.next()){
            customerDTO=new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        }
        return customerDTO;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT id FROM Customer WHERE cusId=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
