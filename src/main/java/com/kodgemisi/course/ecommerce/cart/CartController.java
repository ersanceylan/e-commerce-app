package com.kodgemisi.course.ecommerce.cart;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @GetMapping
    public String show(Model model) {
        List<CartItem> cartItems = cartService.getAllItems();
        cartItems.stream().forEach(item -> {
            Product product = productService.findById(item.getProductId());
            item.setProduct(product);
        });
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/new")
    public String addNewProduct(CartItem cartItem) {
        cartService.addNewItem(cartItem);
        return "redirect:/products/" + cartItem.getProductId();
    }

    @PostMapping("/remove")
    public String removeItem(Long productId, HttpSession httpSession) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateItem(CartItem cartItem) {
        cartService.updateItem(cartItem);
        return "redirect:/cart";
    }

}
