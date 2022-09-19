package itu.mbds.vacataire.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import itu.mbds.vacataire.R;
import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.calendar.CalendarViewHolder;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<LocalDate> days;
    private List<LocalDate> emargements;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, List<LocalDate> emargements, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
        this.emargements = emargements;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = (int) parent.getHeight();

        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        final LocalDate date = days.get(position);
        if (date == null)
            holder.dayOfMonth.setText("");
        else {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            List<LocalDate> dateList = new ArrayList<>();
            //TODO mettre les dates en couleur
            dateList.add(LocalDate.of(2022,9,8));
            dateList.add(LocalDate.of(2022,9,18));
            emargements.forEach(localDate -> {
                if (date.equals(localDate)) {
                    holder.parentView.setBackgroundColor(Color.argb(190,231, 76, 60));
                }
            });
            if (date.equals(CalendarUtils.selectedDate)) {
                holder.parentView.setBackgroundColor(Color.LTGRAY);
            }
        }

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
