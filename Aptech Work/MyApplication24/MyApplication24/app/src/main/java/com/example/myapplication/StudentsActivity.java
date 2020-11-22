package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> lst;
    StudentAdapter adapter;

    public MySQLiteDb db=new MySQLiteDb(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        listView=findViewById(R.id.listView);
        lst=db.fetchStudents();

        adapter=new StudentAdapter(lst,this);
        listView.setAdapter(adapter);



    }

class  StudentAdapter extends BaseAdapter
{
    ArrayList<Student> lst;
    Context context;
    LayoutInflater inflater;

    public StudentAdapter(ArrayList<Student> lst, Context context) {
        this.lst = lst;
        this.context = context;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=inflater.inflate(R.layout.std_layout,null);
        TextView txtId=v.findViewById(R.id.std_id);
        TextView txtName=v.findViewById(R.id.std_name);
        TextView txtPhone=v.findViewById(R.id.std_phone);
        TextView txtEmail=v.findViewById(R.id.std_email);
        TextView txtPassword=v.findViewById(R.id.std_password);

        ImageView imgEdit=v.findViewById(R.id.std_img_edit);
        ImageView imgView=v.findViewById(R.id.std_img_view);
        ImageView imgDelete=v.findViewById(R.id.std_img_delete);


       final Student obj=lst.get(i);

        txtId.setText(obj.Id);
        txtName.setText(obj.Name);
        txtPhone.setText(obj.Phone);
        txtEmail.setText(obj.Email);
        txtPassword.setText(obj.Password);
//------------------------  Delete record   -----------------
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Record");
                builder.setMessage("Are you sure do you want to delete this record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       boolean isDelete= db.delete(obj.Id);
                       if(isDelete)
                       {
                           lst=db.fetchStudents();
                           adapter.notifyDataSetChanged();
                           Toast.makeText(StudentsActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                       }
                       else
                           Toast.makeText(StudentsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

//-----------------------  end  ----------------------
//-----------------------  Update Record  ----------------------

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,MainActivity.class);
                intent.putExtra("Id",obj.Id);
                intent.putExtra("Name",obj.Name);
                intent.putExtra("Phone",obj.Phone);
                intent.putExtra("Email",obj.Email);
                intent.putExtra("Password",obj.Password);
                startActivity(intent);

            }
        });

//------------------------------- end ----------------------------

//------------------------------ View Data  -----------------------------

//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(context,MainActivity.class);
//                intent.putExtra("Id",obj.Id);
//                intent.putExtra("Name",obj.Name);
//                intent.putExtra("Phone",obj.Phone);
//                intent.putExtra("Email",obj.Email);
//                intent.putExtra("Password",obj.Password);
//                startActivity(intent);
//
//            }
//        });
//-------------------------------------------------------------------






        return v;
    }
}


}