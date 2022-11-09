package com.example.scyjm5_cw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class ColourActivity extends AppCompatActivity {

    ImageButton eraser;
    ImageButton green;
    ImageButton black;
    ImageButton red;
    ImageButton blue;
    ImageButton yellow;
    TextView currentColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        //initialise all views
        initWidgets();

        Intent intent = getIntent();
        AtomicInteger colour = new AtomicInteger(intent.getIntExtra("ColourExtra", Color.YELLOW));
        currentColour.setText(getColourString(colour));


        //eraser button click listener
        eraser.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            currentColour.setText("Eraser");
            colour.set(Color.WHITE);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //green button click listener
        green.setOnClickListener(view -> {
            currentColour.setText("Green");
            Intent resultIntent = new Intent();
            colour.set(Color.GREEN);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //black button click listener
        black.setOnClickListener(view -> {
            currentColour.setText("Black");
            Intent resultIntent = new Intent();
            colour.set(Color.BLACK);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //red button click listener
        red.setOnClickListener(view -> {
            currentColour.setText("Red");
            Intent resultIntent = new Intent();
            colour.set(Color.RED);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //blue button click listener
        blue.setOnClickListener(view -> {
            currentColour.setText("Blue");
            Intent resultIntent = new Intent();
            colour.set(Color.BLUE);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //yellow button click listener
        yellow.setOnClickListener(view -> {
            currentColour.setText("Yellow");
            Intent resultIntent = new Intent();
            colour.set(Color.YELLOW);
            resultIntent.putExtra("resultColour", colour.get());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    //set the textview as the colour chosen
    private String getColourString(AtomicInteger colour) {
        if(colour.get() == Color.WHITE){
            return "Eraser";
        }
        else if(colour.get() == Color.GREEN){
            return "Green";
        }
        else if(colour.get() == Color.BLACK){
            return "Black";
        }
        else if(colour.get() == Color.RED){
            return "Red";
        }
        else if(colour.get() == Color.BLUE){
            return "Blue";
        }
        else if(colour.get() == Color.YELLOW){
            return "Yellow";
        }
        return "Error";
    }

    private void initWidgets() {
        eraser = findViewById(R.id.eraser);
        green = findViewById(R.id.green);
        black = findViewById(R.id.black);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        yellow = findViewById(R.id.yellow);
        currentColour = findViewById(R.id.colour);
    }
}