package fr.esiea.ex4A.Controllers;

import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InscriptionController {

    private final UserService userService;

    InscriptionController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody UserInfo userInfo) throws Exception{
        userService.registerUser(userInfo);
    }
}
