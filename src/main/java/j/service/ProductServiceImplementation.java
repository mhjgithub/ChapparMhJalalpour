package j.service;

import j.model.Product;
import j.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImplementation implements ProductServiceInterface {

    ProductRepo productRepo;

    @Autowired
    public ProductServiceImplementation(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductServiceImplementation() {
    }

    // _______________________________
    @Override
    public void putProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteAllProducts() {
        productRepo.deleteAll();
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> thisProduct = productRepo.findByidKala(productId);
        if (thisProduct.isPresent()) {
            return thisProduct.get();
        } else {
            throw new RuntimeException();
        }
    }
}
