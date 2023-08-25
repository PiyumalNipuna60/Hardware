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
public class Invoice {
    String invoiceId;
    String cusId;
    String total;
    String totalDiscount;
    Date invoiceDate;
}
