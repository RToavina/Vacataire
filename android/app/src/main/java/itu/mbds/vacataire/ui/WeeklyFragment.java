package itu.mbds.vacataire.ui;

import static itu.mbds.vacataire.calendar.CalendarUtils.daysInWeekArray;
import static itu.mbds.vacataire.calendar.CalendarUtils.monthYearFromDate;
import static itu.mbds.vacataire.calendar.CalendarUtils.selectedDate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.adapter.CalendarAdapter;
import itu.mbds.vacataire.adapter.EmargementAdapter;
import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.models.Emargement;
import itu.mbds.vacataire.models.ProfesseurViewModel;


public class WeeklyFragment extends Fragment implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private ProfesseurViewModel professeurViewModel;
    private List<Emargement> emargements;

    public WeeklyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        Button previousButton = (Button) view.findViewById(R.id.weekly_btn_previous);
        Button nextButton = (Button) view.findViewById(R.id.weekly_btn_next);
        Button emargerButton = (Button) view.findViewById(R.id.weekly_btn_emarger);
        Button monthlyButton = (Button) view.findViewById(R.id.calendar_btn_monthly);


        previousButton.setOnClickListener(this::previousWeekAction);
        nextButton.setOnClickListener(this::nextWeekAction);
        emargerButton.setOnClickListener(this::emargerAction);
        monthlyButton.setOnClickListener(this::monthly);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        professeurViewModel = new ViewModelProvider(requireActivity()).get(ProfesseurViewModel.class);
        professeurViewModel.getProfesseur().observe(getViewLifecycleOwner(), professeur -> {
            emargements = professeur.emargements;
            setWeekView();
        });
        initWidgets();
        setWeekView();
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Emargement emargement = (Emargement) adapterView.getItemAtPosition(i);
                NavDirections action = WeeklyFragmentDirections.actionWeeklyFragmentToEmargementFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedEmargement", emargement);
                Navigation.findNavController(view).navigate(action.getActionId(), bundle);
            }
        });
    }

    private void initWidgets() {
        calendarRecyclerView = getView().findViewById(R.id.calendarRecyclerView);
        monthYearText = getView().findViewById(R.id.monthYearTV);
        eventListView = getView().findViewById(R.id.eventListView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);
        List<LocalDate> e = new ArrayList<>();
        if(emargements != null) {
           e = emargements.stream().map(emargement ->
                    emargement.getDate()
            ).collect(Collectors.toList());
        }
        CalendarAdapter calendarAdapter = new CalendarAdapter(days,e, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEmargementAdpater();
    }


    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEmargementAdpater();
    }

    private void setEmargementAdpater() {
        if(emargements != null) {
            EmargementAdapter adapter = new EmargementAdapter(getContext(), emargements.stream()
                    .filter(emargement -> emargement.getDate().isEqual(selectedDate)).collect(Collectors.toList()));
            eventListView.setAdapter(adapter);
        }
    }

    public void emargerAction(View view) {
        NavDirections action = WeeklyFragmentDirections.actionWeeklyFragmentToEmargementFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedDate", CalendarUtils.selectedDate);
        Navigation.findNavController(view).navigate(action.getActionId(), bundle);
    }

    public void monthly(View view) {
        NavDirections action = WeeklyFragmentDirections.actionWeeklyFragmentToCalendarFragment();
        Navigation.findNavController(view).navigate(action.getActionId());
    }
}