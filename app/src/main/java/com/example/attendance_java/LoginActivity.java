package com.example.attendance_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MainActivity DBReference = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        //reference to xml
        EditText Emp_no = findViewById(R.id.Empno);
        EditText otp = findViewById(R.id.otp);
        Button getotp = findViewById(R.id.getotp);
        Button verifyotp = findViewById(R.id.verifyotp);

        //get otp button action
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestverificationcode(employeeno);

            }
        });

        //verify button action
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(Emp_no.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"Enter employee no",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String employeeno = Emp_no.getText().toString();
                    requestverificationcode(employeeno);
                }

                verifycode();

            }
        });


    }
    //method to request otp code
    private void requestverificationcode(String employeeno)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber()       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    //method to verify otp code
    private void verifycode(){
    }
}