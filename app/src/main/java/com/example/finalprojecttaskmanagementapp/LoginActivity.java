package com.example.finalprojecttaskmanagementapp;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginBtn, gotoSignUp;

    boolean loggedIn, adminLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserUtility userutil = new UserUtility();
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        gotoSignUp = findViewById(R.id.gotoSignUp);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = user != null ? user.getUid() : null;

                            if (userId != null) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            UserModel userModel = snapshot.getValue(UserModel.class);
                                            if (userModel != null) {
                                                if (Boolean.TRUE.equals(userModel.getAdmin())) {
                                                    // User is an admin
                                                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    // User is not an admin
                                                    Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            } else {
                                                // Handle the case where userModel is null
                                                Toast.makeText(LoginActivity.this, "User data not available", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Handle the case where the data doesn't exist
                                            Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        // Handle the error
                                        Toast.makeText(LoginActivity.this, "Database not working", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // Handle the case where userId is null
                                Toast.makeText(LoginActivity.this, "User ID not available", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // The login failed
                            Exception exception = task.getException();
                            if (exception != null) {
                                String errorMessage = exception.getMessage();
                                Log.e("SignIn", "Error: " + errorMessage);
                                Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}

