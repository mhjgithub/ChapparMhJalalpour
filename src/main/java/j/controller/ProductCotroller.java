package j.controller;

import j.model.Product;
import j.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductCotroller {
    ProductServiceInterface productServiceInterface;

    @Autowired
    public ProductCotroller(ProductServiceInterface productServiceInterface) {
        this.productServiceInterface = productServiceInterface;
    }

    public ProductCotroller() {
    }

    // _______________________________________________ Put
    @PutMapping("/putProduct")
    private ResponseEntity putProduct(@PathVariable("name") String name) {
        Product product = new Product();
        product.setName(name);
        productServiceInterface.putProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(" محصولی با این مشخصات افزوده شد:" + "\n" + product.toString());
    }
    // _______________________________________________ del
    @DeleteMapping("deleteAllProducts")
    private ResponseEntity deleteAllProducts (){
        deleteAllProducts();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("تابع حذف تمام محصولات اجرا شد.");
    }

}
