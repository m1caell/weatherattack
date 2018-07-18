package br.com.cwi.crescer.weatherattack.service.logCombate;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.dto.response.LogCombateDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscarLogCombatePorIdUsuarioService {

    @Autowired
    private LogCombateDelegate repository;

    @Autowired
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    public List<LogCombateDtoResponse> buscar(Long id){
        List<LogCombate> lista =  repository.findTop4ByDesafiado_IdOrDesafiante_IdOrderByDataFimDesc(id, id);
        List<LogCombateDtoResponse> listaDto = new ArrayList<>();
        for (LogCombate l: lista) {
            UsuarioDtoResponse desafiante = buscarUsuarioResponsePorIdService.buscarPorId(l.getDesafiante().getId());
            UsuarioDtoResponse desafiado = buscarUsuarioResponsePorIdService.buscarPorId(l.getDesafiado().getId());
            UsuarioDtoResponse vencedor = buscarUsuarioResponsePorIdService.buscarPorId(l.getVencedor().getId());
            listaDto.add(LogCombateDtoResponse.builder().id(l.getId()).desafiante(desafiante).desafiado(desafiado).vencedor(vencedor)
                            .duracaoEmSegundos(l.getDuracaoEmSegundos()).dataInicio(l.getDataInicio()).dataFim(l.getDataFim())
                            .idCombate(l.getIdCombate()).build());
        }

        return listaDto;
    }
}
