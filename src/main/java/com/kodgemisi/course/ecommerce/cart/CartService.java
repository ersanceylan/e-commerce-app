package com.kodgemisi.course.ecommerce.cart;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private final HttpSession httpSession;

    public List<CartItem> getAllItems() {
        if (httpSession.getAttribute("cart") == null) {
            httpSession.setAttribute("cart", new ArrayList<CartItem>());
        }
        return (List<CartItem>) httpSession.getAttribute("cart");
    }

    public void removeAllItems() {
        httpSession.setAttribute("cart", new ArrayList<CartItem>());
    }

    // TODO: 30.07.2018 validations omitted for cart item
    void addNewItem(CartItem cartItem) {
        List<CartItem> cartItems;
        if (httpSession.getAttribute("cart") == null) {
            cartItems = new ArrayList<>(Arrays.asList(cartItem));
            httpSession.setAttribute("cart", cartItems);
        } else {
            cartItems = (List<CartItem>) httpSession.getAttribute("cart");
            if (cartItems.stream().noneMatch(item -> item.getProductId().equals(cartItem.getProductId()))) {
                cartItems.add(cartItem);
            } else {
                cartItems.forEach(item -> {
                    if (item.getProductId().equals(cartItem.getProductId())) {
                        item.setCount(item.getCount() + cartItem.getCount());
                    }
                });
            }
        }
    }

    void updateItem(CartItem cartItem) {
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cart");
        cartItems.forEach(item -> {
            if (item.getProductId().equals(cartItem.getProductId())) {
                item.setCount(cartItem.getCount());
            }
        });
    }

    void removeItem(Long productId) {
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cart");
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

}
