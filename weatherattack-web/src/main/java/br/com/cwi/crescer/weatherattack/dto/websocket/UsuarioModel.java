package br.com.cwi.crescer.weatherattack.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UsuarioModel {

    private Long id;

    private String apelido;

    private String email;

}
