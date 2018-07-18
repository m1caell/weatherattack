package br.com.cwi.crescer.weatherattack.web.controller.login;

import br.com.cwi.crescer.weatherattack.dto.request.LoginDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.LoginDtoResponse;
import br.com.cwi.crescer.weatherattack.service.login.FazerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class LoginController {

    @Autowired
    FazerLoginService fazerLoginService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginDtoResponse login(@RequestBody LoginDtoRequest request) {
        return fazerLoginService.fazerLogin(request);
    }
}
