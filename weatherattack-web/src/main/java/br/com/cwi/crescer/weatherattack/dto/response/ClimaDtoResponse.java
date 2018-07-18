package br.com.cwi.crescer.weatherattack.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClimaDtoResponse {

    public BigDecimal temperatura;

    public BigDecimal vento;

    public BigDecimal chuva;

    public BigDecimal neve;

    public BigDecimal tempestade;

    public String nomeDaCidade;

}
