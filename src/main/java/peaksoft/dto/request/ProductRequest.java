package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Size;
import peaksoft.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private long quantity;
    private List<Size> sizes;
    private String image;
    private String colour;

    public Product build(){
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setSizes(this.sizes);
        product.setImage(this.image);
        product.addColours(this.colour);
        return product;
    }
}
