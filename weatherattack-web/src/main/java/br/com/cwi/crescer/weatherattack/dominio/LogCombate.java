package br.com.cwi.crescer.weatherattack.dominio;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "LOG_COMBATE")
public class LogCombate {

    private static final String SEQUENCE = "SEQ_LOG_COMBATE";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOG_COMBATE",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DESAFIANTE")
    private Usuario desafiante;

    @ManyToOne
    @JoinColumn(name = "DESAFIADO")
    private Usuario desafiado;

    @ManyToOne
    @JoinColumn(name = "VENCEDOR")
    private Usuario vencedor;

    @Column(name = "DURACAO_EM_SEGUNDOS")
    private Long duracaoEmSegundos;

    @Column(name = "DATA_INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private  LocalDateTime dataFim;

    @Column(name = "ID_COMBATE")
    private String idCombate;
}
