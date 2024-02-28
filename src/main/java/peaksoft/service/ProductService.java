package peaksoft.service;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;

public interface ProductService {
    SimpleResponse save(Long loginID, Category category, ProductRequest productRequest);
}
