package br.com.cwi.crescer.weatherattack.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PODER")
public class Poder {

    private static final String SEQUENCE = "SEQ_PODER";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PODER",nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DANOBASE",nullable = false)
    private Long danoBase;

    @Column(name = "CUSTO_MANA",nullable = false)
    private Long custoDeMana;


    @OneToMany(mappedBy = "poder", fetch = FetchType.EAGER)
    private List<Regra> regras;


}
