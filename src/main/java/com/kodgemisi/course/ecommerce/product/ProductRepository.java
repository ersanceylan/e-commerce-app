package com.kodgemisi.course.ecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findByEnabledTrue(Pageable pageable);

	Optional<Product> findByIdAndEnabledTrue(Long id);

}
