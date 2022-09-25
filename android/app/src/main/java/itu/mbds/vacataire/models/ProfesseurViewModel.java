package itu.mbds.vacataire.models;

import android.app.Application;
import android.util.Log;
import android.util.Property;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import itu.mbds.vacataire.api.ApiEndpoint;
import itu.mbds.vacataire.api.ClientApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfesseurViewModel extends AndroidViewModel {

    private MutableLiveData<Professeur> professeur;

    public ProfesseurViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Professeur> getProfesseur() {
        if(professeur == null) {
            professeur = new MutableLiveData<>();
        }
        return professeur;
    }

    public void setValue(Professeur p) {
        if(professeur == null) {
            professeur = new MutableLiveData<>();
        }
        professeur.setValue(p);
    }


    public void loadProfesseur(User user) {
        if (user != null) {
            ClientApi api = new ClientApi(getApplication());
            ApiEndpoint service = api.create();
            Call<ProfesseurResponse> call = service.getProfesseur(user.username);
            call.enqueue(new Callback<ProfesseurResponse>() {
                @Override
                public void onResponse(Call<ProfesseurResponse> call, Response<ProfesseurResponse> response) {
                    if (response.isSuccessful()) {
                        if(professeur == null) {
                            professeur = new MutableLiveData<>();
                        }
                        professeur.setValue(new Professeur(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<ProfesseurResponse> call, Throwable t) {
                    Log.e("err",t.getMessage());
                }
            });
        }
    }
}
