package com.everymind.nunesSports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everymind.nunesSports.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
