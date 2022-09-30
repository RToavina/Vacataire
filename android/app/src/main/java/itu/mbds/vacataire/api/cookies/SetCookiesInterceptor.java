package itu.mbds.vacataire.api.cookies;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SetCookiesInterceptor implements Interceptor {

    // We're storing our stuff in a preference made just for cookies called PREF_COOKIES.
    private Context context;

    public SetCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences =  PreferenceHelper.getCookies(context);

        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}