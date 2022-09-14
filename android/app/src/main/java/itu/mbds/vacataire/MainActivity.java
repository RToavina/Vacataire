package itu.mbds.vacataire;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import itu.mbds.vacataire.models.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.button_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        switch (item.getItemId()) {
            case R.id.navigation_signup:
                navController.navigate(R.id.signupFragment);
                invalidateOptionsMenu();
                return true;
            case R.id.navigation_calendar:
                navController.navigate(R.id.calendarFragment);
                invalidateOptionsMenu();
                return true;
            case R.id.navigation_logout:
                userViewModel.clearUser();
                navController.navigate(R.id.loginFragment);
                invalidateOptionsMenu();
                return true;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!userViewModel.isLoggedIn) {
            menu.findItem(R.id.navigation_logout).setVisible(false);
            menu.findItem(R.id.navigation_calendar).setVisible(false);
            menu.findItem(R.id.navigation_login).setVisible(true);
            menu.findItem(R.id.navigation_signup).setVisible(true);
        }else{
            menu.findItem(R.id.navigation_logout).setVisible(true);
            menu.findItem(R.id.navigation_calendar).setVisible(true);
            menu.findItem(R.id.navigation_login).setVisible(false);
            menu.findItem(R.id.navigation_signup).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
