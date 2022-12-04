package j.service;

import j.model.Order;

import java.util.List;

public interface OrderServiceInterface {
    List<Order> getAllOrders();
    // __________________________________________ بررسی نشده
    Order putOrderForThisCustomerOnThisProduct(Long ProductId , Long CustomerId);
    Order addOrder(Order order);

    List<Order> getAllOrdersOfThisCustomerId(Long idMoshtari);
//    List<Order> findAllOrdersForStdId(Customer st);
//    List<Order> findAllOrders(String orderName);
}
