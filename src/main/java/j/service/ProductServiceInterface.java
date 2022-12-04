package j.service;

import j.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ProductServiceInterface {
    void putProduct(Product product);

    void deleteAllProducts();

    Product getProductById(Long idMoshtari);

}
