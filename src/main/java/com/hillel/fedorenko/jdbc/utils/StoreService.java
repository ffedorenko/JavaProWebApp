package com.hillel.fedorenko.jdbc.utils;

import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.repository.OrderRepository;
import com.hillel.fedorenko.jdbc.repository.SqlQuery;

import java.util.List;

public class StoreService {
    OrderRepository orderRepository;

    public StoreService() {
        orderRepository = new OrderRepository();
    }

    public Order getOrderById(int orderId) {
        return orderRepository.getById(orderId);
    }

    public List<Order> infoByMaxSumAndCountOfDistinct(double maxSum, int countOfDistinct) {
        return orderRepository.getOrdersIdByMaxPriceAndDistinctProducts(maxSum, countOfDistinct);
    }

    public List<Order> getOrdersByProductPresent(String productName) {
        return orderRepository.getOrdersByProductPresent(SqlQuery.GET_ORDER_ID_BY_PRODUCT_PRESENT, productName);
    }

    public List<Order> getOrdersByProductNotPresentToday(String productName) {
        return orderRepository.getOrdersByProductPresent(SqlQuery.GET_ORDERS_BY_PRODUCT_NOT_PRESENT_TODAY, productName);
    }

    public int createOrderFromProductsOrderedToday() {
        return orderRepository.createOrderFromProductsOrderedToday();
    }

    public Order getOrderByMaxId() {
        return orderRepository.getById(orderRepository.getMaxOrderId());
    }

    public int deleteOrderByAmountOfProductName(String productName, int amount) {
        return orderRepository.deleteOrderByAmountOfProductName(productName, amount);
    }
}
