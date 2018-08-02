package com.kodgemisi.course.ecommerce.buying;

import com.kodgemisi.course.ecommerce.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class SellingProduct implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int count;

    // fixme: implement hibernate envers (http://hibernate.org/orm/envers/)
    @NotNull
    @OneToOne
    private Product product;

    SellingProduct(int count, Product product) {
        this.count = count;
        this.product = product;
    }
}
