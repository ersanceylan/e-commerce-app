package com.kodgemisi.course.ecommerce.cart;

import com.kodgemisi.course.ecommerce.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartItem implements Serializable {

    private Long productId;

    private int count;

    private Product product;

}
