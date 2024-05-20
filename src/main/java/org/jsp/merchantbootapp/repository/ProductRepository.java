package org.jsp.merchantbootapp.repository;

import java.util.List;

import org.jsp.merchantbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);

	List<Product> findByBrand(String brand);

	List<Product> findByCategory(String category);

}