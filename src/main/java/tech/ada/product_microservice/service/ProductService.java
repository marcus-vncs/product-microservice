package tech.ada.product_microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.repository.ProductRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Product partialUpdate(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);

        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        return this.productRepository.save(product);
    }

    public Product updateProduct(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long sku) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        this.productRepository.delete(productBySku);
    }

    public Product update(Long sku, Product product) {
        Product Updating = this.getProductBySku(sku);
        // Não permitir alteração do sku
        if (Objects.nonNull(product.getSku()) && !product.getSku().equals(Updating.getSku())) {
            throw new RuntimeException("Nao eh permitido alterar o sku");
        }
        // Substitui os campos (exceto sku)
        Updating.setDescription(product.getDescription());
        Updating.setPrice(product.getPrice());
        int idx = this.allProducts().indexOf(Updating);
        this.allProducts().set(idx, Updating);
        return Updating;
    }

    public void delete(Long sku) {
        Product deleting = this.getProductBySku(sku);
        this.allProducts().remove(deleting);
    }
}
