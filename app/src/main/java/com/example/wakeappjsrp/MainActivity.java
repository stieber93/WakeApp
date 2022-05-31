package com.example.wakeappjsrp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.app.TimePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;



public class MainActivity extends AppCompatActivity {
    TimePickerDialog picker;
    EditText eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Load Values
        EditText inputUhrzeit = (EditText) findViewById(R.id.inputUhrzeit);
        EditText inputFahrtzeit = (EditText) findViewById(R.id.inputFahrtzeit);
        EditText inputZZF = (EditText) findViewById(R.id.inputZeitZumFertigmachen);
        EditText resultField = (EditText) findViewById(R.id.plainTextEndergebnis);
        try {
            FileInputStream fileInputStream = openFileInput("zielzeit.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;

            while((lines = bufferReader.readLine()) != null) {
                stringBuffer.append(lines);
            }

            inputUhrzeit.setText(stringBuffer.toString());

            fileInputStream = openFileInput("fahrtzeit.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);

            bufferReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();

            while((lines = bufferReader.readLine()) != null) {
                stringBuffer.append(lines);
            }

            inputFahrtzeit.setText(stringBuffer.toString());

            fileInputStream = openFileInput("vorzeit.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);

            bufferReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();

            while((lines = bufferReader.readLine()) != null) {
                stringBuffer.append(lines);
            }

            inputZZF.setText(stringBuffer.toString());

            fileInputStream = openFileInput("weckzeit.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);

            bufferReader = new BufferedReader(inputStreamReader);
            stringBuffer = new StringBuffer();

            while((lines = bufferReader.readLine()) != null) {
                stringBuffer.append(lines);
            }

            resultField.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Prep time picker
        eText=(EditText) findViewById(R.id.inputUhrzeit);  //Hier als ID die Textbox ausw채hlen
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                String formattedHour = String.format("%02d", sHour);
                                String formattedMinute = String.format("%02d", sMinute);
                                eText.setText(formattedHour + ":" + formattedMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
    }

    public void changeResultTime(View view) {
        EditText inputFahrtzeit = (EditText) findViewById(R.id.inputFahrtzeit);
        EditText inputZZF = (EditText) findViewById(R.id.inputZeitZumFertigmachen);
        EditText resultField = (EditText) findViewById(R.id.plainTextEndergebnis);
        String inputUhrzeitValue = eText.getText().toString();
        String inputFahrtzeitValue = inputFahrtzeit.getText().toString();
        String inputZZFValue = inputZZF.getText().toString();
        int inputHours = Integer.valueOf(inputUhrzeitValue.substring(0,2));
        int inputMinutes = Integer.valueOf(inputUhrzeitValue.substring(3,5));
        int tempTime = (inputHours *60)+inputMinutes - Integer.valueOf(inputFahrtzeitValue) - Integer.valueOf(inputZZFValue);
        String resultTime ="";
        if(tempTime > 0) {

            resultTime = +tempTime / 60 + ":" + tempTime % 60;
            String[] split = resultTime.split(":");
            if(split[0].length()==1){
                split[0]="0"+split[0];
            }
            if(split[1].length()==1){
                split[1]="0"+split[1];
            }
            resultTime = split[0] + ":"+split[1]+ " Uhr";
        }
        if(tempTime < 0){
            tempTime += (24 *60);
            resultTime = +tempTime/60 +":" +tempTime%60;
            String[] split = resultTime.split(":");
            if(split[0].length()==1){
                split[0]="0"+split[0];
            }
            if(split[1].length()==1){
                split[1]="0"+split[1];
            }
            resultTime = split[0] + ":"+split[1]+ " Uhr am Vortag";
;        }
        if (tempTime<  0){
            tempTime += (24*60);
            resultTime = +tempTime/60 +":" +tempTime%60;
            String[] split = resultTime.split(":");
            if(split[0].length()==1){
                split[0]="0"+split[0];
            }
            if(split[1].length()==1){
                split[1]="0"+split[1];
            }
            resultTime = split[0] + ":"+split[1]+ " Uhr zwei Tage vorher";
        }
        resultField.setText(resultTime);

        try {
            FileOutputStream fileOutputStream = openFileOutput("zielzeit.txt",MODE_PRIVATE);
            fileOutputStream.write(inputUhrzeitValue.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            fileOutputStream = openFileOutput("fahrtzeit.txt",MODE_PRIVATE);
            fileOutputStream.write(inputFahrtzeitValue.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            fileOutputStream = openFileOutput("vorzeit.txt",MODE_PRIVATE);
            fileOutputStream.write(inputZZFValue.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            fileOutputStream = openFileOutput("weckzeit.txt",MODE_PRIVATE);
            fileOutputStream.write(resultTime.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    }

