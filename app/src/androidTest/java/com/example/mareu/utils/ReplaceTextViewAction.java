package com.example.mareu.utils;

import android.view.View;
import android.widget.TextView;

import com.example.mareu.R;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class ReplaceTextViewAction implements ViewAction {

    private final String value;

    public ReplaceTextViewAction(String value) {
        this.value = value;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isDisplayed();
    }

    @Override
    public String getDescription() {
        return "replace text";
    }

    @Override
    public void perform(UiController uiController, View view) {
       /* View textView = view.findViewById(id);
        ((TextView) textView).setText(value);*/
        ((TextView) view).setText(value);
    }

    public String getValue() {
        return value;
    }
}
