package com.example.insertwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static  String url = "http://192.168.1.105/MyLaravelWork/laravelapi/public/api/student";
    EditText txtName, txtEmail, txtPassword;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        requestQueue = Volley.newRequestQueue(this);

    }

    public void insertData(View view) {
        final String name = txtName.getText().toString();
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        StringRequest insert_request = new StringRequest(Request.Method.POST, url+"/submit", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> map=new HashMap<>();
                map.put("Name",name);
                map.put("Email",email);
                map.put("Password",password);
                return map;
            }
        };
        requestQueue.add(insert_request);

//        Toast.makeText(this, "My name is "+name+" , Email is "+email+" and password is "+password, Toast.LENGTH_SHORT).show();


    }
}