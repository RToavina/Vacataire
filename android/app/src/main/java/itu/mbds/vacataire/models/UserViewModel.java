package itu.mbds.vacataire.models;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;
    public boolean isLoggedIn = false;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<User>();
            loadUser();
        }
        return user;
    }

    private void loadUser() {
        // Do an asynchronous operation to fetch users.
        SharedPreferences  mPrefs = getApplication().getSharedPreferences("userLogin", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user","");
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

    public void clearUser() {
        user.setValue(null);
        SharedPreferences mPrefs = getApplication().getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear();
        prefsEditor.commit();
        isLoggedIn = false;
    }
}
