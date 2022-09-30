package itu.mbds.vacataire.ui;

import static itu.mbds.vacataire.calendar.CalendarUtils.daysInMonthArray;
import static itu.mbds.vacataire.calendar.CalendarUtils.monthYearFromDate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.adapter.CalendarAdapter;
import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.models.Emargement;
import itu.mbds.vacataire.models.ProfesseurViewModel;
import itu.mbds.vacataire.models.UserViewModel;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private UserViewModel userViewModel;
    private ProfesseurViewModel professeurViewModel;
    private List<Emargement> emargements;

    public CalendarFragment() {
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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        Button previousButton = (Button) view.findViewById(R.id.calendar_btn_previous);
        Button nextButton = (Button) view.findViewById(R.id.calendar_btn_next);
        Button weeklyButton = (Button) view.findViewById(R.id.calendar_btn_weekly);
        FloatingActionButton emarger = (FloatingActionButton) view.findViewById(R.id.btn_plus_emarger);
        professeurViewModel = new ViewModelProvider(requireActivity()).get(ProfesseurViewModel.class);
        professeurViewModel.getProfesseur().observe(getViewLifecycleOwner(), professeur -> {
            emargements = professeur.emargements;
            setMonthView();
        });

        previousButton.setOnClickListener(this::previousMonthAction);
        nextButton.setOnClickListener(this::nextMonthAction);
        weeklyButton.setOnClickListener(this::weeklyAction);
        emarger.setOnClickListener(this::emargerAction);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        final NavController navController = Navigation.findNavController(view);
        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
                navController.navigate(R.id.loginFragment);
            }
        });

        professeurViewModel = new ViewModelProvider(requireActivity()).get(ProfesseurViewModel.class);

        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = getView().findViewById(R.id.calendarRecyclerView);
        monthYearText = getView().findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);
        List<LocalDate> e = new ArrayList<>();
        if(emargements != null) {
            e = emargements.stream().map(emargement ->
                    emargement.getDate()
            ).collect(Collectors.toList());
        }
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,e, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view) {
        NavDirections action = CalendarFragmentDirections.actionCalendarFragmentToWeeklyFragment();
        Navigation.findNavController(view).navigate(action);
    }

    public void emargerAction(View view) {
        NavDirections action = CalendarFragmentDirections.actionCalendarFragmentToEmargementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedDate", CalendarUtils.selectedDate);
        Navigation.findNavController(view).navigate(action.getActionId(), bundle);
    }

}