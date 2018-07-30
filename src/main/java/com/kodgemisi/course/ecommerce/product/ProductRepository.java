package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findByEnabled(Pageable pageable, @Param("enabled") Boolean enabled );

	Optional<Product> findByIdAndEnabledTrue(Long id);

}
