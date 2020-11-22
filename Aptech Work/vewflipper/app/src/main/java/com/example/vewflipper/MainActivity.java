package com.example.vewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval (3000);
    }


    public void play (View v)
    {
        viewFlipper.startFlipping();
    }

    public void stop (View v)
    {
        viewFlipper.stopFlipping();
    }

    public void next (View v)
    {
        viewFlipper.showNext();
    }

    public void previous (View v)
    {
        viewFlipper.showPrevious();
    }


}
