package com.cs250.joanne.myfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CONTENT_VIEW_ID = 10101010;
    private Fragment item;
    private Fragment list;
    private FragmentTransaction transaction;
    protected ItemAdapter aa;
    //arrayadapter

    protected ArrayList<Task> myItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // create ArrayList of items
        myItems = new ArrayList<Task>();
        // make array adapter to bind arraylist to listview with custom item layout
        aa = new ItemAdapter(this, R.layout.item_layout, myItems);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        item = new ItemFrag();
        list = new ListFrag();

        //list should be the first thing we see
        /*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, list).commit();
        */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if (id == R.id.nav_manage) {

            //if click on done
        } else if (id == R.id.done) {
            Intent myIntent = new Intent(this, CompleteActivity.class);

            this.startActivity(myIntent);

            //if click on stats
        } else if (id == R.id.stats) {

            Intent myIntent = new Intent(this, Statistics.class);

            this.startActivity(myIntent);

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

    @Override
    protected void onStart() {
        super.onStart();

        //numHits = myPrefs.getInt("hitsValue", 0);
        //TextView hits = (TextView) findViewById(R.id.hits_value);
        //hits.setText(numHits.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String taskName = intent.getStringExtra("taskName");
        String taskCategory = intent.getStringExtra("taskCategory");
        String taskDate = intent.getStringExtra("taskDue");
        Task newTask = new Task(taskName, taskDate , taskCategory);
        //this.aa.notifyDataSetChanged();

        myItems.add(newTask);






        this.aa.notifyDataSetChanged();



// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back


        /*
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.list_frag, container, false);

        cntx = getActivity().getApplicationContext();

        myact = (MainActivity) getActivity();
        myList = (ListView) myview.findViewById(R.id.mylist);
        // connect listview to the array adapter in MainActivity
        myList.setAdapter(myact.aa);
        registerForContextMenu(myList);
        // refresh view
        myact.aa.notifyDataSetChanged();
        */



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {

        //SharedPreferences.Editor peditor = myPrefs.edit();
        //peditor.putInt("hitsValue", 10);
        //peditor.apply();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // do stuff here
        Log.d("onDestroy", "exit 3");
        super.onDestroy();
    }

    //  Called when the user clicks the update button
    public void update(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("taskName", taskName);
        //intent.putExtra("taskCategory", taskCategory);
        //intent.putExtra("taskCategory", taskDate);

        startActivity(intent);
    }


    public void cancel(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Called to save UI state changes at the
    // end of the active lifecycle.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d ("Other Fragment", "onSaveInstanceState");
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate, onCreateView, and
        // onCreateView if the parent Activity is killed and restarted.
        super.onSaveInstanceState(savedInstanceState);
    }





}
