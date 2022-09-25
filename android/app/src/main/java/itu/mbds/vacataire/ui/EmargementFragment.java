package itu.mbds.vacataire.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.api.ApiEndpoint;
import itu.mbds.vacataire.api.ClientApi;
import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.models.Emargement;
import itu.mbds.vacataire.models.EmargementRequest;
import itu.mbds.vacataire.models.EmargementString;
import itu.mbds.vacataire.models.Matiere;
import itu.mbds.vacataire.models.MatiereViewModel;
import itu.mbds.vacataire.models.Professeur;
import itu.mbds.vacataire.models.ProfesseurViewModel;
import itu.mbds.vacataire.models.User;
import itu.mbds.vacataire.models.UserViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmargementFragment extends Fragment {
    private MatiereViewModel matiereViewModel;
    private UserViewModel userViewModel;
    private ProfesseurViewModel professeurViewModel;
    private Professeur professeur;
    private Button emargerButton;

    TextInputLayout matiereView;
    private TextInputLayout dateTextView, heureDepartView, heureArriveView;
    final Calendar myCalendar = Calendar.getInstance();
    LocalTime depart, fin;

    private LocalDate selectedDate;
    private Emargement selectedEmargement;

    public EmargementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        depart = LocalTime.of(7,0);
        fin = LocalTime.of(7,0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emargement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        matiereViewModel = new ViewModelProvider(requireActivity()).get(MatiereViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        professeurViewModel = new ViewModelProvider(requireActivity()).get(ProfesseurViewModel.class);
        emargerButton = getView().findViewById(R.id.emargerButton);
        matiereView = getView().findViewById(R.id.emargement_matiere);

        heureDepartView = (TextInputLayout) getView().findViewById(R.id.heureDepart);
        heureArriveView = (TextInputLayout) getView().findViewById(R.id.heureArrive);
        dateTextView = (TextInputLayout) getView().findViewById(R.id.dateEmargement);

        selectedDate = (LocalDate) getArguments().getSerializable("selectedDate");
        selectedEmargement = (Emargement) getArguments().getSerializable("selectedEmargement");
        if (selectedDate != null) {

            myCalendar.set(Calendar.YEAR, selectedDate.getYear());
            myCalendar.set(Calendar.MONTH, selectedDate.getMonthValue() - 1);
            myCalendar.set(Calendar.DAY_OF_MONTH, selectedDate.getDayOfMonth());
            dateTextView.getEditText().setText(CalendarUtils.formattedDate2(selectedDate));
        }
        if (selectedEmargement != null) {
            dateTextView.getEditText().setText(CalendarUtils.formattedDate2(selectedEmargement.getDate()));

            heureArriveView.getEditText().setText(CalendarUtils.formattedTime(selectedEmargement.getDebut()));
            heureDepartView.getEditText().setText(CalendarUtils.formattedTime(selectedEmargement.getFin()));
            matiereView.getEditText().setText(selectedEmargement.getMatiere().nomMatiere);
            //disable
            matiereView.setEnabled(false);
            dateTextView.setEnabled(false);
            getView().findViewById(R.id.btn_dateEmargement).setEnabled(false);

        }

        professeurViewModel.getProfesseur().observe(getViewLifecycleOwner(), professeur -> {
            if (professeur != null) {
                this.professeur = professeur;
                ArrayAdapter<Matiere> adapter =
                        new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_item, professeur.matieres);
                AutoCompleteTextView dropdownView = getView().findViewById(R.id.emargement_matiere_dropdown);
                dropdownView.setAdapter(adapter);
            }
        });

        emargerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmargement();
            }
        });

        initDatePicker();
        initHeurePicker(heureDepartView.getEditText(), (Button) getView().findViewById(R.id.btn_heureDepart));
        initHeurePicker(heureArriveView.getEditText(), (Button) getView().findViewById(R.id.btn_heureArrive));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initDatePicker() {
        Button button = (Button) getView().findViewById(R.id.btn_dateEmargement);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabelDate();
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getContext(),
                                date, myCalendar.get(Calendar.YEAR),
                                myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void initHeurePicker(EditText editText, Button button) {
        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                depart = LocalTime.of(hourOfDay, minute);
                updateLabelHeure(editText);
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalTime cur = LocalTime.of(7,0);
                if (editText.getText().toString().length() > 0 && CalendarUtils.isValidFormat("kk:mm", editText.getText().toString())) {
                    cur = LocalTime.parse(editText.getText().toString());
                }
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        timeSetListener, cur.getHour(),
                        cur.getMinute(), true);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });
    }

    private void updateLabelHeure(EditText view) {
        view.setText(CalendarUtils.formattedTime(depart));
    }

    private void updateLabelDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateTextView.getEditText().setText(dateFormat.format(myCalendar.getTime()));
    }

    private boolean checkFormulaire() {
        String date = dateTextView.getEditText().getText().toString();
        String debut = heureDepartView.getEditText().getText().toString();
        String fin = heureArriveView.getEditText().getText().toString();
        String nomMatiere = matiereView.getEditText().getText().toString();
        boolean isvalid = true;
        //matiere
        if (nomMatiere.isEmpty()) {
            matiereView.setError("Matiere obligatoire");
            matiereView.requestFocus();
            isvalid = false;
        }
        //date
        if (date.isEmpty() || !CalendarUtils.isValidFormat("dd/MM/yyyy", date)) {
            dateTextView.setError("Date obligatoire");
            dateTextView.requestFocus();
            isvalid = false;
        }

        //debut
        if (debut.isEmpty() || !CalendarUtils.isValidFormat("kk:mm", debut)) {
            heureDepartView.setError("Heure obligatoire");
            heureDepartView.requestFocus();
            isvalid = false;
        }

        //fin
        if (fin.isEmpty() || !CalendarUtils.isValidFormat("kk:mm", fin)) {
            heureArriveView.setError("Heure obligatoire");
            heureArriveView.requestFocus();
            isvalid = false;
        }
        return isvalid;
    }

    private void saveEmargement() {
        if (checkFormulaire()) {
            EmargementRequest request =  getEmargement();
            ClientApi api = new ClientApi(getContext());
            ApiEndpoint service = api.create();
            Call<EmargementString> call = service.saveEmargement(request);
            call.enqueue(new Callback<EmargementString>() {
                @Override
                public void onResponse(Call<EmargementString> call, Response<EmargementString> response) {
                    if (response.isSuccessful()) {
                        Emargement emargement = EmargementString.toEmargement(response.body());
                        professeur.emargements = professeur.emargements.stream().filter(e -> e.equals(emargement)).collect(Collectors.toList());
                        professeur.emargements.add(emargement);
                        professeurViewModel.setValue(professeur);
                        Toast.makeText(getContext(), "Emagement ok", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(R.id.calendarFragment);
                    }
                }

                @Override
                public void onFailure(Call<EmargementString> call, Throwable t) {

                }
            });
        }
    }

    private EmargementRequest getEmargement() {
        String date = dateTextView.getEditText().getText().toString();
        String debut = heureArriveView.getEditText().getText().toString();
        String fin = heureDepartView.getEditText().getText().toString();
        String nomMatiere = matiereView.getEditText().getText().toString();
        String username = professeur.username;
        Long id = null;
        if (selectedEmargement != null) {
            id = selectedEmargement.getId();
        }
        return new EmargementRequest(id, date,username,debut,fin,nomMatiere, false);
    }


}