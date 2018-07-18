package br.com.cwi.crescer.weatherattack.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogCombateDtoResponse {
    private Long id;
    private UsuarioDtoResponse desafiante;
    private UsuarioDtoResponse desafiado;
    private UsuarioDtoResponse vencedor;
    private Long duracaoEmSegundos;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String idCombate;
}
