package org.example.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ProductTm {
    String productId;
    String productName;
    String nickName;
    int cost;

    int discount;
    int qty;
    int orderQty;
}
