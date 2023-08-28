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
public class ProductTMI {
    String productId;
    String productName;
    String nickName;
    String state;
    int cost;
    int qty;
    Date addedDate;
}
