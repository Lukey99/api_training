package fr.esiea.ex4A.hello.Controller;

import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MatchControllerIT {

    private final MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public MatchControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void matchesThenReturnsListOfUsers() throws Exception {

        UserInfo userInfo = new UserInfo("test@test.fr", "Emile", "emile", "US", "M", "F");
        UserInfo userInfo2 = new UserInfo("test2@test.fr", "Isabelle", "isabelle", "FR", "F", "M");
        UserInfo userInfo3 = new UserInfo("test3@test.fr", "Ethan", "ethan", "FR", "M", "F");

        userService.registerUser(userInfo);
        userService.registerUser(userInfo2);
        userService.registerUser(userInfo3);

        when(userService.matches("Emile", "US")).thenReturn(List.of(userInfo2));

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches?userName=Emile&userCountry=US"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) jsonPath("$[0].name").value("Isabelle"))
            .andExpect((ResultMatcher) jsonPath("$[0].twitter").value("isabelle"));

        verify(userService).matches("Emile", "FR");

    }

}
