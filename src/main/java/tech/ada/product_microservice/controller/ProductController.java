package tech.ada.product_microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(this.productService.allProducts());
    }

    //GET BY ID
    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable Long sku) {
        return ResponseEntity.ok(this.productService.getProductBySku(sku));
    }

    //POST - CREATE
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
    }

    //PUT - UPDATE ALL
    @PostMapping("/{sku}")
    public ResponseEntity<Product> update(@PathVariable Long sku,
                                          @RequestBody Product product) {
        Product updated = this.productService.update(sku, product);
        return ResponseEntity.ok(updated);
    }

    //PATCH - PARTIAL UPDATE
    @PatchMapping("/{sku}")
    public ResponseEntity<Product> partialUpdate(@PathVariable Long sku,
                                                 @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.partialUpdate(sku, product));
    }

    //DELETE - REMOVE
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable Long sku) {
        this.productService.delete(sku);
        return ResponseEntity.noContent().build();
    }

}
