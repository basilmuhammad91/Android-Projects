package com.example.myapplication;

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

public class MainActivity extends AppCompatActivity {

    EditText txtName,txtPhone,txtEmail,txtPassword,txtId;
    MySQLiteDb db=new MySQLiteDb(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId=findViewById(R.id.txtId);
        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);

        try {
            Bundle bundle=getIntent().getExtras();
            String Id=bundle.getString("Id","");
            String Name=bundle.getString("Name","");
            String Phone=bundle.getString("Phone","");
            String Email=bundle.getString("Email","");
            String Password=bundle.getString("Password","");
            txtId.setText(Id);
            txtName.setText(Name);
            txtPhone.setText(Phone);
            txtEmail.setText(Email);
            txtPassword.setText(Password);
        }catch(Exception exe){}





//--------------------------------------------------------------
        txtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                txtName.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                String id=editable.toString();

                try {
                    Cursor cursor=db.fetch(id);
                    if(cursor.moveToNext())
                    {
                        txtName.setText(cursor.getString(1));
                        txtPhone.setText(cursor.getString(2));
                        txtEmail.setText(cursor.getString(3));
                        txtPassword.setText(cursor.getString(4));

                    }

                }catch(Exception exe){}


            }
        });
//--------------------------------------------------------------

    }

    public void insertStudent(View view)
    {
        String Name=txtName.getText().toString();
        String Phone=txtPhone.getText().toString();
        String Email=txtEmail.getText().toString();
        String Password=txtPassword.getText().toString();

        boolean IsInsert=db.insert(Name,Phone,Email,Password);
        if(IsInsert)
        {
            Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();



    }

    public void showInAlert(View view)
    {
        String str="";
       Cursor cursor= db.fetch();
       while (cursor.moveToNext())
       {
           String id=cursor.getString(0);
           String name=cursor.getString(1);
           String phone=cursor.getString(2);
           String email=cursor.getString(3);
           String pass=cursor.getString(4);

           str+=id+"\n";
           str+=name+"\n";
           str+=phone+"\n";
           str+=email+"\n";
           str+=pass+"\n\n";

       }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
       builder.setTitle("Students records");
       builder.setMessage(str);
       builder.create().show();


    }

    public void updateStudent(View view)
    {
        String id=txtId.getText().toString();
        String Name=txtName.getText().toString();
        String Phone=txtPhone.getText().toString();
        String Email=txtEmail.getText().toString();
        String Password=txtPassword.getText().toString();

        boolean IsUpdate=db.update(id,Name,Phone,Email,Password);
        if(IsUpdate)
        {
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view)
    {
        String id=txtId.getText().toString();
        boolean IsDeleted=db.delete(id);
        if(IsDeleted)
        {
            Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    public void Student_Activity(View view)
    {
        startActivity(new Intent(this,StudentsActivity.class));
    }
}
