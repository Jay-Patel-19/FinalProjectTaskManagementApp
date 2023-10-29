package com.example.finalprojecttaskmanagementapp;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class UserUtility {

    public  boolean isSuccess;
    private static FirebaseAuth mAuth;
    private static DatabaseReference mDatabase;

    boolean isAdmin;

    /*public boolean signUp(String firstname, String lastname, String email, String password, boolean isAdmin){

        Task<AuthResult> task = mAuth.createUserWithEmailAndPassword(email, password);

        //mAuth.createUserWithEmailAndPassword(email, password);






        return true;
    }*/

    public boolean signUp(String firstname, String lastname, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        isSuccess = true;
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid();
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

                        if(email.equals("kuber@gmail.com") || email=="jay@gmail.com" || email=="harshil@gmail.com"){
                            isAdmin = true;
                        }else {
                            isAdmin = false;
                        }

                        UserModel usermodel = new UserModel(userId, firstname, lastname, email, password, isAdmin);
                        usersRef.child("users").child(userId).setValue(usermodel);
                        // Registration successful
                        // You can add additional logic here if needed
                        // For example, create a user profile in the database
                        // based on the provided parameters (firstname, lastname, isAdmin)
                        //createUserProfile(firstname, lastname, isAdmin);
                    } else {
                        isSuccess = false;
                        // Registration failed
                        // Log the error or handle it as needed
                        Exception exception = task.getException();
                        if (exception != null) {
                            String errorMessage = exception.getMessage();
                            Log.e("SignUp", "Error: " + errorMessage);
                        }
                    }
                });

        // The task is asynchronous, so you can't directly return a result here.
        // Instead, you can handle the success or failure in the callback above.
        return isSuccess; // Return a default value or indication
    }




}
