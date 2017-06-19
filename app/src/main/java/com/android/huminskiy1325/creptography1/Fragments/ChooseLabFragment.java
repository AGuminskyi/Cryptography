package com.android.huminskiy1325.creptography1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.huminskiy1325.creptography1.Activities.CaesarActivity;
import com.android.huminskiy1325.creptography1.Activities.DesActivity;
import com.android.huminskiy1325.creptography1.Activities.GammaActivity;
import com.android.huminskiy1325.creptography1.Activities.StirlActivity;
import com.android.huminskiy1325.creptography1.Activities.TritemiusActivity;
import com.android.huminskiy1325.creptography1.R;

/**
 * Created by cubru on 08.03.2017.
 */

public class ChooseLabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        Button mLab1 = (Button) view.findViewById(R.id.lab1_button);
        mLab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaesarActivity.class);
                startActivity(intent);
            }
        });

        Button mLab2 = (Button) view.findViewById(R.id.lab2_button);
        mLab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TritemiusActivity.class);
                startActivity(intent);
            }
        });

        Button mLab3 = (Button) view.findViewById(R.id.lab3_button);
        mLab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GammaActivity.class);
                startActivity(intent);
            }
        });

        Button mLab4 = (Button)view.findViewById(R.id.lab4_button);
        mLab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StirlActivity.class);
                startActivity(intent);
            }
        });

        Button mLab5 = (Button)view.findViewById(R.id.lab5_button);
        mLab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DesActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
