package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.category.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@XmlRootElement
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

    @NotNull
    @OneToOne
    private Category category;

    private LocalDate creationDate;
}
