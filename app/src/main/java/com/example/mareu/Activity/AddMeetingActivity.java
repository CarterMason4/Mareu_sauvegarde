package com.example.mareu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.Di;
import com.example.mareu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity{

    @Nullable
    @BindView(R.id.date)
    TextView date;

    @Nullable
    @BindView(R.id.heure)
    TextView heure;

    @Nullable
    @BindView(R.id.editTextSalle)
    AutoCompleteTextView editTextSalle;

    @Nullable
    @BindView(R.id.editTextViewIntervenants)
    MultiAutoCompleteTextView editTextIntervenants;

    @Nullable
    @BindView(R.id.editTextAPropos)
    TextInputEditText editTextAPropos;

    @Nullable
    @BindView(R.id.valider_button)
    MaterialButton button;

    private MeetingApiService apiService;
    private Toolbar toolbar;

    private int mHour;
    private int mMinutes;

    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);

        orientation = getResources().getConfiguration().orientation;

        setUpViews();

        setSupportActionBar(toolbar);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        apiService = Di.getMeetingApiService();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getMenuInflater().inflate(R.menu.menu_ajoute_reunion_landscape, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.validerItem) {

            validateData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.valider_button)
    public void valider() {

        validateData();

        finish();
    }

    @OnClick(R.id.date)
    public void openDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this, android.R.style.Theme_DeviceDefault_Dialog) {
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
                String currentDate = String.valueOf(dayOfMonth) + '/' + (month + 1) + '/' + year;
                date.setText(currentDate);
            }
        };

        dialog.show();
    }

    @OnClick(R.id.heure)
    public void openTimePicker() {
        TimePickerDialog dialog = new TimePickerDialog(AddMeetingActivity.this, (view, hourOfDay, minute) -> {
            Calendar calendar = Calendar.getInstance();

            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinutes = calendar.get(Calendar.MINUTE);

            String currentTime = String.valueOf(hourOfDay) + 'h' + minute;
            heure.setText(currentTime);
        }, mHour, mMinutes, true);

        dialog.show();
    }

    /**
     * Est appelé à chaque que l'activité est appelée.
     * */

    private void setUpViews() {

        Date myDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm", Locale.FRANCE);

        date.setText(formatDate.format(myDate));
        heure.setText(formatHeure.format(myDate));

        if(orientation ==  Configuration.ORIENTATION_PORTRAIT) {
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

            editTextIntervenants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

            toolbar = findViewById(R.id.meetingToolbar);
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            button.setVisibility(View.GONE);
            toolbar = findViewById(R.id.land);
        }

    }




    private void validateData() {
        // TODO C'est ici qu'on doit implémenter la logique.

        // TODO Vérifier qu'aucun champ n'est vide.
        // TODO Vérifier qu'il y ait bien au moins 2 intervenants en comptant les '@'
        // TODO Vérifier que le champ "Sujet" ne soit pas trop court.


        if(editTextSalle.getText().toString().isEmpty() ||
                editTextIntervenants.getText().toString().isEmpty() ||
            editTextAPropos.getText().toString().isEmpty()) {

            makeToast("Vous avez oublié un ou plusieurs champs.");

        } else if(getNumberOfSpeakers(editTextIntervenants.getText().toString()) < 2) {

            makeToast("Pas assez d'intervants pour créer une réunion.");

        } else if(editTextAPropos.getText().toString().length() <= 3) {
            makeToast("Sujet de réunion trop court.");
        }
    }

    private int getNumberOfSpeakers(String string) {
        int nombre = 0;

        for(int i = 0 ; i < string.length() ; i++) {
            if(string.charAt(i) == '@') {
                nombre++;
            }
        }

        return nombre;
    }

    private void makeToast(String string) {
        Toast toast = Toast.makeText(this, string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }


}