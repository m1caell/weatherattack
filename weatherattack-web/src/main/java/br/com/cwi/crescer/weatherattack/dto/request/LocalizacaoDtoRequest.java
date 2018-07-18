package br.com.cwi.crescer.weatherattack.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocalizacaoDtoRequest {

    public final BigDecimal latitude;

    public  final BigDecimal longitude;


}
