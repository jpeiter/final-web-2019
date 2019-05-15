package br.com.trabalhoweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Brand implements Serializable {

    private static final long serialVersionUID = 6630464431771335393L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    @NotNull(message = "Informe o nome!")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull(message = "Selecione o país de origem!")
    private Country country;
}
