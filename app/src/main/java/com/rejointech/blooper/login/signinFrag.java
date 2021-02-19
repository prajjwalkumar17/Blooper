package com.rejointech.blooper.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rejointech.blooper.MainActivity;
import com.rejointech.blooper.R;
import com.rejointech.blooper.model.Login_Cred;


public class signinFrag extends Fragment {

    View root;
    private int guh = 2;
    TextInputLayout account, passwordtt;
    private Button signUpbot, usePhonebot, loginBot;

    private static String NO_USERNAME_NOW = "noName";

    private static final String NO_PHOTO_URI = "nopic";

    private static String NO_PHONE = "willbeadd";

    private SharedPreferences pref, pref1;
    private SharedPreferences.Editor editor, editor1;

    private FirebaseAuth auth;

    private DatabaseReference database;

    Login_Cred login_cred, login_cred1;

    private GoogleSignInClient mGoogleSignInClient;

    private FloatingActionButton floatingActionButton2;

    static String name, tokenUID, photoUri, accounttrt, id;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {

            Navigation.findNavController(root).navigate(R.id.action_signinFrag_to_phoneVerFrag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_signin, container, false);


//TODO: all muticals::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        muticals();
        Toast.makeText(getContext(), "helooo", Toast.LENGTH_SHORT).show();

//TODO all variables:::::::::::::::::::::::::::::::::::::::::::::::::::::


//TODO Buttons :::::::::::::::::::::::::::::::::::::::::::::::::::::::

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleChooser();

                googleSignUp(guh);
            }
        });
        signUpbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(root).navigate(R.id.action_signinFrag_to_signupFrag);
            }
        });
        usePhonebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(root).navigate(R.id.action_signinFrag_to_phoneLoginFrag);
            }
        });
        loginBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signin(account.getEditText().getText().toString(), passwordtt.getEditText().getText().toString());
            }
        });
        return root;
    }



//TODO all functions::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    private void muticals() {
        signUpbot = root.findViewById(R.id.signUpbot);
        usePhonebot = root.findViewById(R.id.usePhonebot);

        account = root.findViewById(R.id.account);


        passwordtt = root.findViewById(R.id.passwordtt);


        loginBot = root.findViewById(R.id.loginBot);

        floatingActionButton2 = root.findViewById(R.id.floatingActionButton2);
        auth = FirebaseAuth.getInstance();


        database = FirebaseDatabase.getInstance().getReference("Profile");
            login_cred = new Login_Cred();
        }

    private void signin(String username, final String password) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            logindatatofirebase();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logindatatofirebase() {
        database
                .child("User_cred")
                .child(auth.getUid())
                .child("Permanent_cred")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(getContext(), "pottyyyykrlon frandzzzzzz", Toast.LENGTH_SHORT).show();
                            login_cred = snapshot.getValue(Login_Cred.class);
                            addDataTosharedPref();
                            Navigation.findNavController(root).navigate(R.id.action_signinFrag_to_phoneVerFrag);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addDataTosharedPref() {
        pref = getActivity().getSharedPreferences("loginSaves", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("accountLogin", account.getEditText().getText().toString());
        editor.putString("passwordLogin", passwordtt.getEditText().getText().toString());
        editor.putString("nameLogin", login_cred.getName());
        editor.putString("phoneLogin", login_cred.getPhone());

        editor.putString("picLogin", login_cred.getPicUri());
        editor.putString("usernameLogin", login_cred.getUsername());
        editor.putString("uidLogin", login_cred.getUid());


        editor.apply();
    }

    private void googleChooser() {
        /* Configure sign-in to request the user's ID, email address, and basic
            profile. ID and basic profile are included in DEFAULT_SIGN_IN.*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    private void googleSignUp(int code) {

        Intent signInIntent = this.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == guh) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                name = account.getDisplayName();
                tokenUID = account.getIdToken();
                id = account.getId();
                accounttrt = account.getEmail();
                firebaseAuthWithGoogle(tokenUID);
                // Signed in successfully, show authenticated UI.
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = auth.getCurrentUser();
                    Navigation.findNavController(root).navigate(R.id.action_signinFrag_to_phoneVerFrag);
                    addDataToPrefGOOGLE(name, tokenUID, accounttrt, NO_PHONE);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getContext(), "YOU ARE FUCKED!!!!!", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDataToPrefGOOGLE(String name, String tokenUID, String accountt, String Phone) {
        pref1 = getActivity().getSharedPreferences("GOOGLEloginSaves", Context.MODE_PRIVATE);
        editor1 = pref1.edit();
        editor1.putString("GOOGLEnamePref", name);
        editor1.putString("GOOGLEphonePref", Phone);
        editor1.putString("GOOGLEuidPref", tokenUID);
        editor1.putString("GOOGLEemailPref", accountt);
        editor1.apply();
    }


}