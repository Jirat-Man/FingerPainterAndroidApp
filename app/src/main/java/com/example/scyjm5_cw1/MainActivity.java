package com.example.scyjm5_cw1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1001;

    FingerPainterView myFingerPainterView;
    ImageButton gallery;
    ImageButton download;
    ImageButton brush;
    ImageButton palette;
    int height, width;
    int currentSize, currentShape, currentColour;

    //handle intent from Colour Activity
    ActivityResultLauncher<Intent> startForColour = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result != null && result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null){
                        myFingerPainterView.setColour(result.getData().getIntExtra("resultColour", Color.GREEN));
                        currentColour = result.getData().getIntExtra("resultColour", Color.GREEN);
                    }
                }
            });

    //handle intent from Brush Activity
    ActivityResultLauncher<Intent> startForBrush = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result != null && result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null){
                        if(result.getData().getStringExtra("resultBrush").equals("ROUND")){
                            myFingerPainterView.setBrush(Paint.Cap.ROUND);
                            currentShape = 1;
                        }
                        else if(result.getData().getStringExtra("resultBrush").equals("SQUARE")){
                            myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                            currentShape = 2;
                        }
                        else{
                            myFingerPainterView.setBrushWidth(Integer.parseInt(result.getData().getStringExtra("resultBrush")));
                            currentSize = Integer.parseInt(result.getData().getStringExtra("resultBrush"));
                        }
                    }
                }
            });

    //handle intent from Image from file
    ActivityResultLauncher<Intent> startForImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result != null && result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null){
                        Uri ChosenImg = result.getData().getData();
                        Bitmap bm = null;
                        try {
                            bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ChosenImg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myFingerPainterView.setImgBackground(bm, height, width);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();//find all the views by ID

        getDimensionOfScreen(); //get width and height of screen

        //if an image is selected from file set the draw the image onto the bitmap
        if(getIntent().getData() != null){
            setImage();
        }

        //handle screen rotating, loading saved data.
        if(savedInstanceState != null){
            currentShape = savedInstanceState.getInt("shape");
            currentColour = savedInstanceState.getInt("colour");
            currentSize = savedInstanceState.getInt("size");
            if(currentShape == 1){
                myFingerPainterView.setBrush(Paint.Cap.ROUND);
            }
            else if (currentShape == 2){
                myFingerPainterView.setBrush(Paint.Cap.SQUARE);
            }
            myFingerPainterView.setColour(currentColour);
            myFingerPainterView.setBrushWidth(currentSize);
        }

        //Download button click listener
        download.setOnClickListener(view -> {
            Toast.makeText(this, "Download Not Working", Toast.LENGTH_SHORT).show();
        });

        //Gallery button click listener
        gallery.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startForImage.launch(Intent.createChooser(intent, "Choose picture"));
        });

        //Brush button click listener
        brush.setOnClickListener(view -> {
            String currentBrushShape = myFingerPainterView.getBrush().toString();
            int currentBrushSize = myFingerPainterView.getBrushWidth();
            Intent intent = new Intent(MainActivity.this, BrushActivity.class);
            intent.putExtra("BrushShapeExtra", currentBrushShape);
            intent.putExtra("BrushSizeExtra", currentBrushSize);
            startForBrush.launch(intent);
        });

        //Palette button click listener
        palette.setOnClickListener(view -> {
            int currentColour = myFingerPainterView.getColour();
            Intent intent = new Intent(MainActivity.this, ColourActivity.class);
            intent.putExtra("ColourExtra", currentColour);
            startForColour.launch(intent);
        });
    }

    private void setImage() {
        Uri img = getIntent().getData();
        Bitmap bm = null;
        try {
            bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myFingerPainterView.setImgBackground(bm, height, width);
    }

    private void getDimensionOfScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        FingerPainterView canvas = findViewById(R.id.FingerPainterViewId);
        outState.putParcelable("canvasSave",canvas.onSaveInstanceState());
        super.onSaveInstanceState(outState);
        outState.putInt("shape", currentShape);
        outState.putInt("colour", currentColour);
        outState.putInt("size", currentSize);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initWidgets() {
        myFingerPainterView = findViewById(R.id.FingerPainterViewId);
        brush = (ImageButton) findViewById(R.id.BrushImage);
        palette = (ImageButton) findViewById(R.id.PaletteImage);
        gallery = (ImageButton) findViewById(R.id.GalleryImage);
        download = (ImageButton) findViewById(R.id.DownloadImage);
    }
}