package com.kodgemisi.course.ecommerce.dashboard;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class DashboardController {

    private final ProductService productService;

    @GetMapping
    public String dashboard(Model model, Pageable pageable) {
        Page<Product> products = productService.findAllByEnabledTrue(pageable);
        model.addAttribute("products", products);
        return "dashboard/index";
    }

}
