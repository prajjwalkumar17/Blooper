package com.rejointech.blooper.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rejointech.blooper.R;


public class overView2 extends Fragment {

    private FloatingActionButton signInFloat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =inflater.inflate(R.layout.fragment_over_view2, container, false);
        signInFloat=root.findViewById(R.id.signInFloat);
        signInFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),SigninMethodsHolder.class));
                getActivity().onBackPressed();

            }
        });
        return  root;
    }
}