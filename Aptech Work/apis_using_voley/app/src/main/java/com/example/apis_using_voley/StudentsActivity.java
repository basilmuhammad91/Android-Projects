package com.example.apis_using_voley;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentsActivity extends AppCompatActivity {

    ArrayList<Student> lst=new ArrayList<>();
    ListView listView;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        listView=findViewById(R.id.listview);

        adapter=new StudentAdapter(lst,this);
        listView.setAdapter(adapter);
        getdata();

    }
    //------------------ get data from API  ------------------
    public void getdata()
    {

        StringRequest fetchrequest=new StringRequest(
                Request.Method.GET,
                MainActivity. url + "public/api/Students/getStudents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jarr=new JSONArray(response);
                            for (int i =0 ;i<jarr.length(); i++)
                            {
                                JSONObject jobj=jarr.getJSONObject(i);
                                String StudentId=jobj.getString("StudentId");
                                String Name=jobj.getString("Name");
                                String Phone=jobj.getString("Phone");
                                String Email=jobj.getString("Email");
                                String Password=jobj.getString("Password");
                                String Image=jobj.getString("Image");
                                Image=MainActivity.url+"public/storage/"+ Image;
                                Student obj=new Student(StudentId,Name,Phone,Email,Password,Image);
                                lst.add(obj);
                            }
                            adapter.notifyDataSetChanged();

                        }
                        catch (Exception ex)
                        {
                            Toast.makeText(StudentsActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        );

       MainActivity.requestQueue.add(fetchrequest);

    }

    //------------------ Student Adapter ------------------
    class  StudentAdapter extends BaseAdapter
    {
        ArrayList<Student> lst;
        Context context;
        LayoutInflater inflater;

        public StudentAdapter(ArrayList<Student> lst, Context context) {
            this.lst = lst;
            this.context = context;
            inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return lst.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v=inflater.inflate(R.layout.std_layout,null);

            CircleImageView img=v.findViewById(R.id.std_img);
            TextView txtId=v.findViewById(R.id.std_id);
            TextView txtName=v.findViewById(R.id.std_name);
            TextView txtPhone=v.findViewById(R.id.std_phone);
            TextView txtEmail=v.findViewById(R.id.std_email);
            TextView txtPassword=v.findViewById(R.id.std_password);

            Student obj=lst.get(position);
            txtId.setText(obj.StudentId);
            txtName.setText(obj.Name);
            txtPhone.setText(obj.Phone);
            txtEmail.setText(obj.Email);
            txtPassword.setText(obj.Password);

            Picasso.with(context).load(obj.Image).into(img);

            return v;
        }
    }

}
