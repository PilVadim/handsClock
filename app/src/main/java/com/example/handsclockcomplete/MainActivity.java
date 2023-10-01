package com.example.handsclockcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView ivb = findViewById(R.id.clockFace);
        final ImageView ivh = findViewById(R.id.clockHands);
        drawBackground(ivb);
        initializeTimer(ivh);
    }

    private void drawBackground(final ImageView ivb){
        final Bitmap background = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(background);
        final Paint bg = new Paint();
        bg.setColor(Color.BLACK);
        bg.setStrokeWidth(3);
        bg.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(250,250,150, bg);

        bg.setColor(Color.WHITE);
        bg.setStrokeWidth(3);
        bg.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(250,250,150, bg);

        ivb.setImageBitmap(background);
    }

    private void initializeTimer(final ImageView ivh ) {
        Bitmap hands = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);
        Canvas handsCanvas = new Canvas(hands);
        Paint paintHands = new Paint();
        paintHands.setColor(Color.parseColor("#FF0000"));
        paintHands.setStrokeWidth(10);
        paintHands.setStyle(Paint.Style.STROKE);
        handsCanvas.drawCircle(250,250,150, paintHands);
        ivh.setImageBitmap(hands);

    }
}