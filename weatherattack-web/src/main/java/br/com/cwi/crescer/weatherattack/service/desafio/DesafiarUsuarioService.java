package br.com.cwi.crescer.weatherattack.service.desafio;

import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;

@Service
public class DesafiarUsuarioService {

    @Autowired
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    @Autowired
    private ListarDesafiosService listarDesafiosService;

    public Desafio desafiarUsuario(Long idDesafiante, Long idDesafiado){
        UsuarioDtoResponse desafiante = buscarUsuarioResponsePorIdService.buscarPorId(idDesafiante);
        UsuarioDtoResponse desafiado = buscarUsuarioResponsePorIdService.buscarPorId(idDesafiado);

        return listarDesafiosService.adicionarDesafio(desafiante, desafiado);
    }
}
