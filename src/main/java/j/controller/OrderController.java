package j.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import j.model.Order;
import j.service.OrderServiceInterface;
import j.service.CustomerServiceInterface;
import j.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    // _______________________________________________
    OrderServiceInterface orderServiceInterface;
    CustomerServiceInterface customerServiceInterface;
    ProductServiceInterface productServiceInterface;

    @Autowired
    public OrderController(OrderServiceInterface orderServiceInterface, CustomerServiceInterface customerServiceInterface, ProductServiceInterface productServiceInterface) {
        this.orderServiceInterface = orderServiceInterface;
        this.customerServiceInterface = customerServiceInterface;
        this.productServiceInterface = productServiceInterface;
    }

    public OrderController() {
    }

    // _______________________________________________ Get
    @GetMapping("/getAllOrders")
    private List<Order> getAllOrders() {
        return orderServiceInterface.getAllOrders();
    }

//    @GetMapping("/getOrderByName/{orderName}")
//    private ResponseEntity getOrderByName(@PathVariable("orderName") String orderName) {
//        List<Order> result = orderServiceInterface.findAllOrders(orderName);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("فهرست سفارش‌ها با این نام: " + result.toString()); //todo
//    }

//    @GetMapping("/getSample")
//    private Order getSample() {
//        Customer st = customerServiceInterface.getCustomer(36L); // new Customer(1L, "Mostafa", 25)
//        Order l = new Order();
//        l.setCustomer(st);
//        l.setOrderName("جغرافی۱۱");
//        return l;
////        l.setOrderName();
////        return new Order(2L,"سفارش جغرافیا ۱۴" , st);
//    }

    @GetMapping("/getAllOrdersOfThisCustomerId/{idMoshtari}")
    private ResponseEntity getAllOrdersOfThisCustomerId(@PathVariable("idMoshtari") Long idMoshtari) {
        List<Order> orders = orderServiceInterface.getAllOrdersOfThisCustomerId(idMoshtari);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("این سفارش‌ها برای مشتری‌ای به شماره "
                + idMoshtari + "\nموجود است:" + orders.toArray().toString());
    }

    // _______________________________________________ Put
//    @PutMapping("/putOneOrder")
//    private String putOneOrder(@RequestBody Order order) {
//        Order lt = ls.addOrder(order);
//        return "سفارشی با این مشخصات افزوده شد:" + lt.toString() + "******";
//    }
// این توضیحات بالای یکی از  ای‌پی‌آی هایی که دوست داریم می‌آید
    @ApiOperation(value = "این ای‌پی‌آی یک سفارش را برای یک مشتری اضافه می‌کند") // این بند توضیح می‌دهد که ای‌پی‌آی ما چه کار انجام می‌دهد
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "کد دویست و یک: کار به درستی انجام شد!"), // به مخاطب سواگر می‌گوید که اگر کد دویست و یک آمد معنی‌اش این است
            @ApiResponse(code = 401, message = "کد چهارصد و یک : شما اجازه ندارید"),
            @ApiResponse(code = 409, message = "کد چهارصد و نه: تکراری است"),
            @ApiResponse(code = 500, message = " کد پانصد: ارور سرور")
    })
    @PutMapping("/putOrderForThisCustomerOnThisProduct/{productId}/{customerId}")
    private ResponseEntity putOrderForThisCustomerOnThisProduct(@PathVariable("orderName") Long productId, @PathVariable("customerId") Long customerId) {
        Order lt = orderServiceInterface.putOrderForThisCustomerOnThisProduct(productId, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body("این سفارش برای این مشتری ساخته شد" + lt.toString() + "******");
    }
}
