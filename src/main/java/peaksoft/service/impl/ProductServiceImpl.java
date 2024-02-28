package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.enums.Role;
import peaksoft.model.Product;
import peaksoft.model.User;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ProductService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    private void checkLoginUSer(Long loginID){
        User user = userRepo.findById(loginID)
                .orElseThrow(() ->
                        new NoSuchElementException("User with id: " + loginID + " not found"));
        if (!user.getRole().equals(Role.ADMIN)){
            throw new RuntimeException("Forbidden 403");
        }
    }

    @Override
    public SimpleResponse save(Long loginID, Category category, ProductRequest productRequest) {
        checkLoginUSer(loginID);
        Product buildProduct = productRequest.build();
//        System.out.println("productRequest.getName() = " + productRequest.getName());
        buildProduct.setCategory(category);
        productRepo.save(buildProduct);
        return new SimpleResponse(HttpStatus.OK, "Successfully saved Product with name: "+buildProduct.getName());
    }
}
