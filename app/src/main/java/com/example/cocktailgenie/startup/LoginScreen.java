package com.example.cocktailgenie.startup;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cocktailgenie.MainActivity;
import com.example.cocktailgenie.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {


    //buttons
    public Button
            emailSignInButton,
            emailCreateAccountButton;

    public EditText
            fieldEmail,
            fieldPassword;

    // Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        fieldEmail = findViewById(R.id.fieldEmail);
        fieldPassword = findViewById(R.id.fieldPassword);

        // Buttons
        emailSignInButton = findViewById(R.id.emailSignInButton);
        emailCreateAccountButton = findViewById(R.id.emailCreateAccountButton);

        emailSignInButton.setOnClickListener(this);
        emailCreateAccountButton.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void registerUser() {
        String email = fieldEmail.getText().toString().trim();
        String password = fieldPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //create a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginScreen.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginScreen.this, "Registration Error, Check Email and Password.", Toast.LENGTH_LONG).show();
                    }
                });

    }

    //method for user login
    private void userLogin() {
        String email = fieldEmail.getText().toString().trim();
        String password = fieldPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //logging in the user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                });

    }


    @Override
    public void onClick(View v) {
        if (v == emailSignInButton) {
            userLogin();
        }

        if (v == emailCreateAccountButton) {
            registerUser();
        }
    }
}
