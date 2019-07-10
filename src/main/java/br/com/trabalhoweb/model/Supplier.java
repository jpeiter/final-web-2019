package br.com.trabalhoweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "supplier", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 7822387491561498723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    @NotNull(message = "Informe o nome antes de salvar.")
    private String name;

    @Column(name = "code", length = 20, nullable = false)
    @NotNull(message = "Informe o código antes de salvar.")
    private String code;

    @ManyToOne()
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull(message = "Selecione o país antes de salvar.")
    private Country country;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;
}
