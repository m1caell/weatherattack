package br.com.cwi.crescer.weatherattack.dominio.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Parametro {

    MAIOR_QUE(">"),
    MENOR_QUE("<"),
    MAIOR_OU_IGUAL_A(">="),
    MENOR_OU_IGUAL_A("<=");


    private String parametro;

}
