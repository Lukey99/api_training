package fr.esiea.ex4A.Service;

import fr.esiea.ex4A.Models.User;
import fr.esiea.ex4A.Models.UserInfo;
import fr.esiea.ex4A.Repository.UserRepository;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AgifyClient agifyClient;

    public UserService(UserRepository userRepository, AgifyClient agifyClient) {
        this.userRepository = userRepository;
        this.agifyClient = agifyClient;
    }

    public void registerUser(UserInfo userInfo) throws IOException, JSONException {
        if (userRepository.getUser(userInfo.name, userInfo.country) == null) {
            User user = getUser(userInfo.name, userInfo.country);
            if (user != null) {
                userRepository.cacheData.put(userInfo, user.age);
                userRepository.addUser(userInfo);
            }
        }
    }

    public List<UserInfo> matches(String name, String country) {
        UserInfo userInfo = userRepository.getUser(name, country);
        return userRepository.userMatch(userInfo);
    }

    public User getUser(String name, String country) throws IOException, JSONException {
        Call<ResponseBody> call = agifyClient.getUser(name, country);
        Response<ResponseBody> response = call.execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject.get("age") == null ? null : new User(jsonObject.getString("name"), jsonObject.getInt("age"), jsonObject.getInt("count"), jsonObject.getString("country_id"));
    }

}
