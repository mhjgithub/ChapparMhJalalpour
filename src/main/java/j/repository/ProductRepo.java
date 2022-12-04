package j.repository;

import j.model.Order;
import j.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Order, Long> {
void save(Product product);
    Optional<Product> findByidKala(Long idKala);
}
