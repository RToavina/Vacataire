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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.models.Matiere;
import itu.mbds.vacataire.models.MatiereViewModel;
import itu.mbds.vacataire.models.ProfesseurViewModel;
import itu.mbds.vacataire.models.User;
import itu.mbds.vacataire.models.UserViewModel;

public class EmargementFragment extends Fragment {
    private MatiereViewModel matiereViewModel;
    private UserViewModel userViewModel;
    private ProfesseurViewModel professeurViewModel;

    private TextView matiere;
    TextInputLayout menu;
    String[] matieres = {"Français", "Mathématiques", "SVT", "Histoire", "Géographie", "EMC"};
    String[] professeurs = {"Toto", "Titi", "Tata"};
    private TextInputLayout dateTextView, heureDepartView, heureArriveView;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar hour = Calendar.getInstance();

    private LocalDate selectedDate;


    public EmargementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hour.set(Calendar.HOUR, 7);
        hour.set(Calendar.MINUTE, 0);
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

        matiere = getView().findViewById(R.id.matiere);
        heureDepartView = (TextInputLayout) getView().findViewById(R.id.heureDepart);
        heureArriveView = (TextInputLayout) getView().findViewById(R.id.heureArrive);
        dateTextView = (TextInputLayout) getView().findViewById(R.id.dateEmargement);

        selectedDate = (LocalDate) getArguments().getSerializable("selectedDate");
        if (selectedDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            myCalendar.set(Calendar.YEAR, selectedDate.getYear());
            myCalendar.set(Calendar.MONTH, selectedDate.getMonthValue() - 1);
            myCalendar.set(Calendar.DAY_OF_MONTH, selectedDate.getDayOfMonth());
            dateTextView.getEditText().setText(selectedDate.format(formatter));
        }

        professeurViewModel.getProfesseur().observe(getViewLifecycleOwner(), professeur -> {
            Log.d("professeur", professeur.username);
            if (professeur != null) {
                ArrayAdapter<Matiere> adapter =
                        new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_item, professeur.matieres);
                AutoCompleteTextView dropdownView = getView().findViewById(R.id.emargement_matiere_dropdown);
                dropdownView.setAdapter(adapter);
            }
        });

        initProfesseur();
        initDatePicker();
        initHeurePicker(heureDepartView.getEditText(), (Button) getView().findViewById(R.id.btn_heureDepart));
        initHeurePicker(heureArriveView.getEditText(), (Button) getView().findViewById(R.id.btn_heureArrive));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initProfesseur() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_item, professeurs);
        AutoCompleteTextView dropdownView =
                getView().findViewById(R.id.emargement_professeur_dropdown);
        dropdownView.setAdapter(adapter);
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
                hour.set(Calendar.HOUR_OF_DAY, hourOfDay);
                hour.set(Calendar.MINUTE, minute);
                updateLabelHeure(editText);
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create TimePickerDialog:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        timeSetListener, hour.get(Calendar.HOUR_OF_DAY),
                        hour.get(Calendar.MINUTE), true);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });
    }

    private void updateLabelHeure(EditText view) {
        String myFormat = "kk:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
        view.setText(dateFormat.format(hour.getTime()));
    }

    private void updateLabelDate() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateTextView.getEditText().setText(dateFormat.format(myCalendar.getTime()));
    }


}