package br.com.cwi.crescer.weatherattack.dto.request;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UsuarioDtoRequest {
    private String nome;
    private String email;
    private String apelido;
    private String senha;

}
