package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class OrderDTO {
    String orderId;
    int total;
    Date date;
    int totalDiscount;
}
