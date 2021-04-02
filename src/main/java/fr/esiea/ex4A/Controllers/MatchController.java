package fr.esiea.ex4A.Controllers;

import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {
    private final UserService userService;

    MatchController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/matches")
    List<UserInfo> matches(@RequestParam String userName, @RequestParam String userCountry){
        return userService.matches(userName, userCountry);
    }

}
