package itu.mbds.vacataire.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import itu.mbds.vacataire.api.ApiEndpoint;
import itu.mbds.vacataire.api.ClientApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatiereViewModel extends AndroidViewModel {
    private MutableLiveData<List<Matiere>> matieres;

    public MatiereViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Matiere>> getMatieres() {
        if (matieres == null) {
            matieres = new MutableLiveData<List<Matiere>>();
            loadMatieres();
        }
        return matieres;
    }

   private void loadMatieres() {
        ClientApi api = new ClientApi(getApplication());
        ApiEndpoint service = api.create();
        Call<List<Matiere>> call = service.getMatieres();
        call.enqueue(new Callback<List<Matiere>>() {
            @Override
            public void onResponse(Call<List<Matiere>> call, Response<List<Matiere>> response) {
                if (response.isSuccessful()) {
                    matieres.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Matiere>> call, Throwable t) {
            }
        });
    }

}
