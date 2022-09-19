package itu.mbds.vacataire.models;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.util.List;

import itu.mbds.vacataire.api.ApiEndpoint;
import itu.mbds.vacataire.api.ClientApi;
import itu.mbds.vacataire.api.cookies.PreferenceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;

    public boolean isLoggedIn = false;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
            loadUser();
        }
        return user;
    }



    private void loadUser() {
        // Do an asynchronous operation to fetch users.
        SharedPreferences mPrefs = getApplication().getSharedPreferences("userLogin", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user", "");
        User u = gson.fromJson(json, User.class);
        user.setValue(u);
        isLoggedIn = u != null;
    }


    public void setUser(User user) {
        this.user.setValue(user);
        SharedPreferences mPrefs = getApplication().getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
        isLoggedIn = true;
    }

    private void clearUser() {
        user.setValue(null);
        SharedPreferences mPrefs = getApplication().getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
        isLoggedIn = false;
    }

    private void clearCookies() {
        SharedPreferences mPrefs = getApplication().getSharedPreferences(PreferenceHelper.KEY_COOKIES, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void signout() {
        ClientApi api = new ClientApi(getApplication());
        ApiEndpoint service = api.create();
        Call<MessageResponse> call = service.signout();
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    clearUser();
                    clearCookies();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {

            }
        });
    }


}
