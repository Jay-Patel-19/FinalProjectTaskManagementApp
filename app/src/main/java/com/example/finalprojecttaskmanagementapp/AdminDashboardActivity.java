package com.example.finalprojecttaskmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Create an array of UserItem objects with your data
        ArrayList<UserItem> userItems = new ArrayList<>();
        userItems.add(new UserItem("John", "Doe", "john.doe@example.com"));
        userItems.add(new UserItem("Jane", "Smith", "jane.smith@example.com"));
        // Add more UserItem objects as needed

        // Create a custom adapter and set it for the ListView
        CustomUserItemAdapter adapter = new CustomUserItemAdapter(this, userItems);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}