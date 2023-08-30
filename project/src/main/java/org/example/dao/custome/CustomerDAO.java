package org.example.dao.custome;

import org.example.dao.CrudDAO;
import org.example.dao.SuperDAO;
import org.example.dto.CustomerDTO;

public interface CustomerDAO extends CrudDAO<CustomerDTO, String> {
}
