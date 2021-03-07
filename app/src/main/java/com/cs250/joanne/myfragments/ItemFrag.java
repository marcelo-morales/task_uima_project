package com.cs250.joanne.myfragments;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;

import static android.widget.Toast.LENGTH_SHORT;

public class ItemFrag extends Fragment {

    private TextView tv;
    private Button btn;
    private MainActivity myact;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_frag, container, false);

        myact = (MainActivity) getActivity();


        //change this later
        final LocalDate test = LocalDate.of(Integer.parseInt("2020"), Integer.parseInt("7"), Integer.parseInt("4"));



        tv = (EditText) view.findViewById(R.id.item_text);
        btn = (Button) view.findViewById(R.id.add_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task myitem = new Task(tv.getText().toString(), test, "Project");
                myact.myItems.add(myitem);
                Toast.makeText(getActivity().getApplicationContext(), "added item", LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
