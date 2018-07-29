package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
