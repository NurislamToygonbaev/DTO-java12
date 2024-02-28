package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductAPI {
    private final ProductService productService;

    @PostMapping("/{loginID}")
    public SimpleResponse save(@PathVariable Long loginID,
                               @RequestParam Category category,
                               @RequestBody ProductRequest productRequest){
        return productService.save(loginID, category, productRequest);
    }
}
