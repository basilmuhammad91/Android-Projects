package com.example.apis_using_voley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public  static  String url="http://11.40.0.27:92/project/";
    EditText txtName,txtPhone,txtEmail,txtPassword;
    ImageView imageView;
    public static RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        imageView=findViewById(R.id.img);
        requestQueue= Volley.newRequestQueue(this);

    }

    // --------------    Call Api for Insert Record--------------
    public void insertStudent(View view)
    {
        final String name=txtName.getText().toString();
        final String phone=txtPhone.getText().toString();
        final String email=txtEmail.getText().toString();
        final String password=txtPassword.getText().toString();
        // convert imageView->Drawable->bitmap->byteArray->base64 String
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        final String img_base64= Base64.encodeToString(byteArray, Base64.DEFAULT);
        //---------
        StringRequest insert_request=new StringRequest(
                Request.Method.POST,
                url+"public/api/Students/insert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jobj=new JSONObject(response);
                            String res=jobj.getString("Response");
                            Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage(error.getMessage())
                            .create()
                            .show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> map=new HashMap<>();
                map.put("Name",name);
                map.put("Phone",phone);
                map.put("Email",email);
                map.put("Password",password);
                map.put("Image",img_base64);


                return map;
            }
        };
        requestQueue.add(insert_request);
    }
    // --------------    File Chooser for select Image --------------
    public void bowseImage(View view)
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,102);
    }

    // --------------    get image from file chooser  --------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==102 && resultCode==RESULT_OK )
        {
            Uri uri=data.getData();
            imageView.setImageURI(uri);

        }

    }

    public void getdata(View view)
    {

        StringRequest fetchrequest=new StringRequest(
                Request.Method.GET,
                url + "public/api/Students/getStudents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            String str="";
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

                                str+=StudentId+"\n";
                                str+=Name+"\n";
                                str+=Phone+"\n";
                                str+=Email+"\n";
                                str+=Password+"\n";
                                str+=Image+"\n\n";
                            }

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Data")
                                    .setMessage(str)
                                    .create()
                                    .show();
                        }
                        catch (Exception ex)
                        {
                            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        requestQueue.add(fetchrequest);

    }

    public void student_list(View view)
    {
        Intent intent=new Intent(this,StudentsActivity.class);
        startActivity(intent);
    }
}
