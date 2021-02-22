package com.xelacm.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

     PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);
        final EditText inputMobile = findViewById(R.id.inputMobile);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);

        ProgressBar progressBar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        buttonGetOTP.setOnClickListener(view -> {
            if(inputMobile.getText().toString().isEmpty()){
                Toast.makeText(SendOTPActivity.this, "Mobile Number Can't be Empty", Toast.LENGTH_LONG).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            buttonGetOTP.setVisibility(View.INVISIBLE);

            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+254"+inputMobile.getText().toString())       // Phone number to verify

                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setActivity(this)                 // Activity (for callback binding)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential credential) {

                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                    Toast.makeText(SendOTPActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId,
                                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                    progressBar.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                    intent.putExtra("mobile", inputMobile.getText().toString());
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            })          // OnVerificationStateChangedCallbacks
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);




            });
    }

}