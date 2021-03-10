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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.LocalDate.of;

public class TaskUpdate extends AppCompatActivity {
    private MainActivity myact;
    private ListView myList;
    private SharedPreferences myPrefs;

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

        simpleButton1 = (Button) findViewById(R.id.save_btn);//get id of button 1
        simpleButton2 = (Button) findViewById(R.id.cancel_btn);//get id of button 2


        editText = (EditText) findViewById(R.id.task_name);




        //Get task category
        editText2 = (EditText) findViewById(R.id.category_name);

        //Get local date
        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        //localDate = LocalDate.of(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        System.out.println("this is new date " + localDate);


        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();
                String value2 = editText2.getText().toString();
                System.out.println("this is category name " + value);
                System.out.println("this is category name " + value2);
                Intent intent = new Intent(TaskUpdate.this, MainActivity.class);

                intent.putExtra("taskName", value);
                intent.putExtra("taskCategory", value2);
                int day = datePicker.getDayOfMonth();
                System.out.println("this is day " + day);
                int month = datePicker.getMonth() + 1;
                System.out.println("this is month " + month);
                int year =  datePicker.getYear();
                System.out.println("this is year " + year);
               // Calendar calendar = Calendar.getInstance();
                //calendar.set(year, month, day);
                //Date time = calendar.getTime();
                String date = String.valueOf(month).concat("/").concat(String.valueOf(day)).concat("/").concat(String.valueOf(year));
                System.out.println("this is date " + date);
                intent.putExtra("taskDue", date);
                startActivity(intent);



            }
        });
        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // System.out.println("this is task name " + taskName);
                //System.out.println("this is category name " + taskCategory);
                finish();
            }
        });
    }


}