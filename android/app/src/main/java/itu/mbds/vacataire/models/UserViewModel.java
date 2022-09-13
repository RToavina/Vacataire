package itu.mbds.vacataire.models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user;
    private Context context;
    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<User>();
        }
        loadUser();
        return user;
    }

    private void loadUser() {
        // Do an asynchronous operation to fetch users.
        SharedPreferences  mPrefs = context.getSharedPreferences("userLogin", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user","");
        user.setValue(gson.fromJson(json, User.class));
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
