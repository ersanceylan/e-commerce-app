package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.cart.CartItem;
import com.kodgemisi.course.ecommerce.cart.CartService;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@PreAuthorize("hasRole('USER')")
@Controller
@AllArgsConstructor
@RequestMapping("/buy")
public class BuyingController {

    private final CartService cartService;

    private final BuyingService buyingService;

    private final ProductService productService;

    @GetMapping("/checkout")
    String checkout(Model model) {
        List<CartItem> cartItems = cartService.getAllItems();
        BigDecimal total = BigDecimal.valueOf(0);
        for (CartItem cartItem : cartItems) {
            BigDecimal itemTotal = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf((cartItem.getCount())));
            total = total.add(itemTotal);
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", total);
        return "buying/checkout";
    }

    @PostMapping
    String proceedPayment(@AuthenticationPrincipal User user) {

        // payment details

        List<CartItem> cartItems = cartService.getAllItems();

        // todo: handle exception
        Set<SellingProduct> sellingProducts = buyingService.createSellingProducts(cartItems);

        Buying buying = buyingService.createNewBuying(user, sellingProducts, PaymentType.CREDIT_CARD);

        // PAY : this will return a success payment status

        productService.updateStockCounts(sellingProducts);

        buying.setBuyingStatus(BuyingStatus.APPROVED);
        // todo: update buying status

        cartService.removeAllItems();

        // todo: set redirect attributes

        return "redirect:/";
    }

}
