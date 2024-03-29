package br.com.trabalhoweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Purchase implements Serializable {
    private static final long serialVersionUID = -6419113076388179923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @NotNull(message = "Informe o fornecedor antes de salvar.")
    private Supplier supplier;

    @NotNull(message = "Preencha o campo data!")
    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
    private List<ProductPurchase> productsPurchase;

    @JsonIgnore
    public List<ProductPurchase> getProductsPurchace(){
        return productsPurchase;
    }
}
