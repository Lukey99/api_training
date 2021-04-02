package fr.esiea.ex4A.Repository;

import fr.esiea.ex4A.Models.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public final int ageDifference = 4;
    public final List<UserInfo> userInfos;

    public UserRepository() {
        userInfos = new ArrayList<>();
    }

    public UserInfo getUser(String name, String country) {
        return userInfos.stream().filter(userInfo -> userInfo.name.equals(name) && userInfo.country.equals(country)).findFirst().orElse(null);
    }

    public void addUser(UserInfo userInfo) {
        userInfos.add(userInfo);
    }

}
