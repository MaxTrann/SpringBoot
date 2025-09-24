package vn.maxtrann.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // product id

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "desc", length = 2000)
    private String desc;

    // dùng BigDecimal cho tiền tệ
    @Column(name = "price", precision = 18, scale = 2)
    private Double price;

    // FK tới User (userid)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")       // cột userid trong đề
    private User user;
}
