package com.example.fred.cabhair2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private EditText email;
    private EditText password;
    private TextView signin;
    private ProgressBar Progressbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);
        signin = (TextView) findViewById(R.id.textView3);
        Progressbar = new ProgressBar(this);


// ...
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener((View.OnClickListener) this);
        signin.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if(v == register)
        {
            Toast.makeText(this,"si le da click",Toast.LENGTH_LONG).show();
            registerUser();
        }
        if(v == signin)
        {

        }
    }

    private void registerUser() {
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

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User registered",Toast.LENGTH_LONG);}
                        else {Toast.makeText(MainActivity.this,"Not able to register",Toast.LENGTH_LONG);}
                    }
                });
    }
}
