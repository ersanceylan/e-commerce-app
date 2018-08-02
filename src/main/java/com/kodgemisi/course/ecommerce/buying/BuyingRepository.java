package com.kodgemisi.course.ecommerce.buying;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BuyingRepository extends CrudRepository<Buying, Long> {
}
