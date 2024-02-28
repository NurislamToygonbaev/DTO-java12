package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}