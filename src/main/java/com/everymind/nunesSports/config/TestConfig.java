package com.everymind.nunesSports.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.everymind.nunesSports.entities.Product;
import com.everymind.nunesSports.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product(null, 250.0, "Chuteira", "Calçado esportivo para futebol.");
		Product p2 = new Product(null, 250.0, "Bola de futebol",
				"Bola para campo confeccionada em material durável e tecnológico");
		Product p3 = new Product(null, 250.0, "Meião", "Composição: 85% poliéster, 15% algodão.");

		productRepository.saveAll(Arrays.asList(p1, p2, p3));

	}
}