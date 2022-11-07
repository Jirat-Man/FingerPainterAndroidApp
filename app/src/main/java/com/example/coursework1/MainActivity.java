package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FingerPainterView myFingerPainterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFingerPainterView = findViewById(R.id.myFingerPainterViewId);
        myFingerPainterView.setColour(0xFF00FF00);
    }
    public void changeC(){
        myFingerPainterView.setColour(0xFF00FF00);
    }

}