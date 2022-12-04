package j.repository;

import j.model.Customer;
import j.model.Order;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
    // __________________________________________ بررسی نشده
    Optional<Order> findByOrderId(Long orderId);

    List<Order> findAllByCustomer(Customer st);

    List<Order> findAll();

    List<Order> findAllByOrderName(String orderName);
}
