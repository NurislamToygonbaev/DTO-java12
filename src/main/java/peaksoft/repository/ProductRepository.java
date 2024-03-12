package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.ProductResponseForAdmin;
import peaksoft.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new peaksoft.dto.response.ProductResponse(p.image, p.name, p.price) from Product p")
    List<ProductResponse> findAllProducts();

    @Query("select new peaksoft.dto.response.ProductResponseForAdmin(p.name, p.image, p.colours, p.sizes, p.quantity, p.price) from Product p")
    List<ProductResponseForAdmin> findAllForAdmin();

}
