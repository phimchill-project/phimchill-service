package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "NAME")
    private String name;
    @NotBlank
    @Column(name = "DESCRIPTION", length = 50, nullable = false)
    private String description;
    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> customers;
}
