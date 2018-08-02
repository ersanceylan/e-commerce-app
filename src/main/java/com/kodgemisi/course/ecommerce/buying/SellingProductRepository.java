package com.kodgemisi.course.ecommerce.buying;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SellingProductRepository extends CrudRepository<SellingProduct, Long> {
}
