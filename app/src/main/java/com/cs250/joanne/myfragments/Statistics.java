package com.cs250.joanne.myfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static com.cs250.joanne.myfragments.MainActivity.myItems;

public class Statistics extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Current count
    private int done_by_deadline = 0;
    private int done_after_due = 0;
    private int past_due = 0;
    private int to_be_due = 0;
    private int total_tasks = 0;


    // Key for current count
    private final String DONE_BY_DEADLINE_KEY = "count";
    private final String DONE_AFTER_DUE_KEY = "count";
    private final String PAST_DUE_KEY = "count";
    private final String TO_BE_DUE_KEY = "count";
    private final String TOTAL_TASKS_KEY = "count";



    private Fragment item;
    private Fragment list;
    private FragmentTransaction transaction;
    protected ItemAdapter aa;
    private SharedPreferences sPreferences;
    //reference a shared preferences file?

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";

    //arrayadapter


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //initialize values
        done_by_deadline = done_by_deadline(myItems);
        done_after_due = done_after_deadline(myItems);
        past_due = past_due(myItems);
        to_be_due = to_be_done(myItems);
        total_tasks = myItems.size();

        TextView first_text = new TextView(this);
        first_text=(TextView)findViewById(R.id.done_by_deadline);
        first_text.setText(String.valueOf(done_by_deadline) + " done by deadline");

        TextView second_text = new TextView(this);
        second_text=(TextView)findViewById(R.id.done_after_due);
        second_text.setText(String.valueOf(done_after_due) + " done after due");

        TextView third_text = new TextView(this);
        third_text=(TextView)findViewById(R.id.past_due);
        third_text.setText(String.valueOf(past_due) + " past due");

        TextView fourth_text = new TextView(this);
        fourth_text=(TextView)findViewById(R.id.to_be_done);
        fourth_text.setText(String.valueOf(to_be_due) + " to be done");

        TextView fifth_text = new TextView(this);
        fifth_text=(TextView)findViewById(R.id.total_tasks);
        fifth_text.setText(String.valueOf(total_tasks) + " total tasks");


        done_by_deadline = sPreferences.getInt(DONE_BY_DEADLINE_KEY, 0);
        done_after_due = sPreferences.getInt(DONE_AFTER_DUE_KEY, 0);
        past_due = sPreferences.getInt(PAST_DUE_KEY, 0);
        to_be_due = sPreferences.getInt(TO_BE_DUE_KEY, 0);
        total_tasks = sPreferences.getInt(TOTAL_TASKS_KEY, 0);



    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int done_by_deadline(ArrayList<Task> tasks) {
        int completed = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            Task current_task = tasks.get(i);
            int [] my_deadline = get_task_date(current_task);
            int [] today = get_today();

            //if today is before deadline and the task is complete
            if (completed_by_date(today, my_deadline) && current_task.checkCompletetion()) {
                ++completed;
            }
        }
        return completed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int done_after_deadline(ArrayList<Task> tasks) {
        int completed = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            Task current_task = tasks.get(i);
            int [] my_deadline = get_task_date(current_task);
            int [] today = get_today();

            //if the deadline has past (is after today) but the the task is complete
            if (completed_by_date(my_deadline, today) && current_task.checkCompletetion()) {
                ++completed;
            }
        }
        return completed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int past_due(ArrayList<Task> tasks) {
        int completed = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            Task current_task = tasks.get(i);
            int [] my_deadline = get_task_date(current_task);
            int [] today = get_today();
            //if the deadline has past (is after today) but the the task is complete
            if (completed_by_date(my_deadline, today) && !current_task.checkCompletetion()) {
                ++completed;
            }
        }
        return completed;
    }

    public int to_be_done(ArrayList<Task> tasks) {
        int completed = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            Task current_task = tasks.get(i);

            //if the deadline has past (is after today) but the the task is complete
            if (!current_task.checkCompletetion()) {
                ++completed;
            }
        }
        return completed;
    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    public int [] get_today() {
        //Getting the current date value
        LocalDate currentdate = LocalDate.now();
        System.out.println("Current date: "+currentdate);
        //Getting the current day
        int currentDay = currentdate.getDayOfMonth();
        System.out.println("Current day: "+currentDay);
        //Getting the current month
        Month currentMonth = currentdate.getMonth();
        int current = currentMonth.getValue();

        System.out.println("Current month: "+currentMonth);
        //getting the current year
        int currentYear = currentdate.getYear();
        //System.out.println("Current month: "+currentYear);
        int [] today = new int [3];
        today[0] = current;
        today[1] = currentDay;
        today[2] = currentYear;
        return today;
    }

    public int [] get_task_date(Task task) {
        //Getting the current date value

        String date = task.getDeadline();
        String[] tokens = date.split("/");

        int counter = 0;
        int [] today = new int [3];

        for (String t : tokens) {
            if (counter == 0) {
                int month=Integer.parseInt(t);
                today[0] = month;
            } else if (counter == 2) {
                int day=Integer.parseInt(t);
                today[1] = day;
            } else {
                int year=Integer.parseInt(t);
                today[2] = year;
            }
        }

        return today;
    }

    //helps with first two stats
    //find out if date one comes before date two
    public boolean completed_by_date (int [] date_one, int [] date_two) {
        int day_one = date_one[0];
        int month_one = date_one[0];
        int year_one = date_one[0];

        int day_two = date_two[0];
        int month_two = date_two[0];
        int year_two = date_two[0];

        if (year_one > year_two) {
            return false;
        }
        if (year_one == year_two && month_one > month_two) {
            return false;
        }
        if (year_one == year_two && month_one == month_two && day_one > day_two) {
            return false;
        }

        return true;

    }





    /*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        Bundle bundle = getIntent().getExtras();
        String task = bundle.getString("task_name");

    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //return true;
        //setContentView(R.layout.activity_task_update);
        // }
        Intent myIntent = new Intent(this, TaskUpdate.class);

        this.startActivity(myIntent);


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.todo) {
            Intent myIntent = new Intent(this, MainActivity.class);

            this.startActivity(myIntent);

            //if click on done
        } else if (id == R.id.done) {
            Intent myIntent = new Intent(this, CompleteActivity.class);

            this.startActivity(myIntent);

            //if click on stats
        } else if (id == R.id.stats) {



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onPause() {
        super.onPause();

        super.onPause();

        SharedPreferences.Editor preferencesEditor = sPreferences.edit();
        preferencesEditor.putInt(DONE_BY_DEADLINE_KEY, done_by_deadline);
        preferencesEditor.putInt(DONE_AFTER_DUE_KEY, done_after_due);
        preferencesEditor.putInt(PAST_DUE_KEY, past_due);
        preferencesEditor.putInt(TO_BE_DUE_KEY, to_be_due);
        preferencesEditor.putInt(TOTAL_TASKS_KEY, total_tasks);
        preferencesEditor.apply();
    }


}
