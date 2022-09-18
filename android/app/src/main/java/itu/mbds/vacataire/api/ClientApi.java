package itu.mbds.vacataire.api;

import android.content.Context;

import itu.mbds.vacataire.api.cookies.ReceivedCookiesInterceptor;
import itu.mbds.vacataire.api.cookies.SetCookiesInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApi {
    public final static String API_URL = "http://192.168.1.49:8080/api/";
    private static ApiEndpoint instance;
    private Context context;

    public ClientApi(Context context) {
        this.context = context;
    }

    public ApiEndpoint create() {
        if(instance == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new SetCookiesInterceptor(context));
            httpClient.addInterceptor(new ReceivedCookiesInterceptor(context));
            
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            instance =  retrofit.create(ApiEndpoint.class);
        }
        return instance;
    }
}
