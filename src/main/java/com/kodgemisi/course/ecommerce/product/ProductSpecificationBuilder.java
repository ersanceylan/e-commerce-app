package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationBuilder {

    private final List<ProductFilterCriteria> params;

    ProductSpecificationBuilder() {
        params = new ArrayList<ProductFilterCriteria>();
    }

    public ProductSpecificationBuilder with(String key, String operation, Object value) {
        if (value != null) {
            params.add(new ProductFilterCriteria(key, operation, value));
        }
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Product>> specs = new ArrayList<Specification<Product>>();
        for (ProductFilterCriteria param : params) {
            specs.add(new ProductSpecification(param));
        }

        Specification<Product> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
