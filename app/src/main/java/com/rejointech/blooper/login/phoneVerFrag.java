package com.rejointech.blooper.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rejointech.blooper.R;

public class phoneVerFrag extends Fragment {

    private View root;
    private static SharedPreferences sharedPreferences;
    private static String no, popo;
    private TextView phn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_phone_ver, container, false);


//TODO: all muticals::::::::::::::::::::::::::::::::::::::::::::::::::::::::

        muticals();

//TODO all variables:::::::::::::::::::::::::::::::::::::::::::::::::::::

//        sharedPreferences=getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
//        no= sharedPreferences.getString("phonePref","No Phone No Found!");

//TODO Buttons :::::::::::::::::::::::::::::::::::::::::::::::::::::::

        phn.setText(updatetextView());


        return root;
    }


    //TODO all functions::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private void muticals() {
        phn = root.findViewById(R.id.phn);
    }

    private String updatetextView() {
        sharedPreferences = getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        no = sharedPreferences.getString("phonePref", "No Phone No Found!");
        return no;
    }

}