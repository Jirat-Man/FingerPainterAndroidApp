package com.example.scyjm5_cw1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BrushActivity extends AppCompatActivity {

    Button roundBrush;
    Button squareBrush;
    TextView brushShape;
    TextView brushSize;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);

        //initialise views
        initWidgets();

        //get Intent from main activity
        receiveIntent();

        //seekBar listener
        seekBar.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            int temp = 0;
            Intent resultIntent = new Intent();
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                brushSize.setText(String.valueOf(i));
                temp = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                resultIntent.putExtra("resultBrush", String.valueOf(temp));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }));
        //squareBrush button listener
        squareBrush.setOnClickListener(view -> {
            ((TextView)findViewById(R.id.BrushShape)).setText("SQUARE");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("resultBrush", brushShape.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        //roundBrush button listener
        roundBrush.setOnClickListener(view -> {
            ((TextView)findViewById(R.id.BrushShape)).setText("ROUND");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("resultBrush", brushShape.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @SuppressLint("SetTextI18n")
    private void receiveIntent() {
        Intent intent = getIntent();
        String Shape = intent.getStringExtra("BrushShapeExtra");
        int Size = intent.getIntExtra("BrushSizeExtra", 20);
        brushShape.setText(Shape);
        brushSize.setText(Integer.toString(Size));
        seekBar.setProgress(Size);
    }

    private void initWidgets() {
        brushShape = findViewById(R.id.BrushShape);
        brushSize = findViewById(R.id.BrushSize);
        squareBrush = findViewById(R.id.SquareBrush);
        roundBrush = findViewById(R.id.RoundBrush);
        seekBar = findViewById(R.id.SeekBar);
    }
}