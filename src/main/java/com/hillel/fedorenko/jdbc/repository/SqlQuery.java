package com.hillel.fedorenko.jdbc.repository;

public class SqlQuery {
    public static final String GET_ORDER_BY_ID = """
            SELECT *
            FROM store.order
            LEFT JOIN store.order_products ON store.order.id = store.order_products.id_order
            LEFT JOIN product ON store.order_products.id_product = store.product.id
            WHERE id_order = ?
            ORDER BY id_product
            """;
    public static final String GET_ORDER_BY_MAX_PRICE_AND_DISTINCT_PRODUCTS = """
            SELECT store.order_products.id_order,\s
            SUM(price * product_count) AS sum_cost,\s
            COUNT(DISTINCT store.order_products.id_product) AS products_count
            FROM store.order
            JOIN store.order_products ON store.order.id = store.order_products.id_order
            JOIN product ON id_product = product.id
            GROUP BY id_order
            HAVING sum_cost <= ? AND products_count = ?
            """;
    public static final String GET_ORDER_ID_BY_PRODUCT_PRESENT = """
            SELECT id_order
            FROM store.order_products
            JOIN store.product ON store.order_products.id_product = store.product.id
            WHERE store.product.name = ?
            """;
    public static final String GET_ORDERS_BY_PRODUCT_NOT_PRESENT_TODAY = """
            SELECT store.order_products.id_order
            FROM order_products
            JOIN `order` ON id_order = id
            WHERE order_date LIKE CURDATE()
              AND id_order NOT IN (SELECT id_order FROM order_products
                  JOIN product ON id_product = product.id WHERE product.name = ?)
            GROUP BY id_order
            """;
    public static final String DELETE_ORDER_BY_AMOUNT_OF_PRODUCT_NAME = """
            DELETE store.`order`, store.order_products
            FROM store.order
            JOIN store.order_products ON store.order.id = store.order_products.id_order
            WHERE id_order IN (SELECT id_order FROM (SELECT store.order_products.id_order FROM store.order_products
                JOIN product ON order_products.id_product = product.id
                WHERE product.name = ? AND order_products.product_count = ?) AS ids);
            """;
    public static final String UPDATE_ORDER_PRODUCT_TABLE = """
            INSERT INTO order_products
            SELECT MAX(store.`order`.id) + 1 AS order_id, order_products.id_product, order_products.product_count AS product_count
            FROM order_products, `order`
            WHERE order_products.id_order IN (SELECT DISTINCT store.order.id FROM store.order
                               WHERE store.order.order_date LIKE Curdate())
            GROUP BY id_product, product_count ORDER BY id_product;
            """;
    public static final String UPDATE_ORDER_DATE_TABLE = """
            INSERT INTO store.order(id, order_date)
            VALUES ((SELECT MAX(id) + 1 FROM store.order), Curdate())
            """;
    public static final String GET_MAX_ORDER_ID = """
            SELECT MAX(store.order.id) as id
            FROM store.order
            """;
}
