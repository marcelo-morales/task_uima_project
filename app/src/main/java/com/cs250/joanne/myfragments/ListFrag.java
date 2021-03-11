package com.cs250.joanne.myfragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class ListFrag extends Fragment {

    public static final int MENU_ITEM_EDITVIEW = Menu.FIRST;
    public static final int MENU_ITEM_COPY = Menu.FIRST + 1;
    public static final int MENU_ITEM_DELETE = Menu.FIRST + 2;

    private ListView myList;
    private MainActivity myact;

    private EditText editText;
    private EditText editText2;
    private DatePicker datePicker;

    Context cntx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        // program a short click on the list item
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String taskName = myact.myItems.get(position).getName();
                String category = myact.myItems.get(position).getCategory();
                String date = myact.myItems.get(position).getDeadline();

                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("taskName", taskName);
                bundle.putString("category", category);
                bundle.putString("date", date);

                if (myact.myItems.get(position).checkCompletetion()) {
                    Fragment CompletedTasksFragment = new CompletedTasksFragment();
                    CompletedTasksFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, CompletedTasksFragment);
                    fragmentTransaction.commit();
                } else {

                    Fragment toBeCompletedFrag = new ToBeCompletedFrag();
                    toBeCompletedFrag.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, toBeCompletedFrag);
                    fragmentTransaction.commit();
                }
            }
        });

        return myview;
    }

    // for a long click on a menu item use ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        editText = (EditText) v.findViewById(R.id.task_name);
        editText2 = (EditText) v.findViewById(R.id.category_name);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker1);
        // create menu in code instead of in xml file (xml approach preferred)
        // Add menu items
        menu.add(0, MENU_ITEM_EDITVIEW, 0, R.string.menu_editview);
        menu.add(0, MENU_ITEM_COPY, 0, R.string.menu_copy);
        menu.add(0, MENU_ITEM_DELETE, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = menuInfo.position; // position in array adapter

        switch (item.getItemId()) {
            case MENU_ITEM_EDITVIEW: {

                Toast.makeText(cntx, "edit request",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), TaskUpdate.class);
                intent.putExtra("name", myact.myItems.get(index).getName());
                intent.putExtra("category", myact.myItems.get(index).getCategory());
                intent.putExtra("position", index);
                this.startActivity(intent);

                return false;
            }
            case MENU_ITEM_COPY: {
                Task oldTask = myact.myItems.get(index);
                String newName = oldTask.getName() + "(Copy)";
                Task copyTask = new Task(newName, oldTask.getDeadline(), oldTask.getCategory());
                Toast.makeText(cntx, "task " + index + 1 + " copied",
                        Toast.LENGTH_SHORT).show();
                myact.myItems.add(oldTask);
                myact.myItems.add(copyTask);
                myact.aa.notifyDataSetChanged();
            }
            case MENU_ITEM_DELETE: {
                myact.myItems.remove(index);
                Toast.makeText(cntx, "task " + index + 1 + " deleted",
                        Toast.LENGTH_SHORT).show();
                // refresh view
                myact.aa.notifyDataSetChanged();
                return true;
            }
        }
        return false;
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
