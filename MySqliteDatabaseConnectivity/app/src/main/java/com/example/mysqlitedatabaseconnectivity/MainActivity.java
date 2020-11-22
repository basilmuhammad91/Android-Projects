package com.example.mysqlitedatabaseconnectivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtId, txtName, txtPhone, txtEmail, txtPassword;
    SqLiteDb db = new SqLiteDb(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.itxtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        txtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                txtName.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtPassword.setText("");

                String id = editable.toString();
                try {
                    Cursor cursor = db.fetch(id);
                    while (cursor.moveToNext())
                    {
                    txtName.setText(cursor.getString(1));
                    txtPhone.setText(cursor.getString(2));
                    txtEmail.setText(cursor.getString(3));
                    txtPassword.setText(cursor.getString(4));
                    }

                }
                catch(Exception ex)
                {
                    Toast.makeText(MainActivity.this, ""+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void insertData(View view) {
        String Name = txtName.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();
        try {
            boolean isInsert = db.insert(Name, Phone, Email, Password);
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtPassword.setText("");
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ""+ex, Toast.LENGTH_SHORT).show();
        }

    }

    public void showInAlert(View view) {
        String str="";
        Cursor cursor = db.fetch();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);

            str += id + "\n";
            str += name + "\n";
            str += phone + "\n";
            str += email + "\n";
            str += password + "\n\n";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Student Record");
            builder.setMessage(str);
            builder.create().show();
        }
    }

    public void updatetData(View view) {
        String Id = txtId.getText().toString();
        String Name = txtName.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();
        boolean isUpdate = db.update(Id, Name, Phone, Email, Password);
        if(isUpdate)
        {
            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtPassword.setText("");
            Toast.makeText(this, "Record Updated !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(View view) {
        String Id = txtId.getText().toString();
        boolean isDelete = db.delete(Id);
        if(isDelete)
        {
            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtPassword.setText("");
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void showList(View view) {
        startActivity(new Intent(this, StudentsActivity.class));
    }
}