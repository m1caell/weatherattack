package br.com.cwi.crescer.weatherattack.service.personagem;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.exception.personagem.PersonagemNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPersonagemResponseService {

    @Autowired
    private PersonagemDelegate repository;

    @Autowired
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    public PersonagemDtoResponse buscarPersonagemPorId(Long id){
        Personagem p =  repository.findById(id).orElseThrow(() -> new PersonagemNaoEncontradoException());
        UsuarioDtoResponse usuarioDtoResponse = buscarUsuarioResponsePorIdService.buscarPorId(p.getUsuario().getId());
        return PersonagemDtoResponse.builder().id(p.getId()).battles(p.getBattles()).coins(p.getCoins()).loses(p.getLoses())
                .mana(p.getMana()).poderes(p.getPoderes()).usuario(usuarioDtoResponse).vida(p.getVida()).wins(p.getWins()).build();
    }
}
