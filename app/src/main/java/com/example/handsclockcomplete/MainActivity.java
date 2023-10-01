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

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView ivb = findViewById(R.id.clockFace);
        final ImageView ivh = findViewById(R.id.clockHands);
        drawBackground(ivb);
        initializeTimer(blinker);
    }

    final int AREA_SZ = 480;
    final float AREA_CENTER = AREA_SZ / 2.0f;
    final float FACE_RADIUS = AREA_SZ / 2.0f;
    final int MRG = 10;

    private void drawBackground(final ImageView ivb){
        final Bitmap background = Bitmap.createBitmap(AREA_SZ, AREA_SZ, Bitmap.Config.ARGB_8888);
        final Canvas cv = new Canvas(background);

        //back black circle fill
        final Paint bc = new Paint();
        bc.setColor(Color.BLACK);
        bc.setStrokeWidth(3);
        bc.setStyle(Paint.Style.FILL);
        cv.drawCircle(AREA_CENTER, AREA_CENTER, FACE_RADIUS, bc);

        //white outer circle line
        final Paint oc = new Paint();
        oc.setColor(Color.WHITE);
        oc.setStrokeWidth(3);
        oc.setStyle(Paint.Style.STROKE);
        cv.drawCircle(AREA_CENTER, AREA_CENTER, FACE_RADIUS - 10, oc);

        cv.drawLine(AREA_CENTER, MRG, AREA_CENTER, AREA_SZ - MRG, oc);
        cv.drawLine( MRG, AREA_CENTER, AREA_SZ - MRG, AREA_CENTER, oc);

        cv.drawCircle(AREA_CENTER, AREA_CENTER, FACE_RADIUS - 30, bc);


        ivb.setImageBitmap(background);
    }

    private Timer tmr;

    private void initializeTimer(final Runnable updater){

        if (tmr != null)
            tmr.cancel();

        tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                timerTick(updater);
            }
        }, 0,500);
    }

    private void timerTick(final Runnable updater) {
        this.runOnUiThread(updater);
    }

    private final Runnable blinker = () -> {
        drawHands();
    };

    private void drawHands() {
        final ImageView ivh = findViewById(R.id.clockHands);
        Calendar c = Calendar.getInstance();
        final int h = c.get(Calendar.HOUR_OF_DAY);
        final int m = c.get(Calendar.MINUTE);
        final int s = c.get(Calendar.SECOND);

        Bitmap hands = Bitmap.createBitmap(AREA_SZ, AREA_SZ, Bitmap.Config.ARGB_8888);
        Canvas hc = new Canvas(hands);

        //white hands
        final Paint oc = new Paint();
        oc.setColor(Color.WHITE);
        oc.setStrokeWidth(3);
        oc.setStyle(Paint.Style.STROKE);
        hc.drawCircle(AREA_CENTER, AREA_CENTER, FACE_RADIUS - 10, oc);

        hc.drawLine(
                (float) (AREA_SZ / 2.0f + 0.19f * AREA_SZ * Math.cos(30 * (h + m / 60.0f + s / 3600.0f) * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f + 0.19f * AREA_SZ * Math.sin(30 * (h + m / 60.0f + s / 3600.0f) * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f),
                (float) (AREA_SZ / 2.0f),
                oc
        );

        hc.drawLine(
                (float) (AREA_SZ / 2.0f + 0.31f * AREA_SZ * Math.cos(6 * (m + s / 60.0f) * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f + 0.31f * AREA_SZ * Math.sin(6 * (m + s / 60.0f) * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f),
                (float) (AREA_SZ / 2.0f),
                oc
        );

        hc.drawLine(
                (float) (AREA_SZ / 2.0f + 0.32f * AREA_SZ * Math.cos(6 * s * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f + 0.32f * AREA_SZ * Math.sin(6 * s * Math.PI / 180.0f - Math.PI / 2.0f)),
                (float) (AREA_SZ / 2.0f),
                (float) (AREA_SZ / 2.0f),
                oc
        );

        ivh.setImageBitmap(hands);
    }
}