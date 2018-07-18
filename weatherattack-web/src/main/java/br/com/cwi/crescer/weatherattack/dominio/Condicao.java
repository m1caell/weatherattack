package br.com.cwi.crescer.weatherattack.dominio;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.CondicoesClimaticas;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "CONDICAO")
public class Condicao {

    private static final String SEQUENCE = "SEQ_CONDICAO";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONDICAO",nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOME", nullable = false)
    private CondicoesClimaticas nome;
}
