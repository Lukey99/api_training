package fr.esiea.ex4A.hello.Controller;

import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class InscriptionControllerIT {

    private final MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public InscriptionControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void whenAddingUserThenReturns200() throws Exception {

        UserInfo userInfo = new UserInfo("test@test.fr", "test", "test", "FR", "O", "O");

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/api/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"userEmail\": \"test@test.fr\", \"userName\": \"test\", \"userTweeter\": \"test\", \"userCountry\": \"FR\", \"userSex\": \"O\", \"userSexPref\": \"O\" }")
            )
            .andExpect(status().isOk());

        verify(userService).registerUser(userInfo);

    }

}
