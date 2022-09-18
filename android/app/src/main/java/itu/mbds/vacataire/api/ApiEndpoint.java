package itu.mbds.vacataire.api;

import java.util.List;

import itu.mbds.vacataire.models.Matiere;
import itu.mbds.vacataire.models.MessageResponse;
import itu.mbds.vacataire.models.SignupRequest;
import itu.mbds.vacataire.models.User;
import itu.mbds.vacataire.models.UserRequest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndpoint {

    // Matiere endpoints
    @GET("matieres")
    Call<List<Matiere>> getMatieres();

    @GET("matiere/{id}")
    Call<Matiere> getMatiere(@Path("id") Long id);

    @POST("matiere")
    Call<Matiere> addMatiere(@Body Matiere matiere);

    @DELETE("matiere/{id}")
    Call<Response> deleteMatiere(@Path("id") Long id);

    //Authentication
    @POST("auth/signin")
    Call<User> authenticateUser(@Body UserRequest user);

    @POST("auth/signout")
    Call<MessageResponse> signout();

    //Signup
    @POST("auth/signup")
    Call<MessageResponse> signup(@Body SignupRequest signup);
}
