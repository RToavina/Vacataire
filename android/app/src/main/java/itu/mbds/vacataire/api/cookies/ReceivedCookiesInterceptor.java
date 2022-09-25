package itu.mbds.vacataire.api.cookies;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private Context context;
    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = PreferenceHelper.getCookies(context);
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            PreferenceHelper.setCookies(context,cookies);
        }
        if (originalResponse.code() == 401) {
            Log.e("error", "Unauthorized");
        }

        return originalResponse;
    }
}