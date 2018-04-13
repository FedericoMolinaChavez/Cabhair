package com.example.fred.cabhair2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignin;
    private EditText email;
    private EditText password;
    private TextView Signup;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        email = (EditText) findViewById(R.id.EmailText);
        password = (EditText) findViewById(R.id.PasswordText);
        Signup = (TextView) findViewById(R.id.textView3);

        buttonSignin.setOnClickListener(this);
        Signup.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
            {
                finish();
                startActivity(new Intent(Login.this,MainScreen.class));
            }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSignin)
            {
                userLogin();
            }
        if (v == Signup)
            {
                finish();
                startActivity(new Intent(this,MainActivity.class));
            }

        }

    private void userLogin()
    {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Email is empty",Toast.LENGTH_LONG);
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Password is empty", Toast.LENGTH_LONG);
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Toast.makeText(Login.this,"entra",Toast.LENGTH_LONG).show();
                if (task.isSuccessful()){
                finish();
                startActivity(new Intent(Login.this,MainScreen.class));}
            }
        });
    }
}

