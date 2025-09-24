package vn.maxtrann.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(200)")
    private String name;

    @Column(name = "images", columnDefinition = "nvarchar(200)")
    private String images;

    @ManyToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<>();
}
