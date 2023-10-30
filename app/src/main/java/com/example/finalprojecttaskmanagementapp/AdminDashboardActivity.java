package com.example.finalprojecttaskmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {

    ListView listView = findViewById(R.id.listView);

    ArrayList<UserModel> list_of_users = new ArrayList<UserModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Create an array of UserItem objects with your data

    }
}