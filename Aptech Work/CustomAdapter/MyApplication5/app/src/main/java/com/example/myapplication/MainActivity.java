package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> lst=new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        lst.add(new Student("ali","0320-9865321","ali@gmail.com",R.drawable.img1));
        lst.add(new Student("Azhar Abbas","0320-9865321","azhar@gmail.com",R.drawable.img2));
        lst.add(new Student("Aisha","0320-9865321","Aisha@gmail.com",R.drawable.img3));
        lst.add(new Student("Rabia","0320-9865321","rabia@gmail.com",R.drawable.img4));
        lst.add(new Student("Afshan","0320-9865321","afshan@gmail.com",R.drawable.img5));
        lst.add(new Student("Malik inayat","0320-9865321","malik@gmail.com",R.drawable.img6));
        lst.add(new Student("Anas","0320-9865321","ali@gmail.com",R.drawable.img7));
        lst.add(new Student("Azeem ali","0320-9865321","azeem@gmail.com",R.drawable.img8));
        lst.add(new Student("Arsalan khan","0320-9865321","arsalan@gmail.com",R.drawable.img9));
        lst.add(new Student("Sheeraz hussain","0320-9865321","sheeraz@gmail.com",R.drawable.img10));

        listView.setAdapter(new StudentAdapter(lst,this));


    }
}

class StudentAdapter extends BaseAdapter
{
 ArrayList<Student> lst;
 Context context;
 LayoutInflater inflater;

    public StudentAdapter(ArrayList<Student> lst, Context context) {
        this.lst = lst;
        this.context = context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View v=inflater.inflate(R.layout.item_layout,null);
        ImageView imageView=v.findViewById(R.id.imageView);
        TextView txtName=v.findViewById(R.id.txtName);
        TextView txtPhone=v.findViewById(R.id.txtPhone);
        TextView txtEmail=v.findViewById(R.id.txtEmail);

        Student std=lst.get(i);
        imageView.setImageResource(std.img);
        txtName.setText(std.Name);
        txtPhone.setText(std.Phone);
        txtEmail.setText(std.Email);
        return v;
    }
}





















class Student
{
    String Name,Phone,Email;
    int img;

    public Student(String name, String phone, String email, int img) {
        Name = name;
        Phone = phone;
        Email = email;
        this.img = img;
    }
}