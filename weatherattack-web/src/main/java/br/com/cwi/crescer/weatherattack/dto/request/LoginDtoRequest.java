package br.com.cwi.crescer.weatherattack.dto.request;

import lombok.Data;

@Data
public class LoginDtoRequest {
    private final String email;
    private final String senha;
}
