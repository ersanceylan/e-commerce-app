package com.kodgemisi.course.ecommerce.product;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @Min(0)
    private int stock;

    @Pattern(regexp = "^http.*")
    private String url;

    private LocalDate creationDate;
}
