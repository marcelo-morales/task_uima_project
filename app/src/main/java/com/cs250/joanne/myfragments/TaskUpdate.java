package com.cs250.joanne.myfragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;

import static java.time.LocalDate.of;

public class TaskUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);

        Intent intent = new Intent(this, ListFrag.class);

        //Get task name
        EditText editText = (EditText) findViewById(R.id.task_name);
        String name = editText.getText().toString();

        //Get task category
        EditText editText2 = (EditText) findViewById(R.id.category_name);
        String category = editText2.getText().toString();

        /*
        //Get local date
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        LocalDate localDate = LocalDate.of(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        */

        Button saveButton = (Button) findViewById(R.id.save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}