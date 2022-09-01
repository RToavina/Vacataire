package itu.mbds.vacataire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private ImageView logo;
    private TextView textWelcome,textSign;
    private Button signUp,connect;
    private TextInputLayout identifiant,password;

    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        logo = (ImageView) findViewById(R.id.logo);
        textWelcome = (TextView) findViewById(R.id.texteWelcome);
        textSign = (TextView) findViewById(R.id.textSign);
        connect = (Button) findViewById(R.id.connect);
        identifiant = (TextInputLayout) findViewById(R.id.identifiant);
        password = (TextInputLayout) findViewById(R.id.password);

        //mAuth = FirebaseAuth.getInstance();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    /**
     *
     */
    private void userLogin() {
        String string_identifiant = identifiant.getEditText().getText().toString().trim();
        String string_password = password.getEditText().getText().toString().trim();

        if(string_identifiant.isEmpty()){
            identifiant.setError("Email is required");
            identifiant.requestFocus();
            return;
        }

        if(string_password.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if(string_password.length() < 6){
            password.setError("Min Password length 6 characters");
            password.requestFocus();
            return;
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