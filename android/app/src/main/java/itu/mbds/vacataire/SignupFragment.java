package itu.mbds.vacataire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;


public class SignupFragment extends Fragment {
    private TextInputLayout reg_name, reg_username, reg_email, reg_phoneNumber, reg_password;
    private Button regButton;
    private TextView matiere;
    boolean[] selectedMatiere;
    ArrayList<Integer> matiereList = new ArrayList<>();
    String[] matiereArray = {"Français", "Mathématiques", "SVT", "Histoire", "Géographie", "EMC"};

    //private FirebaseAuth mAuth;

    public SignupFragment() {
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reg_name = getView().findViewById(R.id.reg_name);
        reg_username = getView().findViewById(R.id.reg_username);
        reg_email = getView().findViewById(R.id.reg_email);
        reg_phoneNumber = getView().findViewById(R.id.reg_phoneNumber);
        reg_password = getView().findViewById(R.id.reg_password);
        regButton = getView().findViewById(R.id.regButton);
        matiere = getView().findViewById(R.id.matiere);

        selectedMatiere = new boolean[matiereArray.length];

        //mAuth = FirebaseAuth.getInstance();

        matiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
                //registerUser();
            }
        });
    }



    private boolean checkFormulaire() {
        //get all the values
        String name = reg_name.getEditText().getText().toString().trim();
        String username = reg_username.getEditText().getText().toString().trim();
        String email = reg_email.getEditText().getText().toString().trim();
        String phoneNumber = reg_phoneNumber.getEditText().getText().toString().trim();
        String password = reg_password.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            reg_name.setError("Nom obligatoire");
            reg_name.requestFocus();
            return false;
        }

        if (username.isEmpty()) {
            reg_username.setError("Identifiant obligatoire");
            reg_username.requestFocus();
             return false;

        }

        if (email.isEmpty()) {
            reg_email.setError("Email obligatoire");
            reg_email.requestFocus();
            return false;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            reg_email.setError("Email obligatoire");
            reg_email.requestFocus();
            return false;
        }

        if (phoneNumber.isEmpty()) {
            reg_phoneNumber.setError("Numéro de téléphone obligatoire");
            reg_phoneNumber.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            reg_password.setError("Mot de passe obligatoire");
            reg_password.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            reg_password.setError("Mot de passe trop court");
            reg_password.requestFocus();
            return false;
        }

        //TODO check aussi les matieres

        return true;
    }

    /**
     * Register User
     */
    private void registerUser() {

        if(checkFormulaire()) {
            //TODO
        }

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