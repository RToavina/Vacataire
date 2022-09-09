package itu.mbds.vacataire.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import itu.mbds.vacataire.calendar.CalendarUtils;
import itu.mbds.vacataire.models.Emargement;
import itu.mbds.vacataire.R;

public class EmargementAdapter extends ArrayAdapter<Emargement> {
    public EmargementAdapter(@NonNull Context context, List<Emargement> emargements)
    {
        super(context, 0, emargements);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Emargement emargement = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView emargementMatiere = convertView.findViewById(R.id.emargementMatiere);
        TextView emargementDate = convertView.findViewById(R.id.emargementDate);

        String matiere = emargement.getMatiere();
        String date = CalendarUtils.formattedDate(emargement.getDate());
        emargementMatiere.setText(matiere);
        emargementDate.setText(date);
        return convertView;
    }
}
