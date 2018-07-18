package br.com.cwi.crescer.weatherattack.criptografiaTest;

import br.com.cwi.crescer.weatherattack.exception.usuario.SenhaInvalidaException;
import br.com.cwi.crescer.weatherattack.service.criptografia.CriptografiaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CriptografiaServiceTest {

    @InjectMocks
    private CriptografiaService tested;

    @Test
    public void deveCriptografarSenha(){
        String senhaCriptografada = tested.criptografarSenha("123");
        Assert.assertNotEquals("123", senhaCriptografada);
    }

    @Test
    public void retornaTrueSeSenhasIguais(){
        String senhaCriptografada = tested.criptografarSenha("123");
        boolean resposta = tested.CompararSenha("123", senhaCriptografada);
        Assert.assertEquals(true, resposta);
    }

    @Test(expected = SenhaInvalidaException.class)
    public void retornaExceptionSeSenhasDiferentes(){
        String senhaCriptografada = tested.criptografarSenha("123");
        boolean resposta = tested.CompararSenha("1234", senhaCriptografada);

    }
}
