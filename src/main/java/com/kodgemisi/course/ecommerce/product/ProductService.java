package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.Category;
import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void saveAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product save(ProductDto productDto) {
        Category category = categoryService.findById(productDto.getCategoryId());
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .stock(productDto.getStock())
                .price(productDto.getPrice())
                .url(productDto.getUrl())
                .creationDate(LocalDate.now())
                .category(category)
                .build();
        this.save(product);
        return product;
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow(() -> {
            log.error("product not found by id: {}", id);
            return new ResourceNotFoundException();
        });
    }

}
