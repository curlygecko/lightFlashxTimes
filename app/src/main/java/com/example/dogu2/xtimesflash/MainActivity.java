package com.example.dogu2.xtimesflash;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    EditText flashCount;
    ImageButton onOffButton;
    CameraManager cameraManager;
    String cameraId;
    boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashCount = findViewById(R.id.flashCount);
        onOffButton = findViewById(R.id.flashButton);

                cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        onOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                while(i < time()) {
                    i++;
                    turnOnFlash();
                    onOffButton.setImageResource(R.drawable.on);
                    try {
                        //sleep 0.50 seconds
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    turnOffFlash();
                    onOffButton.setImageResource(R.drawable.off);
                    try {
                        //sleep 0.50 seconds
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
            }
        });

    }

    public void turnOnFlash(){
        try {
            onOffButton.setImageResource(R.drawable.on);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode("0", true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void turnOffFlash(){
        try {
            onOffButton.setImageResource(R.drawable.off);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode("0", false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int time(){
        return Integer.parseInt(flashCount.getText().toString());
    }
}
