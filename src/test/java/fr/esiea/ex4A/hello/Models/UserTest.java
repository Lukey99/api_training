package fr.esiea.ex4A.hello.Models;

import fr.esiea.ex4A.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void createUserTest() {
        User user = new User("test", 20, 1000, "FR");
        assertEquals("test", user.name);
        assertEquals(20, user.age);
        assertEquals(1000, user.count);
        assertEquals("FR", user.country_id);
    }

}
