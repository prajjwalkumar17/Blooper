package com.rejointech.blooper.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rejointech.blooper.R;

public class dashboardFrag extends Fragment {
TextView travelvideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root=inflater.inflate(R.layout.fragment_dashboard,container,false);
        travelvideo=root.findViewById(R.id.travelvideo);
        travelvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_dashboardFrag_to_videos);
            }
        });
        return root;
    }
}