package com.example.anandsurya.vision;


import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class texttospeech3 extends AppCompatActivity {
    Button speak,pause,play;
    final int REQUEST_CODE = 1234;
    int i=0,j=0,k=0,l=0;
    public static TextToSpeech tts;
    Button talk;
    String sentence = " Select the book from the following category:  1.Science.  2.History.   Press lower half of the screen and Say the option after the beep sound";
    ArrayList<String> options = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texttospeech3);
        speak = (Button) findViewById(R.id.speak);
        talk = (Button) findViewById(R.id.talk);
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int value) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float) 0.65);
            }
        });
        tts.speak("Welcome to Reading Books Activity",TextToSpeech.QUEUE_FLUSH,null);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("", TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches_text = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.e("Main2Activity", matches_text.toString());
            if (matches_text.contains("one")) {
                if ((j == 1 | k == 1 | l == 1) && i == 0) {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                            Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();
                        }
                    }, 1500);

                    j = 0;
                    k = 0;
                    l = 0;
                } else if (i == 0) {

                    Toast.makeText(this, "1 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 1 again to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                            //Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();
                        }
                    }, 2500);
                    tts.speak("Science Directory is selected ", TextToSpeech.QUEUE_FLUSH, null);


                    i++;
                    j = 0;
                    k = 0;
                    l = 0;

                } else if (i == 1) {
                    i = 0;
                    tts.speak("you have entered science directory", TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent = new Intent(this, texttospeech2.class);
                    intent.putExtra("book", "Science");
                    startActivity(intent);
                }
            }  if (matches_text.contains("two") || matches_text.contains("to")) {
                if ((i == 1 | k == 1 | l == 1) && j == 0) {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    }, 1500);

                    i = 0;
                    k = 0;
                    l = 0;
                } else if (j == 0) {
                    Toast.makeText(this, "2 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 2 again to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    }, 2500);
                    tts.speak("History Directory is selected ", TextToSpeech.QUEUE_FLUSH, null);


                    i = 0;
                    k = 0;
                    l = 0;

                    j++;

                } else if (j == 1) {
                    j = 0;
                    tts.speak("You have entered History directory", TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent = new Intent(this, texttospeech2.class);
                    intent.putExtra("book", "History");
                    startActivity(intent);
                }
            }
        }
    }}