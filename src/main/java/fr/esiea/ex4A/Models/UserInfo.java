package fr.esiea.ex4A.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    public final String email;
    public final String name;
    public final String twitter;
    public final String country;
    public final String sex;
    public final String sexPref;

    public UserInfo(@JsonProperty("userEmail") String userEmail, @JsonProperty("userName") String userName, @JsonProperty("userTweeter") String userTweeter, @JsonProperty("userCountry") String userCountry, @JsonProperty("userSex") String userSex, @JsonProperty("userSexPref") String userSexPref) {
        this.email = userEmail;
        this.name = userName;
        this.twitter = userTweeter;
        this.country = userCountry;
        this.sex = userSex;
        this.sexPref = userSexPref;
    }

    @Override
    public String toString() {
        return (
            "{" +
                "email: " + email + "," +
                "name: " + name + "," +
                "twitter: " + twitter + "," +
                "country: " + country + "," +
                "sex: " + sex + "," +
                "sexPref: " + sexPref + "," +
                "}"
        );
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((UserInfo) obj).name) && country.equals(((UserInfo) obj).country);
    }
}
