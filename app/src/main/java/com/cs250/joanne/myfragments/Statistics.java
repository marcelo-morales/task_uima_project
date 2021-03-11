package com.cs250.joanne.myfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cs250.joanne.myfragments.ItemAdapter;
import com.cs250.joanne.myfragments.ItemFrag;
import com.cs250.joanne.myfragments.ListFrag;
import com.cs250.joanne.myfragments.R;
import com.cs250.joanne.myfragments.Task;
import com.cs250.joanne.myfragments.TaskUpdate;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.cs250.joanne.myfragments.MainActivity.myItems;
import java.time.LocalDate;
import java.time.Month;

public class Statistics extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment item;
    private Fragment list;
    private FragmentTransaction transaction;
    protected ItemAdapter aa;

    //arrayadapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int done_by_deadline(ArrayList<Task> tasks) {
        int completed = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            Task current_task = tasks.get(i);
            int [] my_deadline = get_task_date(current_task);
            int [] today = get_today();
            if (completed_by_date(my_deadline, today) && current_task.checkCompletetion()) {
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
    public boolean completed_by_date (int [] date_one, int [] date_two) {

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


            /*
            transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, list);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();
            */

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






}
