package com.example.finalprojecttaskmanagementapp;

import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class UserUtility {

    public  boolean isSuccess = true;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private static DatabaseReference mDatabase;

    boolean isAdminSignup;

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

                        if(email.equals("kuber@gmail.com") || email.equals("jay@email.com") || email.equals("harshil@email.com")){
                            isAdminSignup = true;
                        }else {
                            isAdminSignup = false;
                        }

                        UserModel usermodel = new UserModel(userId, firstname, lastname, email, password, isAdminSignup);
                        usersRef.child("users").child(userId).setValue(usermodel);
                    } else {
                        isSuccess = false;
                        Exception exception = task.getException();
                        if (exception != null) {
                            String errorMessage = exception.getMessage();
                            Log.e("SignUp", "Error: " + errorMessage);
                        }
                    }
                });
        return isSuccess;
    }

    /*public interface LoginCallback {
        void onLoginResult(boolean isSuccess);
    }*/

    public boolean login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                isSuccess = true;
                //FirebaseUser user = mAuth.getCurrentUser();
            } else {
                isSuccess = false;
                Exception exception = task.getException();
                if (exception != null) {
                    String errorMessage = exception.getMessage();
                    Log.e("SignUp", "Error: " + errorMessage);
                }
            }
        });
        //callback.onLoginResult(isSuccess);
        return isSuccess;
    }

    public boolean searchAdmin(String id){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(id);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    isAdminSignup = userModel.isAdmin;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return isAdminSignup;
    }


    /*public void searchUserById(String userID) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(userID);

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User with the specified userID exists
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    // Now you can use the userModel object
                    // For example, log the user's email
                    if (userModel != null) {
                        String userEmail = userModel.getEmail();
                        Log.d("SearchUser", "User email: " + userEmail);
                    }
                } else {
                    // User with the specified userID does not exist
                    Log.d("SearchUser", "User not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors or cancellations here
                Log.e("SearchUser", "Error searching user: " + databaseError.getMessage());
            }
        });
    }*/




}
