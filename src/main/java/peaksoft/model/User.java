package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Role;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Product> baskets;

    @ManyToMany
    private List<Product> favorites;

}
