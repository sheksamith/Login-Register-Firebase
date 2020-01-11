package com.samith.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        usernameTextView = findViewById(R.id.usernameTextView);

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        String userEmail = "email: ";

        if (firebaseUser != null){
            userEmail = userEmail.concat(" ").concat(firebaseUser.getEmail());
        }else{
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
        usernameTextView.setText(userEmail);

    }
    public void logOutClicked(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
