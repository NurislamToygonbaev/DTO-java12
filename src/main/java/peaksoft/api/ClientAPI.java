package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientAPI {
    private final ProductService productService;

    @Secured("CLIENT")
    @PutMapping("/{loginID}/{productID}")
    public SimpleResponse addOrRemoveFav(@PathVariable Long loginID, @PathVariable Long productID){
        return productService.addOrRemoveFav(loginID, productID);
    }

    @Secured("CLIENT")
    @PostMapping("/{loginID}/{productID}")
    public SimpleResponse addToBasked(@PathVariable Long loginID,
                                      @PathVariable Long productID){
        return productService.addToBasked(loginID, productID);
    }

    @Secured("CLIENT")
    @DeleteMapping("/{loginID}/{productID}")
    public SimpleResponse deleteFromBasked(@PathVariable Long loginID,
                                      @PathVariable Long productID){
        return productService.deleteFromBasked(loginID, productID);
    }
}
