package peaksoft.service;

import peaksoft.dto.request.AddColourRequest;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.ProductResponseForAdmin;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;

import java.util.List;

public interface ProductService {
    SimpleResponse save(Long loginID, Category category, ProductRequest productRequest);

    SimpleResponse addColours(Long loginID, Long productID, AddColourRequest addColourRequest);

    List<ProductResponse> findAll();

    SimpleResponse addOrRemoveFav(Long loginID, Long productID);

    List<ProductResponseForAdmin> findAllForAdmin();

    SimpleResponse deleteProduct(Long loginID, Long productID);

    SimpleResponse addToBasked(Long loginID, Long productID);

    SimpleResponse deleteFromBasked(Long loginID, Long productID);
}
