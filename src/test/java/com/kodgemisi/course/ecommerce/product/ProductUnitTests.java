package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.CategoryService;
import com.kodgemisi.course.ecommerce.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class ProductUnitTests {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        this.productService = new ProductService(productRepository, categoryService);
    }

    @Test
    public void addStockSuccessfully() throws Exception {
        Product product = new Product();
        product.setStock(50);

        int newStock = productService.addStock(product, 20);

        assertEquals(newStock, (50 + 20));
    }

    @Test(expected = Exception.class)
    public void addStockAndExpectException() throws Exception {
        Product product = new Product();
        product.setStock(50);

        int newStock = productService.addStock(product, (-20));
    }

    @Test
    public void findProductById() {
        Product product = new Product();
        product.setId(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product persistentProduct = productService.findById(1L);

        assertEquals(persistentProduct.getId(), product.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findProductByIdAndExpectException() {
        Product product = new Product();
        product.setId(1L);
        Mockito.when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        productService.findById(1L);
    }

}
