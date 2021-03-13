package com.cs250.joanne.myfragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.cs250.joanne.myfragments.CompleteActivity.completedItems;
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
    private EditText dateText;
    private DatePicker datePicker;
    private Context cntx;

    Button simpleButton1, simpleButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);

        Intent intent = getIntent();

        cntx = getApplicationContext();

        simpleButton1 = (Button) findViewById(R.id.save_btn);//get id of button 1
        simpleButton2 = (Button) findViewById(R.id.cancel_btn);//get id of button 2

        final String text1 = intent.getStringExtra("name");
        final String text2 = intent.getStringExtra("category");
        final String text3 = intent.getStringExtra("date");
        final int index = intent.getIntExtra("position", 0);
        final String type = intent.getStringExtra("type");
        final String listType = intent.getStringExtra("listType");

        editText = (EditText) findViewById(R.id.task_name);
        editText2 = (EditText) findViewById(R.id.category_name);
        dateText = (EditText) findViewById(R.id.due_date);

        if (text1 != null && text2 != null && text3 != null) {
            editText.setText(text1);
            editText2.setText(text2);
            dateText.setText(text3);
        }

        dateText.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                final DatePickerDialog datePicker = new DatePickerDialog(TaskUpdate.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String givenDate =  String.format("%02d/%02d/%04d", month + 1, day, year);
                        dateText.setText(givenDate);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = editText.getText().toString();
                String value2 = editText2.getText().toString();
                String date = dateText.getText().toString();

                if (value2.isEmpty()) {
                    value2 = "misc";
                }

                Intent newIntent = new Intent(TaskUpdate.this, MainActivity.class);

                newIntent.putExtra("taskName", value);
                newIntent.putExtra("taskCategory", value2);
                newIntent.putExtra("taskDue", date);

                if (date.isEmpty()) {
                    Toast.makeText(cntx, "Pick a Due Date to continue",
                            Toast.LENGTH_SHORT).show();
                } else if (value.isEmpty()) {
                    Toast.makeText(cntx, "Make a Task Name to continue",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    if (type != null) {
                        Task newTask = new Task(value, date, value2);

                        if (listType != null) {

                            for (int i = 0; i < completedItems.size(); i++) {
                                if (text1.equals(completedItems.get(i).getName()) &&
                                        text3.equals(completedItems.get(i).getDeadline()) &&
                                        text2.equals(completedItems.get(i).getCategory())) {

                                    Intent newIntent2 = new Intent(TaskUpdate.this, CompleteActivity.class);
                                    newIntent2.putExtra("taskName", value);
                                    newIntent2.putExtra("taskCategory", value2);
                                    newIntent2.putExtra("taskDue", date);

                                    completedItems.get(i).setName(newTask.getName());
                                    completedItems.get(i).setDeadline(newTask.getDeadline());
                                    completedItems.get(i).setCategory(newTask.getCategory());

                                    startActivity(newIntent2);

                                }
                            }
                        } else {

                            for (int i = 0; i < myItems.size(); i++) {
                                if (text1.equals(myItems.get(i).getName()) &&
                                        text3.equals(myItems.get(i).getDeadline()) &&
                                        text2.equals(myItems.get(i).getCategory())) {

                                    myItems.get(i).setName(newTask.getName());
                                    myItems.get(i).setDeadline(newTask.getDeadline());
                                    myItems.get(i).setCategory(newTask.getCategory());

                                }
                            }
                        }
                    } else {
                        Task newTask = new Task(value, date, value2);

                        myItems.add(newTask);
                        total_tasks_array.add(newTask);
                    }
                    startActivity(newIntent);

                }
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
