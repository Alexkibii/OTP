package com.xelacm.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOTPActivity extends AppCompatActivity {

private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
private String verificationId;
String countryCode = "+254";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        TextView textMobile = findViewById(R.id.textMobile);
        inputCode1 = findViewById(R.id.inputCode1);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);
setUpOtpInputs();
final ProgressBar progressBar = findViewById(R.id.progressbar);
final Button verifyBtn = findViewById(R.id.buttonVerify);
verificationId = getIntent().getStringExtra("verificationId");
String phoneNumb = countryCode+ getIntent().getStringExtra("mobile");
textMobile.setText(phoneNumb);
verifyBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(inputCode1.getText().toString().trim().isEmpty()||
           inputCode2.getText().toString().trim().isEmpty()||
        inputCode3.getText().toString().trim().isEmpty()||
        inputCode4.getText().toString().trim().isEmpty()||
                inputCode5.getText().toString().trim().isEmpty()||
                inputCode6.getText().toString().trim().isEmpty()){
            Toast.makeText(VerifyOTPActivity.this,"Enter Valid code", Toast.LENGTH_LONG).show();
            return;
        }
        String code = inputCode1.getText().toString() +
                      inputCode2.getText().toString() +
                      inputCode3.getText().toString() +
                inputCode4.getText().toString() +
                inputCode5.getText().toString() +
                inputCode6.getText().toString();
if (verificationId !=  null){
    progressBar.setVisibility(View.VISIBLE);
    verifyBtn.setVisibility(View.INVISIBLE);
    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code);
    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            progressBar.setVisibility(View.GONE);
            verifyBtn.setVisibility(View.VISIBLE);
            if(task.isSuccessful()){
                Toast.makeText(VerifyOTPActivity.this, "Verification passed", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(VerifyOTPActivity.this, "Invalid code", Toast.LENGTH_LONG).show();
            }
        }
    });
}
    }
});

    }

    private void setUpOtpInputs(){

        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}