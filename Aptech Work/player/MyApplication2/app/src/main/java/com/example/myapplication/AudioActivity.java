package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class AudioActivity extends AppCompatActivity {

    MediaPlayer player;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        seekBar=findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                player.seekTo(seekBar.getProgress());
            }
        });

    }




    public void play_audio(View view)
    {
        if(uri==null)
        {
            Toast.makeText(this, "select Audio First", Toast.LENGTH_SHORT).show();
        }
       else
        {
            if(isStop)
            {
                player=MediaPlayer.create(this,uri);
                player.start();

            }
            else
            {
                player.start();
            }
        }



    }
    boolean isStop=false;
    public void pause_audio(View view)
    {
        player.pause();
        isStop=false;
    }

    public void stop_audio(View view)
    {
        player.stop();
        isStop=true;
    }

    public void select_audio(View view)
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(intent,101);


    }
    Uri uri=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==101)
        {

            uri=data.getData();
           try {
               player.stop();
           }catch (Exception ex)
           {

           }

            player=MediaPlayer.create(this,uri);

            player.start();
            seekBar.setMax(player.getDuration());
            handler.postDelayed(runnable,100);
        }
    }
    //------------ Thread for  Sync seekbar with Player -----------------------

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {

            seekBar.setProgress(player.getCurrentPosition());
            if(player.getDuration()!=player.getCurrentPosition())
            {
                handler.postDelayed(this,1000);
            }

        }
    };


    //-------------------------------------------------- ----------------------
}
