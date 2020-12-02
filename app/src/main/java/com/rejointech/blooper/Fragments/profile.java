package com.rejointech.blooper.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rejointech.blooper.MainActivity;
import com.rejointech.blooper.R;


public class profile extends Fragment {
    View tool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      final View root;
        root = inflater.inflate(R.layout.fragment_profile,container,false);

        tool=root.findViewById(R.id.tool);
        ((MainActivity)getActivity()).setDrawer_locked();
        ((MainActivity)getActivity()).setToolInvisible();

        ((MainActivity)getActivity()).toolbuttonunnotclickable();




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity)getActivity()).setDrawer_unlocked();
        ((MainActivity)getActivity()).setToolVisible();
        ((MainActivity)getActivity()).toolbuttonunclickable();


    }
}