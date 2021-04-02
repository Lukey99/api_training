package fr.esiea.ex4A;

import fr.esiea.ex4A.Service.AgifyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class Launcher {

    @Bean
    AgifyClient agifyClient() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.agify.io/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit.create(AgifyClient.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
