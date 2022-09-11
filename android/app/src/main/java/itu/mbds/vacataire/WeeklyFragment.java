package itu.mbds.vacataire;

import static itu.mbds.vacataire.calendar.CalendarUtils.daysInWeekArray;
import static itu.mbds.vacataire.calendar.CalendarUtils.monthYearFromDate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import itu.mbds.vacataire.adapter.CalendarAdapter;
import itu.mbds.vacataire.adapter.EmargementAdapter;
import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.models.Emargement;


public class WeeklyFragment extends Fragment implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

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

        previousButton.setOnClickListener(this::previousWeekAction);
        nextButton.setOnClickListener(this::nextWeekAction);
        emargerButton.setOnClickListener(this::emargerAction);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidgets();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = getView().findViewById(R.id.calendarRecyclerView);
        monthYearText = getView().findViewById(R.id.monthYearTV);
        eventListView = getView().findViewById(R.id.eventListView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEmargemntAdpater();
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
        setEmargemntAdpater();
    }

    private void setEmargemntAdpater() {
        ArrayList<Emargement> emargements = new ArrayList<>();
        emargements.add(new Emargement("test", CalendarUtils.selectedDate));
        emargements.add(new Emargement("test", CalendarUtils.selectedDate));
        emargements.add(new Emargement("test", CalendarUtils.selectedDate));
        //TODO get tous les emargements via la date selectionnée
        EmargementAdapter adapter = new EmargementAdapter(getContext(), emargements);
        eventListView.setAdapter(adapter);
    }

    public void emargerAction(View view) {
        NavDirections action = WeeklyFragmentDirections.actionWeeklyFragmentToEmargementFragment();
        Navigation.findNavController(view).navigate(action);
    }
}