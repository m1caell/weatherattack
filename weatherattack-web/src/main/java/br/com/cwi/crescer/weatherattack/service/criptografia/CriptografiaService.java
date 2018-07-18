package br.com.cwi.crescer.weatherattack.service.criptografia;

import br.com.cwi.crescer.weatherattack.exception.usuario.SenhaInvalidaException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {


    public String criptografarSenha(String senha){
        return BCrypt.hashpw(senha,BCrypt.gensalt());
    }

    public boolean CompararSenha( String senhaCandidata,String senhaHash){
        boolean isSenhasIguais = BCrypt.checkpw(senhaCandidata,senhaHash);
        if(!isSenhasIguais){
            throw new SenhaInvalidaException();
        }else{
            return true;
        }
    }
}
