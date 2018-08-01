package com.kodgemisi.course.ecommerce.dashboard;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class DashboardController {

    private final ProductService productService;

    @GetMapping
    public String dashboard(Model model, Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        return "dashboard/index";
    }

//    @PreAuthorize("hasRole('USER')")
//    @ResponseBody
//    @GetMapping("/only-user-role")
//    public String onlyUser() {
//        return "Only users can see this page";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @ResponseBody
//    @GetMapping("/only-admin-role")
//    public String onlyAdmin() {
//        return "Only admins can see this page";
//    }
//
//    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
//    @ResponseBody
//    @GetMapping("/only-admin-or-user-role")
//    public String onlyAdminOrUser() {
//        return "Only admins or users can see this page";
//    }

}
