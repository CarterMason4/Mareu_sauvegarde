package com.example.mareu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.mareu.Utils.Utils.getImageDrawable;
import static com.example.mareu.Utils.Utils.makeToast;

public class AddMeetingActivity extends AppCompatActivity {

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
    private List<Meeting> meetings;
    private Toolbar toolbar;

    private List<String> currentEntrants;


    private int mHour;
    private int mMinutes;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String currentDate;
    private String currentTime;

    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);

        orientation = getResources().getConfiguration().orientation;
        currentEntrants = new ArrayList<>();

        setUpViews();

        setSupportActionBar(toolbar);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        apiService = DI.getMeetingApiService();
        meetings = apiService.getAllMeetings();
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
    }

    @OnClick(R.id.date)
    public void openDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this, android.R.style.Theme_DeviceDefault_Dialog) {
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {

                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;

                currentDate = String.valueOf(dayOfMonth) + '/' + (month + 1) + '/' + year;
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

             currentTime = String.valueOf(hourOfDay) + 'h' + minute;
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

        currentDate = formatDate.format(myDate);
        currentTime = formatHeure.format(myDate);

        date.setText(formatDate.format(myDate));
        heure.setText(formatHeure.format(myDate));

        if(orientation ==  Configuration.ORIENTATION_PORTRAIT) {
            ArrayAdapter<String> salleAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.salles));

            editTextSalle.setAdapter(salleAdapter);

            // TODO il faut faire en sorte que le même intervenant n'apparaisse pas deux fois.

            ArrayAdapter<String> intervenantsAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    Utils.entrants());

            editTextIntervenants.setAdapter(intervenantsAdapter);

            editTextIntervenants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

            editTextIntervenants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // editTextIntervenants.getText().toString().contains(Utils.entrants().get())
                }
            });

            toolbar = findViewById(R.id.meetingToolbar);
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            button.setVisibility(View.GONE);
            toolbar = findViewById(R.id.landscape_toolbar);
        }
    }

    private void validateData() {
        if(editTextSalle.getText().toString().isEmpty() ||
                editTextIntervenants.getText().toString().isEmpty() ||
            editTextAPropos.getText().toString().isEmpty()) {

            makeToast(getApplicationContext(), getString(R.string.champs_oublies));

        } else if(getNumberOfSpeakers(editTextIntervenants.getText().toString()) < 2) {

            makeToast(getApplicationContext(), getString(R.string.pas_assez_intervenants));

        } else if(editTextAPropos.getText().toString().length() < 4) {
            makeToast(getApplicationContext(), getString(R.string.sujet_trop_court));

        }  else {
            apiService.addMeeting(new Meeting(
                meetings.size() + 1,
                    getImageDrawable(),
                    currentDate,
                    currentTime,
                    editTextSalle.getText().toString(),
                    editTextIntervenants.getText().toString(),
                    editTextAPropos.getText().toString()));

            makeToast(getApplicationContext(), getString(R.string.reunion_ajoute));

            finish();
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

    private boolean isSpeakerPresentTwice() {

        boolean isPresentTwice = false;

        Map<String, Integer> entrants = new LinkedHashMap<>();

        for(String entrant : Utils.entrants()) {
            entrants.put(entrant, 0);
        }

        String allOfEntrants = editTextIntervenants.getText().toString();

        return false;
    }
}