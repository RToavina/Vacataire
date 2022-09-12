package itu.mbds.vacataire.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApi {
    public final static String API_URL = "http://192.168.1.49:8080/api/";
    private static ApiEndpoint instance;

    public ClientApi() {
    }

    public ApiEndpoint create() {
        if(instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance =  retrofit.create(ApiEndpoint.class);
        }
        return instance;
    }
}
