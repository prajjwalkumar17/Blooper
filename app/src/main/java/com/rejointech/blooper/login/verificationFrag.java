package com.rejointech.blooper.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.rejointech.blooper.R;


public class verificationFrag extends Fragment {

    private View root;
    private Button phoneVerBot, EmailVerBot, SignUpAgain1212;
    private TextInputLayout textInputLayout, phone;
    private TextView phoneNo, rough11;
    private static SharedPreferences sharedPreferences;
    private static String no, fullnom;
    private CountryCodePicker countryCodePicker;
    //    private LinearLayout phone_layout;
    private static SharedPreferences.Editor editor;
    private static Boolean havePhoneNoAsspciated;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_verification, container, false);


//TODO: all muticals::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        muticals();
//TODO all variables:::::::::::::::::::::::::::::::::::::::::::::::::::::
//TODO Buttons :::::::::::::::::::::::::::::::::::::::::::::::::::::::

        checkPhonePref();
        getphoneNofromSharedpref();
        Log.d("pklllll", checkPhonePref().toString());
        phoneVerBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkPhonePref()) {
                    getphone();
                }
                Navigation.findNavController(root).navigate(R.id.action_verificationFrag_to_phoneVerFrag);
            }
        });
        EmailVerBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_verificationFrag_to_accountVerFrag);
            }
        });
        SignUpAgain1212.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_verificationFrag_to_signupFrag);
            }
        });
        return root;
    }


    //TODO all functions::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private void muticals() {
        phoneVerBot = root.findViewById(R.id.phoneVerBot);
        EmailVerBot = root.findViewById(R.id.emailverifyBot);
        SignUpAgain1212 = root.findViewById(R.id.SignUpAgain1212);
        textInputLayout = root.findViewById(R.id.textInputLayout);
        phoneNo = root.findViewById(R.id.phoneNo);
        countryCodePicker = root.findViewById(R.id.countryCodePicker);
        phone = root.findViewById(R.id.phone);
//        phone_layout = root.findViewById(R.id.phone_layout);
        rough11 = root.findViewById(R.id.rough11);
    }

    public Boolean checkPhonePref() {
        sharedPreferences = getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);  
        if (sharedPreferences.getString("phonePref", "null6767").contains("null")) {
            havePhoneNoAsspciated = false;
            return havePhoneNoAsspciated;
//        } else if (sharedPreferences.getString("phonePref", "null").contains("null")) {
        } else {
            return true;
        }
    }

    private void getphoneNofromSharedpref() {
        sharedPreferences = getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        no = sharedPreferences.getString("phonePref", "No Phone No Found!");
        Log.d("pklllll", no);

//        if (no.contains("No Phone No Found!")) {
        if (!checkPhonePref()) {
            countryCodePicker.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
            phoneNo.setVisibility(View.INVISIBLE);
            phoneVerBot.setText("Add phone to verify");
            rough11.setText("kucho ni mila");
        } else {
            phoneNo.setVisibility(View.VISIBLE);
            countryCodePicker.setVisibility(View.INVISIBLE);
            phone.setVisibility(View.INVISIBLE);
            phoneNo.setText(no);
            rough11.setText(no);
        }
    }

    public String getphone() {
        fullnom = countryCodePicker.getDefaultCountryCodeWithPlus() + phone.getEditText().getText().toString();
        Toast.makeText(getContext(), fullnom, Toast.LENGTH_SHORT).show();
        Log.d("pklllll", fullnom);
        updatedPhone();
        return fullnom;
    }

    public void updatedPhone() {
        sharedPreferences = getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("phonePref", fullnom);
        editor.apply();
    }
}