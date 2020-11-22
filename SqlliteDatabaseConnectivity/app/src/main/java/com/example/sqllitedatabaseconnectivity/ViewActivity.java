package com.example.sqllitedatabaseconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView txtId, txtName, txtPhone, txtEmail, txtPassword;

        txtId = findViewById(R.id.s_id);
        txtName = findViewById(R.id.s_name);
        txtPhone = findViewById(R.id.s_phone);
        txtEmail = findViewById(R.id.s_email);
        txtPassword = findViewById(R.id.s_password);

        Bundle bundle = getIntent().getExtras();
        String Id = bundle.getString("Id", "");
        String Name = bundle.getString("Name", "");
        String Phone = bundle.getString("Phone", "");
        String Email = bundle.getString("Email", "");
        String Password = bundle.getString("Password", "");

        txtId.setText("Id: "+Id);
        txtName.setText("Name: "+Name);
        txtPhone.setText("Phone: "+Phone);
        txtEmail.setText("Email: "+Email);
        txtPassword.setText("Password: "+Password);


    }
}