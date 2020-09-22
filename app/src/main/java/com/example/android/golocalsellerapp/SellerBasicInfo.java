package com.example.android.golocalsellerapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerBasicInfo extends AppCompatActivity {
    Intent intent;
    EditText outletName,outletAddress,outletLocality,outletContactName,outletNumber,outletPincode;
    Button buttonConfirm;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    OutletInformation outletInformation;
    Spinner spinnerCity;
    static final public String EMAIL__ID="EMAIL__ID",CITY_NAME="CITY_NAME";
    String Email,Password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.seller_basic_info);
        outletAddress = (EditText) findViewById(R.id.outletAddress);
        outletName = (EditText) findViewById(R.id.outletName);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCitySelector);
        outletLocality = (EditText) findViewById(R.id.outletLocality);
        outletContactName = (EditText) findViewById(R.id.outletContactName);
        outletNumber = (EditText) findViewById(R.id.outletContact);
        outletPincode = (EditText) findViewById(R.id.outletPincode);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        intent = getIntent();
        Email = intent.getExtras().getString(SignUpActivity.EMAIL_ID);
        Password = intent.getExtras().getString(SignUpActivity.PASSWORD);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });
    }

    public void saveInfo(){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    mRef = FirebaseDatabase.getInstance().getReference();
                    String email2 = Email.replace('.', ',');
                    outletInformation = new OutletInformation(Email,outletName.getText().toString().trim(),outletAddress.getText().toString(),spinnerCity.getSelectedItem().toString(),
                            outletLocality.getText().toString().trim(),outletContactName.getText().toString().trim(),outletPincode.getText().toString().trim(),outletNumber.getText().toString());
                    mRef.child("CITY").child(email2).setValue(spinnerCity.getSelectedItem().toString());
                    mRef.child("SELLERS").child(spinnerCity.getSelectedItem().toString()).child(email2).setValue(outletInformation);
                    Intent intent = new Intent(getApplicationContext(), AfterLoginSeller.class);
                    intent.putExtra(EMAIL__ID,Email);
                    intent.putExtra(CITY_NAME,spinnerCity.getSelectedItem().toString());
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}
