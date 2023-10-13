package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.entity.tm.PlaceOrderTm;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class OrderDTO {
    String orderId;
    int total;
    String date;

    public OrderDTO(String orderId, int total, String date, int totalDiscount) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
        this.totalDiscount = totalDiscount;
    }

    int totalDiscount;
    List<PlaceOrderTm> orderDetails;
}
