package itu.mbds.vacataire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private ImageView logo;
    private TextView textWelcome,textSign;
    private Button signUp,connect;
    private TextInputLayout identifiant,password;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logo = (ImageView) getView().findViewById(R.id.logo);
        textWelcome = (TextView) getView().findViewById(R.id.texteWelcome);
        textSign = (TextView) getView().findViewById(R.id.textSign);
        connect = (Button) getView().findViewById(R.id.connect);
        identifiant = (TextInputLayout) getView().findViewById(R.id.identifiant);
        password = (TextInputLayout) getView().findViewById(R.id.password);

        //mAuth = FirebaseAuth.getInstance();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private boolean checkFormulaireValid() {
        String string_identifiant = identifiant.getEditText().getText().toString().trim();
        String string_password = password.getEditText().getText().toString().trim();

        if(string_identifiant.isEmpty()){
            identifiant.setError("Email is required");
            identifiant.requestFocus();
            return false;
        }

        if(string_password.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return false;
        }

        if(string_password.length() < 6){
            password.setError("Min Password length 6 characters");
            password.requestFocus();
            return false;
        }

        return true;
    }

    /**
     *
     */
    private void userLogin() {
       if(checkFormulaireValid()) {

           //TODO
       }

        /*
        mAuth.signInWithEmailAndPassword(string_email,string_password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,UserProfile.class);
                    intent.putExtra("email",string_email);
                    startActivity(intent);
                    Login.this.finish();
                }else {
                    Toast.makeText(Login.this,"Failed to login please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
         */
    }
}