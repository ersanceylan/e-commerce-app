package com.kodgemisi.course.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class ProductFilterCriteria {

    private String key;

    private String operation;

    private Object value;

}
