package br.com.cwi.crescer.weatherattack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoResponse {
    private Long id;
    private String nome;
    private String email;
    private String apelido;
}
