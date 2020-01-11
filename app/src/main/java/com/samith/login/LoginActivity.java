package com.samith.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Declare Variables

    EditText emailLogin,passwordLogin;
    private FirebaseAuth mAuth;
    String sEmailLogin, sPasswordLogin;
    Button signUp,signIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Set Content to Variables */

        setContentView(R.layout.activity_login);
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);

        /* Initialize Firebase */

        mAuth = FirebaseAuth.getInstance();

    }

    // Checking is That User Already Login In.!

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null){
            Toast.makeText(LoginActivity.this, "You Are Already Login", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(LoginActivity.this, "Please Login!", Toast.LENGTH_SHORT).show();
        }
    }

    //Login Function.

    public void loginClicked(View view){

        /* Saving Email and Password in to String.*/

        sEmailLogin = emailLogin.getText().toString();
        sPasswordLogin = passwordLogin.getText().toString();

        /* Adding Condition To Check is Edit Text are Empty  */

        if (sEmailLogin.isEmpty() && sPasswordLogin.isEmpty()){
            Toast.makeText(this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
        }else if (sEmailLogin.isEmpty()){
            emailLogin.setError("Enter a Email");

        } else if (sPasswordLogin.isEmpty()){
            passwordLogin.setError("Enter a Password");

        }



        else if (sEmailLogin != ""){
            if (sPasswordLogin != ""){

                /* Adding onCompleteListener to check Firebase email,password */

                mAuth.signInWithEmailAndPassword(sEmailLogin, sPasswordLogin).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                /* Adding Condition to Sending User to Next Activity */

                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login Success...", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(LoginActivity.this, "ReCheck Email and Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
         else {
            Toast.makeText(LoginActivity.this, "Occurred Error", Toast.LENGTH_SHORT).show();
        }

    }

    //Register Function.

    public void signUpClicked(View view){

        /* Saving Email and Password in to String.*/

        sEmailLogin = emailLogin.getText().toString();
        sPasswordLogin = passwordLogin.getText().toString();

        /* Adding Condition To Check is Edit Text are Empty  */

        if (sEmailLogin.isEmpty() && sPasswordLogin.isEmpty()){
            Toast.makeText(this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
        }else if (sEmailLogin.isEmpty()){
            emailLogin.setError("Enter a Email");

        } else if (sPasswordLogin.isEmpty()){
            passwordLogin.setError("Enter a Password");

        }



        else if (sEmailLogin != ""){
            if (sPasswordLogin != ""){

                /* Adding onCompleteListener to check Firebase email,password */

                mAuth.createUserWithEmailAndPassword(sEmailLogin, sPasswordLogin).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        /* Adding Condition to Sending User to Next Activity If Register Success*/

                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Register Success...", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "ReCheck Email and Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        else {
            Toast.makeText(LoginActivity.this, "Occurred Error", Toast.LENGTH_SHORT).show();
        }

    }

    //Forgot Password Function.

    public void forgotPasswordClicked(View view){
        passwordLogin.setVisibility(View.INVISIBLE);
        signIn.setVisibility(View.INVISIBLE);
        signUp.setVisibility(View.INVISIBLE);

        String getemail = emailLogin.getText().toString();
        if (getemail.isEmpty()){
            emailLogin.setError("Enter a Email");
            Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(getemail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Password Rest Send", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
