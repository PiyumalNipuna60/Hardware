package org.example.dao.custome;

import org.example.dao.CrudDAO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.entity.OrderDetails;

public interface OrderDetailsDAO extends CrudDAO<OrderDetailsDTO,String> {
}
