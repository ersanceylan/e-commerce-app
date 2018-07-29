package com.kodgemisi.course.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void saveAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

}
