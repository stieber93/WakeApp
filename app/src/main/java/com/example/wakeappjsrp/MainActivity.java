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
        EditText resultField = (EditText) findViewById(R.id.showResult);
        String inputUhrzeitValue = inputZeit.getText().toString();
        String inputFahrtzeitValue = inputFahrtzeit.getText().toString();
        String inputZZFValue = inputZZF.getText().toString();
        int inputHours = Integer.valueOf(inputUhrzeitValue.substring(0,2));
        int inputMinutes = Integer.valueOf(inputUhrzeitValue.substring(3,5));
        int tempTime = (inputHours *60)+inputMinutes - Integer.valueOf(inputFahrtzeitValue) - Integer.valueOf(inputZZFValue);
        String resultTime ="";
        if(tempTime > 0) {

            resultTime = +tempTime / 60 + ":" + tempTime % 60;
        }
        if(tempTime < 0){
            tempTime += (24 *60);
            resultTime = +tempTime/60 +":" +tempTime%60 + " am Vortag";
;        }
        if (tempTime<  0){
            tempTime += (24*60);
            resultTime = +tempTime/60 +":" +tempTime%60 + " zwei Tage vorher";
        }
        //Log.d("resultZeit", inputUhrzeitValue);
        //Log.d("Hours", String.valueOf(inputHours));
        //Log.d("Minutes", String.valueOf(inputMinutes));
        Log.d("Result", resultTime);
        resultField.setText(resultTime);
    }


}