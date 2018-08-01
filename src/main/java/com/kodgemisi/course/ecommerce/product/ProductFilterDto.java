package com.kodgemisi.course.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
class ProductFilterDto {

    private String keyword;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

//    private String categoryName;

}
