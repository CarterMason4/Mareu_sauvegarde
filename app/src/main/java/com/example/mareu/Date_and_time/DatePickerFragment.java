package com.example.mareu.Date_and_time;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        int DAY = Calendar.DAY_OF_MONTH;
        int MONTH = Calendar.MONTH;
        int YEAR = Calendar.YEAR;

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), YEAR, MONTH, DAY);
    }
}
