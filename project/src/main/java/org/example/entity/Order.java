package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class Order {
    String orderId;
    int total;
    Date date;
    int totalDiscount;
}
