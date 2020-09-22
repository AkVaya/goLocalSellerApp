package com.example.android.golocalsellerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    DatabaseReference mRef;
    Button Login,SignUp;
    FirebaseUser mUser;
    static final public String EMAIL__ID="EMAIL__ID",CITY_NAME="CITY_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        mUser = mAuth.getCurrentUser();
        Login = (Button) findViewById(R.id.buttonLogin);
        SignUp = (Button) findViewById(R.id.buttonSignUpMain);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            //Login();
        }
    }

    private void userLogin() {
        final String Email = editTextEmail.getText().toString(), Password = editTextPassword.getText().toString();
        if(Email.isEmpty()){
            editTextEmail.setError("Email should be entered");
        }
        if(Password.length()<6){
            editTextPassword.setError("The length of Password should at least be 6");
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    //Login();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*
    private void Login(){
        mRef = FirebaseDatabase.getInstance().getReference("TYPE");
        FirebaseUser mUser = mAuth.getCurrentUser();
        String email2 = mUser.getEmail().replace('.', ',');

        mRef = FirebaseDatabase.getInstance().getReference("TYPE").child(email2);
        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = snapshot.getValue().toString();

                if(val.equals("BUYERS")){
                    Intent intent = new Intent(MainActivity.this, AfterLoginBuyer.class);
                    startActivity(intent);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                else{
                    FirebaseDatabase mRef2 = FirebaseDatabase.getInstance().getReference();
                    Intent intent = new Intent(getApplicationContext(), AfterLoginSeller.class);
                    intent.putExtra(EMAIL__ID,Email);
                    intent.putExtra(CITY_NAME,outletCity.getText().toString());
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}