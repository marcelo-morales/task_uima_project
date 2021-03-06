package com.cs250.joanne.myfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import static com.cs250.joanne.myfragments.CompleteActivity.completedItems;
import static com.cs250.joanne.myfragments.MainActivity.myItems;


public class ToBeCompletedFrag extends Fragment {

    private MainActivity myact;
    private CompleteActivity completeActivity;
    private ListView myList;
    Context cntx;

    private ArrayList<Task> completed_tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_to_be_completed, container, false);

        cntx = getActivity().getApplicationContext();

        myact = (MainActivity) getActivity();

        Bundle bundle = this.getArguments();
        String taskName = bundle.getString("taskName");
        String category = bundle.getString("category");
        String date = bundle.getString("date");
        final int position = bundle.getInt("position");

        TextView nameText = (TextView) myview.findViewById(R.id.to_do_task);
        TextView dateText = (TextView) myview.findViewById(R.id.to_do_date);
        TextView categoryText = (TextView) myview.findViewById(R.id.to_do_category);

        nameText.setText("Task: " + taskName);
        dateText.setText("Due: " + date);
        categoryText.setText("Category: " + category);


        Button button = (Button) myview.findViewById(R.id.complete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task completedTask = myItems.get(position);
                completedTask.markComplete();

                SharedPreferences sPreferences = myact.getSharedPreferences("com.example.android.hellosharedprefs", Context.MODE_PRIVATE);

                SharedPreferences.Editor peditor = sPreferences.edit();

                Calendar cal = Calendar.getInstance();
                String today = String.format("%02d/%02d/%04d", cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.YEAR));
                Task todayTask = new Task("",today,"");
                int[] currentDate = get_today();
                int month = currentDate[0];
                int day = currentDate[1];
                int year = currentDate[2];
                String completedDate = String.format("%02d/%02d/%04d", month, day, year);
                completedTask.setCompletedDate(completedDate);

                myItems.remove(completedTask);
                if (completedTask.compareTo(todayTask) >= 0) {
                    peditor.putInt("done_by_deadline", sPreferences.getInt("done_by_deadline",0) + 1);
                } else {
                    peditor.putInt("done_after_due", sPreferences.getInt("done_after_due",0) + 1);
                }
                peditor.apply();
                completedItems.add(completedTask);
            }
        });

        ImageView imageView = (ImageView) myview.findViewById(R.id.close_mark);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStackImmediate();
                Fragment listFrag = new ListFrag();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, listFrag);
                fragmentTransaction.commit();
            }
        });

        return myview;
    }

    public int [] get_today() {
        //Getting the current date value
        Calendar calendar = Calendar.getInstance();

        //Getting the current day
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Getting the current month
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        //getting the current year
        int currentYear = calendar.get(Calendar.YEAR);

        int [] today = new int [3];
        today[0] = currentMonth;
        today[1] = currentDay;
        today[2] = currentYear;

        return today;
    }

    // Called at the start of the visible lifetime.
    @Override
    public void onStart(){
        super.onStart();
        Log.d ("Other Fragment2", "onStart");
        // Apply any required UI change now that the Fragment is visible.
    }

    // Called at the start of the active lifetime.
    @Override
    public void onResume(){
        super.onResume();
        Log.d ("Other Fragment", "onResume");
        // Resume any paused UI updates, threads, or processes required
        // by the Fragment but suspended when it became inactive.
    }

    // Called at the end of the active lifetime.
    @Override
    public void onPause(){
        Log.d ("Other Fragment", "onPause");
        // Suspend UI updates, threads, or CPU intensive processes
        // that don't need to be updated when the Activity isn't
        // the active foreground activity.
        // Persist all edits or state changes
        // as after this call the process is likely to be killed.
        super.onPause();
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

    // Called at the end of the visible lifetime.
    @Override
    public void onStop(){
        Log.d ("Other Fragment", "onStop");
        // Suspend remaining UI updates, threads, or processing
        // that aren't required when the Fragment isn't visible.
        super.onStop();
    }

    // Called when the Fragment's View has been detached.
    @Override
    public void onDestroyView() {
        Log.d ("Other Fragment", "onDestroyView");
        // Clean up resources related to the View.
        super.onDestroyView();
    }

    // Called at the end of the full lifetime.
    @Override
    public void onDestroy(){
        Log.d ("Other Fragment", "onDestroy");
        // Clean up any resources including ending threads,
        // closing database connections etc.
        super.onDestroy();
    }

    // Called when the Fragment has been detached from its parent Activity.
    @Override
    public void onDetach() {
        Log.d ("Other Fragment", "onDetach");
        super.onDetach();
    }

}
