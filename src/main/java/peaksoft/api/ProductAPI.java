package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.AddColourRequest;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.ProductResponseForAdmin;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ProductAPI {
    private final ProductService productService;

    @GetMapping
    @Secured({"ADMIN", "CLIENT"})
    public List<ProductResponse> findAll(){
        return productService.findAll();
    }

    @PostMapping("/{loginID}")
    @Secured("ADMIN")
    public SimpleResponse save(@PathVariable Long loginID,
                               @RequestParam Category category,
                               @RequestBody ProductRequest productRequest){
        return productService.save(loginID, category, productRequest);
    }

    @Secured("ADMIN")
    @PostMapping("/{loginID}/{productID}")
    public SimpleResponse addColour(@PathVariable Long loginID,
                                    @PathVariable Long productID,
                                    @RequestBody AddColourRequest addColourRequest){
        return productService.addColours(loginID, productID, addColourRequest);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{loginID}/{productID}")
    public SimpleResponse deleteProduct(@PathVariable Long loginID,
                                        @PathVariable Long productID){
        return productService.deleteProduct(loginID, productID);
    }
}
