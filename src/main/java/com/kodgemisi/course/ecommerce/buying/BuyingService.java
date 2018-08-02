package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.cart.CartItem;
import com.kodgemisi.course.ecommerce.exceptions.ProductOutOfStockException;
import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import com.kodgemisi.course.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class BuyingService {

    private final ProductService productService;

    private final BuyingRepository buyingRepository;

    private final SellingProductRepository sellingProductRepository;

    void save(Buying buying) {
        buyingRepository.save(buying);
    }

    Buying createNewBuying(User user, Set<SellingProduct> sellingProducts, PaymentType paymentType) {

        // implement payment info

        Buying buying = new Buying(user, sellingProducts, paymentType);
        this.save(buying);
        return buying;
    }

    Set<SellingProduct> createSellingProducts(List<CartItem> items) throws ProductOutOfStockException {
        return items.stream().map(item -> {
            Product product = productService.findById(item.getProductId());
            if (product.getStock() < item.getCount()) {
                // todo: throw new Exception
                throw new ProductOutOfStockException();
            }
            SellingProduct selling = new SellingProduct(item.getCount(), product);
            sellingProductRepository.save(selling);
            return selling;
        })
        .collect(Collectors.toSet());
    }

}
