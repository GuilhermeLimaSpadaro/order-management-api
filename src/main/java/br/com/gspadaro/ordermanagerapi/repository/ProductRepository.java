package br.com.gspadaro.ordermanagerapi.repository;

import br.com.gspadaro.ordermanagerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
