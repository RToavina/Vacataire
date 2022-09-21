package itu.mbds.vacataire.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.api.ApiEndpoint;
import itu.mbds.vacataire.api.ClientApi;
import itu.mbds.vacataire.models.Matiere;
import itu.mbds.vacataire.models.MatiereViewModel;
import itu.mbds.vacataire.models.MessageResponse;
import itu.mbds.vacataire.models.SignupRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupFragment extends Fragment {
    private MatiereViewModel matiereViewModel;
    private TextInputLayout reg_name,reg_prenom, reg_username, reg_email, reg_phoneNumber, reg_password;
    private Button regButton;
    private TextView matiere;
    boolean[] selectedMatiere;
    ArrayList<Integer> matiereList = new ArrayList<>();
    String[] matieres;


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
        matiereViewModel = new ViewModelProvider(requireActivity()).get(MatiereViewModel.class);
        reg_name = getView().findViewById(R.id.reg_name);
        reg_prenom = getView().findViewById(R.id.reg_prenom);
        reg_username = getView().findViewById(R.id.reg_username);
        reg_email = getView().findViewById(R.id.reg_email);
        reg_phoneNumber = getView().findViewById(R.id.reg_phoneNumber);
        reg_password = getView().findViewById(R.id.reg_password);
        regButton = getView().findViewById(R.id.regButton);
        matiere = getView().findViewById(R.id.matiere);

        matiereViewModel.getMatieres().observe(getViewLifecycleOwner(), matieres -> {
            if (matieres != null) {
               this.matieres = matieres.stream().map(m -> m.nomMatiere).toArray(String[]::new);
                selectedMatiere = new boolean[this.matieres.length];
                initMatiere();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void initMatiere() {

        matiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // set title
                builder.setTitle("Veuillez-choisir vos matières.");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(matieres, selectedMatiere, new DialogInterface.OnMultiChoiceClickListener() {
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

                builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < matiereList.size(); j++) {
                            // concat array value
                            stringBuilder.append(matieres[matiereList.get(j)]);
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

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Effacer tout", new DialogInterface.OnClickListener() {
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
    }


    private boolean checkFormulaire() {
        //get all the values
        String name = reg_name.getEditText().getText().toString().trim();
        String prenom = reg_name.getEditText().getText().toString().trim();
        String username = reg_username.getEditText().getText().toString().trim();
        String email = reg_email.getEditText().getText().toString().trim();
        String phoneNumber = reg_phoneNumber.getEditText().getText().toString().trim();
        String password = reg_password.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            reg_name.setError("Nom obligatoire");
            reg_name.requestFocus();
            return false;
        }

        if (prenom.isEmpty()) {
            reg_prenom.setError("Prénom obligatoire");
            reg_prenom.requestFocus();
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

        if(matiereList.size() < 1) {
            matiere.setError("Matière obligatoire");
            matiere.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Register User
     */
    private void registerUser() {
        if(checkFormulaire()) {
            ClientApi api = new ClientApi(getContext());
            ApiEndpoint service = api.create();
            Call<MessageResponse> call = service.signup(getSignupInfo());
            call.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Utilisateur créé", Toast.LENGTH_LONG).show();
                        NavDirections action = SignupFragmentDirections.actionSignupFragmentToLoginFragment();
                        Navigation.findNavController(getView()).navigate(action);
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        }
    }


    private SignupRequest getSignupInfo() {
        String name = reg_name.getEditText().getText().toString().trim();
        String prenom = reg_prenom.getEditText().getText().toString().trim();
        String username = reg_username.getEditText().getText().toString().trim();
        String email = reg_email.getEditText().getText().toString().trim();
        String phoneNumber = reg_phoneNumber.getEditText().getText().toString().trim();
        String password = reg_password.getEditText().getText().toString();
        List<String> matieres = matiereList.stream().map(i -> this.matieres[i]).collect(Collectors.toList());
        return new SignupRequest(username, email, password, name,prenom, phoneNumber, matieres);
    }

}