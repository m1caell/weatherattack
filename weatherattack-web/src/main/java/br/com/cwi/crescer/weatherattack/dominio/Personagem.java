package br.com.cwi.crescer.weatherattack.dominio;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name= "PERSONAGEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Personagem {

    //private static final String SEQUENCE = "SEQ_PERSONAGEM";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSONAGEM",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "VIDA", nullable = false)
//    @Transient
    private Long vida;

    @Column(name = "MANA", nullable = false)
//    @Transient
    private Long mana;

    @Column(name = "BATALHAS", nullable = false)
    private Long battles;

    @Column(name = "VITORIAS", nullable = false)
    private Long wins;

    @Column(name = "DERROTAS", nullable = false)
    private Long loses;

    @Column(name = "MOEDAS", nullable = false)
    private Long coins;

    @Transient
    private List<Poder> poderes;

    public Personagem(Usuario usuario) {
        this.usuario = usuario;
        this.vida = 100L;
        this.mana = 100L;
        this.coins = 0L;
        this.battles = 0l;
        this.wins = 0l;
        this.loses = 0l;
    }

    public Personagem setValoresVencedor(){
        this.coins = this.coins + 10;
        this.battles ++;
        this.wins ++;
        return this;
    }

    public Personagem setValoresPerdedor(){
        this.coins = this.coins + 5;
        this.battles ++;
        this.loses ++;
        return this;
    }

}
