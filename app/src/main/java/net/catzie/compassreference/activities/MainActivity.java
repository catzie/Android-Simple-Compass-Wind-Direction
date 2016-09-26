package net.catzie.compassreference.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;

import net.catzie.compassreference.R;
import net.catzie.compassreference.customViews.CompassNeedle;

public class MainActivity extends AppCompatActivity {

    private CompassNeedle compassNeedle;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compassNeedle = (CompassNeedle) findViewById(R.id.compassNeedle);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(360);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                Log.d("num","num="+newVal);
                compassNeedle.setRotation(newVal);
            }
        });
    }
}
