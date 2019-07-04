package br.com.trabalhoweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Product implements Serializable {

    private static final long serialVersionUID = -4733681235111609425L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    @NotNull(message = "Informe o nome!")
    private String name;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @DecimalMin(value = "0.01",
            message = "O valor deve ser maior que R$ 0.00.")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull(message = "Selecione a categoria!")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @NotNull(message = "Selecione a marca!")
    private Brand brand;
}
