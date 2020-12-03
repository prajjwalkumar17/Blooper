package com.rejointech.blooper.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.rejointech.blooper.R;
import com.rejointech.blooper.model.Login_Cred;

import java.util.Arrays;
import java.util.Objects;


public class signupFrag extends Fragment {
    private static final String TAG = "aaaaaa";
    private View root;
    private int guh = 1;
    private static String NO_USERNAME_NOW = "noName";
    private static final String NO_PHOTO_URI = "nopic";
    private static String NO_PHONE = "willbeadd";
    TextInputLayout name, phone, account, password;
    private Button loginBot, alreadyAcc;
    private CountryCodePicker countryCodePicker;
    private static String fullnom;
    private EditText phoneedit;
    private FirebaseAuth auth;
    LoginButton floatingActionButton;
    private DatabaseReference database;
    private Login_Cred login_cred, login_credGoogle,login_credFacebook;
    private SharedPreferences pref, pref1;
    private SharedPreferences.Editor editor, editor1;
    private FloatingActionButton floatingActionButton2, floatingActionButton3,fb;
    private GoogleSignInClient mGoogleSignInClient;
    static String namet, tokenUID, photoUri, accountt, id;
    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private ScriptGroup.Binding mBinding;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Navigation.findNavController(root).navigate(R.id.action_signupFrag_to_phoneVerFrag);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_signup, container, false);

//TODO: all muticals::::::::::::::::::::::::::::::::::::::::::::::::::::::::

        muticals();

//TODO all variables:::::::::::::::::::::::::::::::::::::::::::::::::::::

        callbackManager = CallbackManager.Factory.create();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Profile");
        login_cred = new Login_Cred();
        login_credGoogle = new Login_Cred();

//TODO Buttons :::::::::::::::::::::::::::::::::::::::::::::::::::::::

        alreadyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {{
                Navigation.findNavController(root).navigate(R.id.action_signupFrag_to_signinFrag);} }
        });
        loginBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    if (name.getEditText().getText().toString().isEmpty()) {
                        name.setError("Username is required");
                        name.requestFocus();
                    } else if (phone.getEditText().getText().toString().isEmpty()) {
                        phone.setError("Required");
                        phone.requestFocus();
                    } else if (phone.getEditText().getText().toString().length() != 10) {
                        phone.setError("Enter a valid Phone no.");
                        phone.requestFocus();
                    } else if (account.getEditText().getText().toString().isEmpty()) {
                        account.setError("Account Required");
                        account.requestFocus();
                    } else if (!account.getEditText().getText().toString().contains("@") || !account.getEditText().getText().toString().contains(".com")) {
                        account.setError("Emter a valid Email.id");
                        account.requestFocus();
                    } else if ((password.getEditText().getText().toString().length() < 6)) {
                        password.setError("Atleast 6 digit long");
                        password.requestFocus();
                    } else if (
                            !(password.getEditText().getText().toString().contains("@")
                                    || password.getEditText().getText().toString().contains("!")
                                    || password.getEditText().getText().toString().contains("#")
                                    || password.getEditText().getText().toString().contains("$")
                                    || password.getEditText().getText().toString().contains("%")
                                    || password.getEditText().getText().toString().contains("^")
                                    || password.getEditText().getText().toString().contains("&")
                                    || password.getEditText().getText().toString().contains("*")
                                    || password.getEditText().getText().toString().contains("_")
                                    || password.getEditText().getText().toString().contains("+")
                                    || password.getEditText().getText().toString().contains("=")
                                    || password.getEditText().getText().toString().contains("/")
                                    || password.getEditText().getText().toString().contains("?")
                                    || password.getEditText().getText().toString().contains(">")
                                    || password.getEditText().getText().toString().contains("<")
                                    || password.getEditText().getText().toString().contains(";")
                                    || password.getEditText().getText().toString().contains(":"))
                    ) {
                        password.setError("Use a special character");
                        password.requestFocus();
                    } else if (
                            !(password.getEditText().getText().toString().contains("1")
                                    || password.getEditText().getText().toString().contains("2")
                                    || password.getEditText().getText().toString().contains("3")
                                    || password.getEditText().getText().toString().contains("4")
                                    || password.getEditText().getText().toString().contains("5")
                                    || password.getEditText().getText().toString().contains("6")
                                    || password.getEditText().getText().toString().contains("7")
                                    || password.getEditText().getText().toString().contains("8")
                                    || password.getEditText().getText().toString().contains("9")
                                    || password.getEditText().getText().toString().contains("0"))
                    ) {
                        password.setError("Use a digit in your password!");
                        password.requestFocus();
                    } else {
                        getphoneno();
                        login(account.getEditText().getText().toString(), password.getEditText().getText().toString());
                    }
                }
            }});

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { {
                    googleChooser();
                    googleSignUp(guh);
                } }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==fb){
                    floatingActionButton.performClick();
                }
            }
        });

        floatingActionButton.setFragment(signupFrag.this);
        floatingActionButton.setReadPermissions("email", "public_profile");
        floatingActionButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });






        return root;
    }



//TODO all functions::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    private void muticals() {
        name = root.findViewById(R.id.name);
        account = root.findViewById(R.id.account);
        phone = root.findViewById(R.id.phone);
        password = root.findViewById(R.id.passwordtt);
        phoneedit = root.findViewById(R.id.phoneedit);
        loginBot = root.findViewById(R.id.loginBot);
        alreadyAcc = root.findViewById(R.id.alreadyAcc);
        floatingActionButton2 = root.findViewById(R.id.floatingActionButton2);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton3 = root.findViewById(R.id.floatingActionButton3);
        countryCodePicker = root.findViewById(R.id.countryCodePicker);
        fb=root.findViewById(R.id.fb);
    }

    private void login(String username, final String password) {
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            addLoginDataToDatabaseandSharedPref();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getphoneno() {
        fullnom = countryCodePicker.getDefaultCountryCodeWithPlus() + phone.getEditText().getText().toString();
        Toast.makeText(getContext(), fullnom, Toast.LENGTH_SHORT).show();
    }

    private void addLoginDataToDatabaseandSharedPref() {
        login_cred.setName(name.getEditText().getText().toString());
        login_cred.setPhone(fullnom);
        login_cred.setEmail_id(account.getEditText().getText().toString());
        login_cred.setUid(auth.getUid());
        login_cred.setUsername(NO_USERNAME_NOW);
        login_cred.setPicUri(NO_PHOTO_URI);
        database
                .child("User_cred")
                .child(Objects.requireNonNull(auth.getUid()))
                .child("Permanent_cred")
                .setValue(login_cred)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addtoSharedPref();
                        Toast.makeText(getContext(), "hellow honey", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(root).navigate(R.id.action_signupFrag_to_phoneVerFrag);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addtoSharedPref() {
        pref = getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("namePref", name.getEditText().getText().toString());
        editor.putString("phonePref", fullnom);
        editor.putString("uidPref", auth.getUid());
        editor.putString("emailPref", account.getEditText().getText().toString());
        editor.putString("passwordPref", password.getEditText().getText().toString());
        editor.apply();
    }

    //GOOGLE

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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == guh) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                namet = account.getDisplayName();
                tokenUID = account.getIdToken();
                photoUri = account.getPhotoUrl().toString();
                accountt = account.getEmail();
                id = account.getId();
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
                    addDatabaseinfoGoogle(namet, tokenUID, photoUri, accountt, NO_PHONE, NO_USERNAME_NOW);
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(getContext(), "YOU ARE FUCKED!!!!!", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addDatabaseinfoGoogle(String name, String tokenUID, String photoUri, String accountt, String noPhone, String noUsernameNow) {
        login_credGoogle.setEmail_id(accountt);
        login_credGoogle.setName(name);
        login_credGoogle.setPhone(noPhone);
        login_credGoogle.setPicUri(photoUri);
        login_credGoogle.setUid(tokenUID);
        login_credGoogle.setUsername(noUsernameNow);
        database
                .child("User_cred")
                .child(id)
                .child("Permanent_cred")
                .setValue(login_credGoogle)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addDataToPrefGOOGLE(login_credGoogle.getName(), login_credGoogle.getUid(), login_credGoogle.getEmail_id(), login_credGoogle.getPhone());
                        Toast.makeText(getContext(), "hellow honey", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(root).navigate(R.id.action_signupFrag_to_phoneVerFrag);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDataToPrefGOOGLE(String name, String tokenUID, String accountt, String Phone) {
        pref1 = getActivity().getSharedPreferences("GoogleLoginInfo", Context.MODE_PRIVATE);
        editor1 = pref1.edit();
        editor1.putString("GOOGLEnamePref", name);
        editor1.putString("GOOGLEphonePref", Phone);
        editor1.putString("GOOGLEuidPref", tokenUID);
        editor1.putString("GOOGLEemailPref", accountt);
        editor1.apply();
    }

    //Facebook

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = auth.getCurrentUser();
                            Navigation.findNavController(root).navigate(R.id.action_signupFrag_to_phoneVerFrag);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }







}

