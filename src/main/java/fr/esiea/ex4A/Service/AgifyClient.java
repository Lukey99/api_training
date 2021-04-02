package fr.esiea.ex4A.Service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AgifyClient {
    @GET("/?")
    Call<ResponseBody> getUser(@Query("name") String name, @Query("country_id") String country_id);
}
