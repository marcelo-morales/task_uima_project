package com.cs250.joanne.myfragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static com.cs250.joanne.myfragments.MainActivity.myItems;
import static com.cs250.joanne.myfragments.Statistics.total_tasks_array;
import static java.time.LocalDate.of;

public class TaskUpdate extends AppCompatActivity {
    private MainActivity myact;
    private String taskName;
    private String taskCategory;
    private LocalDate localDate;
    private EditText editText;
    private EditText editText2;
    private DatePicker datePicker;

    Button simpleButton1, simpleButton2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);

        Intent intent = getIntent();

        simpleButton1 = (Button) findViewById(R.id.save_btn);//get id of button 1
        simpleButton2 = (Button) findViewById(R.id.cancel_btn);//get id of button 2

        String text1 = intent.getStringExtra("name");
        String text2 = intent.getStringExtra("category");
        int index = intent.getIntExtra("position", 0);

        editText = (EditText) findViewById(R.id.task_name);
        editText2 = (EditText) findViewById(R.id.category_name);

        if (text1 != null && text2 != null) {
            editText.setText(text1);
            editText2.setText(text2);
        }

        //Get local date
        datePicker = (DatePicker) findViewById(R.id.datePicker1);

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = editText.getText().toString();
                String value2 = editText2.getText().toString();

                Intent newIntent = new Intent(TaskUpdate.this, MainActivity.class);

                newIntent.putExtra("taskName", value);
                newIntent.putExtra("taskCategory", value2);

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year =  datePicker.getYear();

                String date = String.valueOf(month).concat("/").concat(String.valueOf(day)).concat("/").concat(String.valueOf(year));

                newIntent.putExtra("taskDue", date);

                Task newTask = new Task(value, date , value2);

                myItems.add(newTask);
                total_tasks_array.add(newTask);

                startActivity(newIntent);
            }
        });

        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
