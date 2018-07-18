package br.com.cwi.crescer.weatherattack.dominio;


import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id","email"})
@Entity
@Table(name = "USUARIO")
public class Usuario {

    private static final String SEQUENCE = "USUARIO_SEQ";

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    //@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO",nullable = false)
    private Long id;

    @Column(name = "NOME",nullable = false)
    private String nome;

    @Column(name = "EMAIL",nullable = false, unique = true)
    private String email;

    @Column(name = "APELIDO",nullable = false)
    private String apelido;

    @Column(name = "SENHA",nullable = false)
    private String senha;

    public Optional<String> getRole() {
        //return Objects.isNull(perfil) ? Optional.empty() : Optional.of(perfil.getCodigo());
        return Optional.of("UsuarioNormal");
    }

    public Usuario novo(UsuarioDtoRequest usuarioDto){
        return Usuario.builder().nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail()).apelido(usuarioDto.getApelido())
                .senha(usuarioDto.getSenha()).build();
    }





}
