package itu.mbds.vacataire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;

public class Signup extends AppCompatActivity {

    private TextInputLayout reg_name, reg_username, reg_email, reg_phoneNumber, reg_password;
    private Button regButton;
    private TextView matiere;
    boolean[] selectedMatiere;
    ArrayList<Integer> matiereList = new ArrayList<>();
    String[] matiereArray = {"Français", "Mathématiques", "SVT", "Histoire", "Géographie", "EMC"};

    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        reg_name = findViewById(R.id.reg_name);
        reg_username = findViewById(R.id.reg_username);
        reg_email = findViewById(R.id.reg_email);
        reg_phoneNumber = findViewById(R.id.reg_phoneNumber);
        reg_password = findViewById(R.id.reg_password);
        regButton = findViewById(R.id.regButton);
        matiere = findViewById(R.id.matiere);

        selectedMatiere = new boolean[matiereArray.length];

        //mAuth = FirebaseAuth.getInstance();

        matiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);

                // set title
                builder.setTitle("Veuillez-choisir votre matière.");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(matiereArray, selectedMatiere, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            matiereList.add(i);
                            // Sort array list
                            Collections.sort(matiereList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            matiereList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < matiereList.size(); j++) {
                            // concat array value
                            stringBuilder.append(matiereArray[matiereList.get(j)]);
                            // check condition
                            if (j != matiereList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        matiere.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedMatiere.length; j++) {
                            // remove all selection
                            selectedMatiere[j] = false;
                            // clear language list
                            matiereList.clear();
                            // clear text view value
                            matiere.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    /**
     * Register User
     */
    private void registerUser() {
        //get all the values
        String name = reg_name.getEditText().getText().toString().trim();
        String username = reg_username.getEditText().getText().toString().trim();
        String email = reg_email.getEditText().getText().toString().trim();
        String phoneNumber = reg_phoneNumber.getEditText().getText().toString().trim();
        String password = reg_password.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            reg_name.setError("Nom obligatoire");
            reg_name.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            reg_username.setError("Identifiant obligatoire");
            reg_username.requestFocus();
            return;

        }

        if (email.isEmpty()) {
            reg_email.setError("Email obligatoire");
            reg_email.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            reg_email.setError("Email obligatoire");
            reg_email.requestFocus();
            return;
        }

        if (phoneNumber.isEmpty()) {
            reg_phoneNumber.setError("Numéro de téléphone obligatoire");
            reg_phoneNumber.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            reg_password.setError("Mot de passe obligatoire");
            reg_password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            reg_password.setError("Mot de passe trop court");
            reg_password.requestFocus();
            return;
        }

        System.out.println(email + " " + password);

        /*
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("Authentification ...");
                if(task.isSuccessful()){
                    UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNumber,password);
                    FirebaseDatabase.getInstance().getReference("users").child(phoneNumber).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            System.out.println("Database find Success ...");
                            if(task.isSuccessful()){
                                Toast.makeText(Signup.this,"Register OK",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Signup.this,Login.class);
                                startActivity(intent);
                                Signup.this.finish();
                            }else {
                                Toast.makeText(Signup.this,"Register Fail",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Signup.this,"Register Fail",Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }
}