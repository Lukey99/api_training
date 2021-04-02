package fr.esiea.ex4A.Repository;

import fr.esiea.ex4A.Models.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    public final int ageDifference = 4;
    public final List<UserInfo> userInfos;
    public final Map<UserInfo, Integer> cacheData;

    public UserRepository() {
        userInfos = new ArrayList<>();
        this.cacheData = new HashMap<>();
    }

    public UserInfo getUser(String name, String country) {
        return userInfos.stream().filter(userInfo -> userInfo.name.equals(name) && userInfo.country.equals(country)).findFirst().orElse(null);
    }

    public void addUser(UserInfo userInfo) {
        userInfos.add(userInfo);
    }

    public List<UserInfo> userMatch(UserInfo currentUserInfo) {
        return userInfos.stream().filter(userInfo -> Math.abs(getUserAge(currentUserInfo) - getUserAge(userInfo)) <= ageDifference
            && currentUserInfo != userInfo && currentUserInfo.sexPref.equals(userInfo.sex)
            && currentUserInfo.sex.equals(userInfo.sexPref)).collect(Collectors.toList());
    }

    public int getUserAge(UserInfo userInfo) {
        return cacheData.get(userInfo);
    }

}
