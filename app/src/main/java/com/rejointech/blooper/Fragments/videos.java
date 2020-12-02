package com.rejointech.blooper.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rejointech.blooper.R;


public class videos extends Fragment {
    TextView vidText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root=inflater.inflate(R.layout.fragment_videos,container,false);
        vidText=root.findViewById(R.id.vidText);
        vidText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_videos_to_settingsFrag);
            }
        });


















        return root;
    }



}