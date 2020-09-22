package com.example.android.golocalsellerapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button signUpButton,login;
    static final public String EMAIL_ID="EMAIL_ID",PASSWORD="PASSWORD";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        signUpButton = (Button) findViewById(R.id.buttonSignUpSeller);
        login = (Button) findViewById(R.id.buttonLogin);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            }
        });

    }
    private void signUp(){
        final String Email = editTextEmail.getText().toString().trim(), Password = editTextPassword.getText().toString().trim();
        if (Email.isEmpty()) {
            editTextEmail.setError("Email should be entered");
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Enter a Valid Email Address");
        }
        if (Password.length() < 6) {
            editTextPassword.setError("The length of Password should at least be 6");
        }
        if(Password.length() >= 6 && Patterns.EMAIL_ADDRESS.matcher(Email).matches()){

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            });
            Intent intent = new Intent(this,SellerBasicInfo.class);
            intent.putExtra(EMAIL_ID,Email);
            intent.putExtra(PASSWORD,Password);
            finish();
            startActivity(intent);
        }
    }
}
