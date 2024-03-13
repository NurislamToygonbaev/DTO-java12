package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.AddColourRequest;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.ProductResponseForAdmin;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Category;
import peaksoft.enums.Role;
import peaksoft.model.Product;
import peaksoft.model.User;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ProductService;

import java.util.ArrayList;
import java.util.List;
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

    private User checkUser(Long userId){
        return userRepo.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with id: "+userId+" not found"));
    }

    @Override
    public SimpleResponse save(Long loginID, Category category, ProductRequest productRequest) {
        checkLoginUSer(loginID);
        Product buildProduct = productRequest.build();
        buildProduct.setCategory(category);
        productRepo.save(buildProduct);
        return new SimpleResponse(HttpStatus.OK, "Successfully saved Product with name: "+buildProduct.getName());
    }

    @Override @Transactional
    public SimpleResponse addColours(Long loginID, Long productID, AddColourRequest addColourRequest) {
        checkLoginUSer(loginID);
        Product product = productRepo.findById(productID)
                .orElseThrow(() -> new NoSuchElementException("Product with ID: " + productID + " not found"));
        for (String colour : addColourRequest.colours()) {
            product.addColours(colour);
        }
        return new SimpleResponse(HttpStatus.OK, "success add colours");
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepo.findAllProducts();
    }

    @Override @Transactional
    public SimpleResponse addOrRemoveFav(Long loginID, Long productID) {
        User user = userRepo.findById(loginID)
                .orElseThrow(() ->
                        new NoSuchElementException("User with id: " + loginID + " not found"));
        Product product = productRepo.findById(productID)
                .orElseThrow(() -> new NoSuchElementException("Product with ID: " + productID + " not found"));

        if (user.getFavoriteProducts().contains(product)){
            user.getFavoriteProducts().remove(product);
            return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("product removed from favorites").build();
        }
        user.getFavoriteProducts().add(product);
        return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("product saved to favorites").build();
    }

    @Override
    public List<ProductResponseForAdmin> findAllForAdmin() {
        return productRepo.findAllForAdmin();
    }

    @Override
    public SimpleResponse deleteProduct(Long loginID, Long productID) {
        checkLoginUSer(loginID);
        Product product = productRepo.findById(productID).orElseThrow(
                () -> new NoSuchElementException("product with id:" + productID + " not found"));
        productRepo.delete(product);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully deleted")
                .build();
    }

    @Override @Transactional
    public SimpleResponse addToBasked(Long loginID, Long productID) {
        User user = checkUser(loginID);
        Product product = productRepo.findById(productID)
                .orElseThrow(() -> new NoSuchElementException("Product with ID: " + productID + " not found"));

        user.getBasketProducts().add(product);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success added")
                .build();
    }

    @Override @Transactional
    public SimpleResponse deleteFromBasked(Long loginID, Long productID) {
        User user = checkUser(loginID);
        Product product = productRepo.findById(productID)
                .orElseThrow(() -> new NoSuchElementException("Product with ID: " + productID + " not found"));

        user.getBasketProducts().remove(product);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success added")
                .build();
    }
}
