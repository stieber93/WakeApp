package com.example.wakeappjsrp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
        timePicker.setEnabled(false);
        EditText showTimePicker = (EditText) findViewById(R.id.inputUhrzeit);
        showTimePicker.setOnClickListener((View.OnClickListener) this);
        //Test
        //test Nick
        //Test Domi
    }
    public void onClick(final View e) {
        View showTimePicker;
        if(e == showTimePicker){
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setEnabled(true);
        }
    }



}