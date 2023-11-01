package com.example.que6;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner campusSpinner;
    private Spinner subOptionsSpinner;
    private TextView subOptionsText;

    private String[] subOptions = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campusSpinner = findViewById(R.id.campus_spinner);
        subOptionsSpinner = findViewById(R.id.sub_options_spinner);
        subOptionsText = findViewById(R.id.sub_options_text);

        ArrayAdapter<CharSequence> mainCampusAdapter = ArrayAdapter.createFromResource(this, R.array.main_campus_options, android.R.layout.simple_spinner_item);
        mainCampusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(mainCampusAdapter);

        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCampus = campusSpinner.getSelectedItem().toString();
                if (!selectedCampus.equals("Select Campus")) {
                    if (selectedCampus.equals("RMD Technical Campus")) {
                        subOptions = getResources().getStringArray(R.array.sub_options_rmd_technical);
                    } else if (selectedCampus.equals("RMD Medical Campus")) {
                        subOptions = getResources().getStringArray(R.array.sub_options_rmd_medical);
                    }
                    updateSubOptions();
                } else {
                    subOptionsText.setVisibility(View.INVISIBLE);
                    subOptionsSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                subOptionsText.setVisibility(View.INVISIBLE);
                subOptionsSpinner.setVisibility(View.GONE);
            }
        });

        subOptionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle sub-options selection if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when nothing is selected in sub-options Spinner
            }
        });
    }

    private void updateSubOptions() {
        ArrayAdapter<String> subOptionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subOptions);
        subOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subOptionsSpinner.setAdapter(subOptionsAdapter);

        subOptionsSpinner.setVisibility(View.VISIBLE);
        subOptionsText.setVisibility(View.VISIBLE);
    }
}
