package j.service;

import j.model.Order;
import j.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderServiceInterface {
    // _________________________________________________ fields
    OrderRepo orderRepo;
    CustomerServiceInterface customerServiceInterface;
    ProductServiceInterface productServiceInterface;

    @Autowired
    public OrderServiceImplementation(OrderRepo orderRepo, CustomerServiceInterface customerServiceInterface, ProductServiceInterface productServiceInterface) {
        this.orderRepo = orderRepo;
        this.customerServiceInterface = customerServiceInterface;
        this.productServiceInterface = productServiceInterface;
    }

    public OrderServiceImplementation() {
    }

    // _________________________________________________
    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order putOrderForThisCustomerOnThisProduct(Long productId, Long customerId) {
        Order order = new Order();
        order.setCustomer(customerServiceInterface.getCustomerById(customerId));
        order.setProduct(productServiceInterface.getProductById(productId));
        return orderRepo.save(order);
    }


    @Override
    public List<Order> getAllOrdersOfThisCustomerId(Long idMoshtari) {
        return orderRepo.findAllByCustomer(customerServiceInterface.getCustomerById(idMoshtari));
    }
//
//    @Override
//    public List<Order> findAllOrdersForStdId(Customer st) {
//        return orderRepo.findAllByCustomer(st);
//    }
//
//    @Override
//    public List<Order> findAllOrders(String orderName) {
//        return orderRepo.findAllByOrderName(orderName);
//    }


}
