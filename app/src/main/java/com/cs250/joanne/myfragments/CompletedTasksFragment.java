package com.cs250.joanne.myfragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

import static com.cs250.joanne.myfragments.CompleteActivity.completedItems;


public class CompletedTasksFragment extends Fragment {

    private CompleteActivity myact;
    private ListView myList;
    Context cntx;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_completed_tasks, container, false);

        cntx = getActivity().getApplicationContext();

        myact = (CompleteActivity) getActivity();

        Bundle bundle = this.getArguments();
        String taskName = bundle.getString("taskName");
        String category = bundle.getString("category");
        String date = bundle.getString("finishedDate");
        final int position = bundle.getInt("position");

        TextView nameText = (TextView) myview.findViewById(R.id.completed_task);
        TextView dateText = (TextView) myview.findViewById(R.id.completed_date);
        TextView dueDateText = (TextView) myview.findViewById(R.id.due_date);
        TextView categoryText = (TextView) myview.findViewById(R.id.completed_category);

        nameText.setText("Task: " + taskName);
        dueDateText.setText("Due: " + completedItems.get(position).getDeadline());
        dateText.setText("Done: " + completedItems.get(position).getCompletedDate());
        categoryText.setText("Category: " + category);

        ImageView imageView = (ImageView) myview.findViewById(R.id.close_mark_2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment listFrag = new CompletedListFrag();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_completed_container, listFrag);
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