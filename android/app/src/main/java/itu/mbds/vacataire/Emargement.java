package itu.mbds.vacataire;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Emargement extends AppCompatActivity {

    private TextView matiere;
    boolean[] selectedMatiere;
    ArrayList<Integer> matiereList = new ArrayList<>();
    String[] matiereArray = {"Français", "Mathématiques", "SVT", "Histoire", "Géographie", "EMC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emargement);

        matiere = findViewById(R.id.matiere);

        selectedMatiere = new boolean[matiereArray.length];

        matiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Emargement.this);

                // set title
                builder.setTitle("Veuillez-choisir votre matière.");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(matiereArray, selectedMatiere, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            matiereList.add(i);
                            // Sort array list
                            Collections.sort(matiereList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            matiereList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < matiereList.size(); j++) {
                            // concat array value
                            stringBuilder.append(matiereArray[matiereList.get(j)]);
                            // check condition
                            if (j != matiereList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        matiere.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedMatiere.length; j++) {
                            // remove all selection
                            selectedMatiere[j] = false;
                            // clear language list
                            matiereList.clear();
                            // clear text view value
                            matiere.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }
}