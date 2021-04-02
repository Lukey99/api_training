package fr.esiea.ex4A.hello.Service;

import fr.esiea.ex4A.Service.AgifyClient;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AgifyClientIT {

    @Test
    public void callTest() throws IOException {
        AgifyClient agifyClient = create();
        Call<ResponseBody> call = agifyClient.getUser("Emile", "US");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    assertEquals("Emile", jsonObject.get("name"));
                    assertEquals(53, jsonObject.get("age"));
                    assertEquals("US", jsonObject.get("country_id"));
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                call.cancel();
            }
        });
    }

    private AgifyClient create() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.agify.io/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit.create(AgifyClient.class);
    }
}
