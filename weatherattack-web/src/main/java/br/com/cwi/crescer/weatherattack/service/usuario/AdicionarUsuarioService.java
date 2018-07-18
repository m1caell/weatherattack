package br.com.cwi.crescer.weatherattack.service.usuario;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.exception.usuario.EmailEmUsoException;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.criptografia.CriptografiaService;
import br.com.cwi.crescer.weatherattack.service.personagem.CriarNovoPersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdicionarUsuarioService {

    @Autowired
    private UsuarioDelegate repository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private CriptografiaService criptografiaService;

    @Autowired
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    @Autowired
    private CriarNovoPersonagemService criarNovoPersonagemService;

    public UsuarioDtoResponse adicionarUsuario(UsuarioDtoRequest usuarioDto){

        try{

            validarUsuarioService.validarUsuario(usuarioDto);
            Usuario usuario1 = buscarUsuarioPorEmailService.buscarUsuarioPorEmail(usuarioDto.getEmail());
            throw new EmailEmUsoException();

        } catch (UsuarioNaoEncontradoException e) {

            String senhaEncriptada = criptografiaService.criptografarSenha(usuarioDto.getSenha());
            usuarioDto.setSenha(senhaEncriptada);
            Usuario usuario = new Usuario();
            Usuario usuarioCadastrado = usuario.novo(usuarioDto);
            repository.save(usuarioCadastrado);
            criarNovoPersonagemService.criarPersonagem(usuarioCadastrado);
            return buscarUsuarioResponsePorIdService.buscarPorId(usuarioCadastrado.getId());
        }
    }
}
