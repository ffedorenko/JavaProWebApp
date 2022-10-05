package com.hillel.fedorenko.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductInOrder {
    private Product product;
    private int amount;

	public String toString() {
        return product.toString() + ", amount: " + amount + ";\n";
    }
}
