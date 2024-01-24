package com.everymind.nunesSports.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.everymind.nunesSports.entities.Product;
import com.everymind.nunesSports.repositories.ProductRepository;
import com.everymind.nunesSports.services.exceptions.DatabaseException;
import com.everymind.nunesSports.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product insert(Product product) {
		return repository.save(product);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity, product);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
		entity.setDescription(product.getDescription());
	}

	public void delete(Long id) {
		if (repository.existsById(id)) {
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	}
}
