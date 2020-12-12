package com.example.mareu.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mareu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static int getImageDrawable() {

        List<Integer> drawables = new ArrayList<>();

        drawables.add(R.drawable.ic_first_color);
        drawables.add(R.drawable.ic_second_color);
        drawables.add(R.drawable.ic_third_color);

        return drawables.get(new Random().nextInt(drawables.size()));
    }

    public static void makeToast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
