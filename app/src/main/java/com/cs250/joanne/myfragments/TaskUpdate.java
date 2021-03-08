package com.cs250.joanne.myfragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;

import static java.time.LocalDate.of;

public class TaskUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);
        //get text inputted from textboxes

        //add it to task list
    }

    /** Called when the user taps the update button */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendTask(View view) {
        Intent intent = new Intent(this, ListFrag.class);

        //Get task name
        EditText editText = (EditText) findViewById(R.id.task_name);
        String name = editText.getText().toString();

        //Get task category
        EditText editText2 = (EditText) findViewById(R.id.category_name);
        String category = editText2.getText().toString();

        //Get local date
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        LocalDate localDate = of(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

    }
}