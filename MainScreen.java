package com.example.fred.cabhair2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    private Button signout;
    private TextView currentUser;
    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = (TextView) findViewById(R.id.currUser);
        if(firebaseAuth.getCurrentUser() == null)
            {
                finish();
                startActivity(new Intent(this, Login.class));
            }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        currentUser.setText("welcome"+user.getEmail());
    }

    @Override
    public void onClick(View v) {
        if (v == signout)
            {firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
            }
    }
}
