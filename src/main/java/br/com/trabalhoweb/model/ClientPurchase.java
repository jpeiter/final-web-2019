package br.com.trabalhoweb.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "clientPurchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@JsonSerialize
public class ClientPurchase implements Serializable {

    private static final long serialVersionUID = 4948964520899939822L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Preencha o campo data!")
    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @NotNull(message = "Informe o país de envio!")
    private Country country;

    @Column(name = "shipping_price", nullable = false)
    private BigDecimal shippingPrice;

    @OneToMany(mappedBy = "clientPurchase", fetch = FetchType.LAZY)
    private List<ProductClientPurchase> productClientPurchases;

}
