package com.example.mareu.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.Di;
import com.example.mareu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {


    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.heure)
    TextView heure;

    @BindView(R.id.editTextSalle)
    AutoCompleteTextView editTextSalle;

    @BindView(R.id.editTextViewIntervenants)
    MultiAutoCompleteTextView editTextIntervenants;

    @BindView(R.id.editTextAPropos)
    TextInputEditText editTextAPropos;

    private MeetingApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        ButterKnife.bind(this);
        apiService = Di.getMeetingApiService();

        setUpViews();
    }

    /**
     * Est appelé à chaque que l'activité est appelée.
     * */

    private void setUpViews() {

        Date myDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM//yyyy", Locale.FRANCE);
        SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm", Locale.FRANCE);

        date.setText(formatDate.format(myDate));
        heure.setText(formatHeure.format(myDate));

        ArrayAdapter<String> salleAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.salles));

        editTextSalle.setAdapter(salleAdapter);

        ArrayAdapter<String> intervenantsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.emails));

        editTextIntervenants.setAdapter(intervenantsAdapter);
    }

    // Dans cette méthode, on implémente la logique de traitement des données.

    @OnClick(R.id.valider_button)
    public void valider() {
        // TODO Ici on doit implémenter la logique.

        // Quelles sont les étapes ?

        // TODO Implémenter les clickListener sur les TextView.




    }
}