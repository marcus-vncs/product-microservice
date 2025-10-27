package tech.ada.product_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.product_microservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(Long sku);
}
