package com.example.sqllitedatabaseconnectivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtName, txtPhone, txtEmail, txtPassword;
    SqLiteDb db = new SqLiteDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
//---------------------SETTING EDIT TEXT VALUES FROM LIST VIEW EDIT BUTTON/IMG--------------------------------
        try {
            Bundle bundle = getIntent().getExtras();
            String Id = bundle.getString("Id", "");
            String Name = bundle.getString("Name", "");
            String Phone = bundle.getString("Phone", "");
            String Email = bundle.getString("Email", "");
            String Password = bundle.getString("Password", "");

            txtId.setText(Id);
            txtName.setText(Name);
            txtPhone.setText(Phone);
            txtEmail.setText(Email);
            txtPassword.setText(Password);
        }
        catch(Exception ex)
        {

        }
//--------------------------END ---------------------------------

//---------------------------FETCH AND SET DATA THROUGH ID ---------------------------------
        txtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable editable) {

                txtName.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
                txtPassword.setText("");

                String id = editable.toString();
                try {
                    Cursor cursor = db.fetch(id);
                    if (cursor.moveToNext())
                    {
                        txtName.setText(cursor.getString(1));
                        txtPhone.setText(cursor.getString(2));
                        txtEmail.setText(cursor.getString(3));
                        txtPassword.setText(cursor.getString(4));
                    }
                }
                catch (Exception e){ }
            }
        });
//---------------------------END ---------------------------------
    }

    public void insertStudent(View view) {
        String Name = txtName.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Email = txtEmail.getText().toString();
        String Passowrd = txtPassword.getText().toString();

        boolean isInsert = db.insert(Name, Phone, Email, Passowrd);
        if(isInsert)
        {
            txtName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void showInAlert(View view) {
        String str="";
        Cursor cursor = db.fetch();
        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);

            str+=id+"\n";
            str+=name+"\n";
            str+=phone+"\n";
            str+=email+"\n";
            str+=password+"\n\n";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Student Record");
            builder.setMessage(str);
            builder.create().show();

        }
    }

    public void updateStudent(View view) {
        String Id = txtId.getText().toString();
        String Name = txtName.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Email = txtEmail.getText().toString();
        String Passowrd = txtPassword.getText().toString();

        boolean isInsert = db.update(Id, Name, Phone, Email, Passowrd);
        if(isInsert)
        {
            txtId.setText("");
            txtName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent(View view) {
        String id = txtId.getText().toString();
        boolean isDelete =  db.delete(id);
        if (isDelete)
        {
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void showRecord(View view) {
        startActivity(new Intent(this, StudentsActivity.class));
    }
}