package com.kodgemisi.course.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String filter(Model model, Pageable pageable, ProductFilterDto productFilterDto) {
        Page<Product> products = productService.filter(pageable, productFilterDto);
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product/show";
    }

}
