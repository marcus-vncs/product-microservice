package tech.ada.product_microservice.repository;

import org.springframework.stereotype.Component;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product(100L, "Tv Samsung 100`", new BigDecimal(3500)));
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product findBySku(Long sku) {
        return this.products.stream()
                .filter(product -> sku.equals(product.getSku()))
                .findFirst().orElse(null);
    }

    public Product save(Product product) {
        this.products.add(product);
        return product;
    }

    public boolean deleteBySku(Long sku) {
        return this.products.removeIf(p -> sku.equals(p.getSku()));
    }
}
