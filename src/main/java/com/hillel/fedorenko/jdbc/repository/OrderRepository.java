package com.hillel.fedorenko.jdbc.repository;

import com.hillel.fedorenko.jdbc.connection.StoreConnection;
import com.hillel.fedorenko.jdbc.entity.Order;
import com.hillel.fedorenko.jdbc.entity.Product;
import com.hillel.fedorenko.jdbc.entity.ProductInOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class OrderRepository extends BaseRepository<Order> {
    public Order getById(int orderId) {
        return super.getById(orderId, GET_ORDER_BY_ID, resultSet -> {
            List<ProductInOrder> productInOrderList = new ArrayList<>();
            productInOrderList.add(new ProductInOrder(new Product(resultSet.getInt("id_product"),
                    resultSet.getString("name"), resultSet.getString("description"),
                    resultSet.getDouble("price")),
                    resultSet.getInt("product_count")));
            while (resultSet.next()) {
                productInOrderList.add(new ProductInOrder(new Product(resultSet.getInt("id_product"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        resultSet.getDouble("price")),
                        resultSet.getInt("product_count")));
            }
            resultSet.previous();
            return new Order(resultSet.getInt("id_order"), resultSet.getDate("order_date"),
                    productInOrderList);
        });
    }

    public List<Order> getOrdersIdByMaxPriceAndDistinctProducts(double maxSum, int countOfDistinct) {
        List<Order> orderList = new ArrayList<>();
        ArrayList<Integer> ordersId = new ArrayList<>();

        Connection connection = StoreConnection.provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ORDER_BY_MAX_PRICE_AND_DISTINCT_PRODUCTS)) {
                preparedStatement.setDouble(1, maxSum);
                preparedStatement.setInt(2, countOfDistinct);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ordersId.add(resultSet.getInt("id_order"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("SQLException");
            } finally {
                StoreConnection.closeConnection(connection);
            }
        }

        for (Integer id :
                ordersId) {
            orderList.add(getById(id));
        }
        return orderList;
    }

    public List<Order> getOrdersByProductPresent(String sql, String productName) {
        List<Order> orderList = new ArrayList<>();
        ArrayList<Integer> ordersId = new ArrayList<>();

        Connection connection = StoreConnection.provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(sql)) {
                preparedStatement.setString(1, productName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ordersId.add(resultSet.getInt("id_order"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("SQLException");
            } finally {
                StoreConnection.closeConnection(connection);
            }
        }
        for (Integer id :
                ordersId) {
            orderList.add(getById(id));
        }
        return orderList;
    }

    public int createOrderFromProductsOrderedToday() {
        int result = StoreConnection.update(UPDATE_ORDER_PRODUCT_TABLE);
        if (result > 0) {
            StoreConnection.update(UPDATE_ORDER_DATE_TABLE);
        }
        return result;
    }

    public int deleteOrderByAmountOfProductName(String productName, int amount) {
        Connection connection = StoreConnection.provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_BY_AMOUNT_OF_PRODUCT_NAME)) {
                statement.setString(1, productName);
                statement.setInt(2, amount);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                StoreConnection.closeConnection(connection);
            }
        }
        return 0;
    }

    public int getMaxOrderId() {
        Connection connection = StoreConnection.provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_MAX_ORDER_ID)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("SQLException");
            } finally {
                StoreConnection.closeConnection(connection);
            }
        }
        return 0;
    }
}
