package com.example.mareu.utils;

import android.view.View;
import android.widget.TextView;
import org.hamcrest.Matcher;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

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
        ((TextView) view).setText(value);
    }

    public String getValue() {
        return value;
    }
}
