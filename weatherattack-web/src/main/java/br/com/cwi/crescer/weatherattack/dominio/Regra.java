package br.com.cwi.crescer.weatherattack.dominio;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.Parametro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "REGRA")
public class Regra {

    private static final String SEQUENCE = "SEQ_REGRA";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGRA",nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PODER")
    private Poder poder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONDICAO")
    private Condicao condicao;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARAMETRO", nullable = false)
    private Parametro parametro;
}
