package org.example.entity.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class PlaceOrderTm {
    int id;
    String productId;
    String productName;
    String nickName;
    int cost;
    int discount;
    int qty;
}
