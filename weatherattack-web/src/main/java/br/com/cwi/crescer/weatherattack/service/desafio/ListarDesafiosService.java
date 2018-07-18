package br.com.cwi.crescer.weatherattack.service.desafio;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import lombok.Getter;

@Service
public class ListarDesafiosService {

    @Getter
    private List<Desafio> desafios = new ArrayList<>();

    public Desafio adicionarDesafio(final UsuarioDtoResponse desafiante, final UsuarioDtoResponse desafiado) {
        Desafio desafio = new Desafio(desafiante, desafiado);
        desafios.add(desafio);
        return desafio;
    }
}
