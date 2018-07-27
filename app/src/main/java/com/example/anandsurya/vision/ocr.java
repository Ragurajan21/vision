package com.example.anandsurya.vision;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.security.Policy;
import java.util.Locale;

public class ocr extends AppCompatActivity {
    SurfaceView cameraView;
    TextView textView;
    TextToSpeech tts;
    CameraSource cameraSource;
    final int RequestCameraPermissionId = 1001;
    String value="yes";
    private Camera.Parameters mParameters;
    private Camera camera;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionId: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        cameraView = (SurfaceView) findViewById(R.id.surface_view);
        textView = (TextView) findViewById(R.id.text_view);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int value){
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float)0.5);
            }
        });
      cameraView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              cameraView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent i=new Intent(ocr.this,HomeMenu.class);
                      startActivity(i);
                  }
              });
          }
      });

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("MainActivity", "Dectector dependecies are not yet available");

        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1290, 1024)
                    .setRequestedFps(1.0f)
                    .setAutoFocusEnabled(true)

                    .build();


            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ocr.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionId);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());


                        // Camera.Parameters p = camera.getParameters();
                        // p.setFlashMode(p.FLASH_MODE_AUTO);
                        // camera.setParameters(FLASH_MODE_AUTO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                    tts.stop();
                    tts.shutdown();
                }
            });
            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }
                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    //s   S`` sssss mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                    // Log.d("Flash", "Auto");
                    if(items.size() != 0)
                    {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i =0 ; i<items.size();++i)
                                {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                textView.setText(stringBuilder.toString());
                                final  String tv= textView.getText().toString();
                                cameraView.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        tts.speak(tv,TextToSpeech.QUEUE_ADD,null);
                                        return false;
                                        // if(tts.isSpeaking())
                                        // { cameraView.setOnClickListener(new V);}

                                    }
                                });
                              /*  if(tts!=null){
                                    tts.stop();
                                    tts.shutdown();
                                }
                                MainActivity.super.onDestroy();*/

                            }
                        });
                    }


                }
            });

        }


    }



}
