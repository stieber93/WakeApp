package com.example.wakeappjsrp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeResultTime(View view) {
        EditText inputZeit = (EditText) findViewById(R.id.inputUhrzeit);
        EditText inputFahrtzeit = (EditText) findViewById(R.id.inputFahrtzeit);
        EditText inputZZF = (EditText) findViewById(R.id.inputZeitZumFertigmachen);
        String inputUhrzeitValue = inputZeit.getText().toString();
        String inputFahrtzeitValue = inputFahrtzeit.getText().toString();
        String inputZZFValue = inputZZF.getText().toString();
        Log.d("resultZeit", inputUhrzeitValue);
        Log.d("resultFahrtzeit", inputFahrtzeitValue);
        Log.d("resultZZF", inputZZFValue);
    }


}