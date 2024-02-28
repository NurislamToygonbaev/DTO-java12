package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Category;
import peaksoft.enums.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    private Long id;
    private String name;
    private BigDecimal price;
    private long quantity;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String image;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Size> sizes;
    @ElementCollection
    private List<String> colours;

    public void addColours(String colour) {
        if (this.colours == null) this.colours = new ArrayList<>();
        this.colours.add(colour);
    }
}