package com.hillel.fedorenko.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private int id;
    private Date date;
    private List<ProductInOrder> productList;

    public String getDisplay() {
        return toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Order: id - " + id + ", Date: " + date + "\n");
        for (ProductInOrder product:
             productList) {
            str.append(product.toString());
        }
        return str.toString();
    }
}
