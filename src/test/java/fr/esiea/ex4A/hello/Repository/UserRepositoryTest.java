package fr.esiea.ex4A.hello.Repository;

import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();

    @Test
    public void getUserTest() {
        String name = "hello";
        String email = "test@test.fr";
        String tweeter = "test";
        String country = "FR";
        String sex = "M";
        String sexPref = "F";
        userRepository.addUser(new UserInfo(email, name, tweeter, country, sex, sexPref));
        UserInfo userExist = userRepository.getUser(name, country);
        assertEquals(name, userExist.name);
        assertEquals(email, userExist.email);
        assertEquals(tweeter, userExist.twitter);
        assertEquals(country, userExist.country);
        assertEquals(sex, userExist.sex);
        assertEquals(sexPref, userExist.sexPref);

        UserInfo userNull = userRepository.getUser("unknown", "F");
        assertNull(userNull);
    }

    @Test
    public void addUserTest() {
        assertEquals(0, userRepository.userInfos.size());
        userRepository.addUser(new UserInfo("test@test.fr", "test", "test", "FR", "F", "F"));
        assertEquals(1, userRepository.userInfos.size());
    }

    @Test
    public void userMatchTest() {
        UserInfo userInfo = new UserInfo("test@test.fr", "test", "test", "FR", "M", "O");
        userRepository.addUser(userInfo);
    }
}
