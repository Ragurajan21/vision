package com.example.anandsurya.vision;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.anandsurya.vision.R.id.talk;

//import static com.example.user.news.MainActivity.i;
//import static com.example.user.news.R.id.talk;

public class newsfeed1 extends Activity {
    static boolean active = false;
    Button general, cricket, health, entertainment, speakbutton, talkbutton;
    TextView tv;
    final int REQUEST_CODE = 1234;
    public TextToSpeech tts;
    String s;
    String sentence = " CATEGORIES. 1. General. 2. Entertaiment. 3. Cricket. 4. Health. press the lower half of the screen to select the category";
    String sentence1 = "Press the right bottom corner of the screen to play the next news. Press the left bottom corner of the screen to play the previous news";
    int i = 0, j = 0, k = 0, l = 0;
    @Override
    public void onStart()
    {
        super.onStart();
        tts.speak(sentence1,TextToSpeech.QUEUE_ADD,null);
    }
    @Override
    public void onStop() {
        super.onStop();
        tts.shutdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed1);
        speakbutton = (Button) findViewById(R.id.news);
        talkbutton = (Button) findViewById(talk);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int value) {
                tts.setLanguage(Locale.UK);
                tts.setPitch(1);
                tts.setSpeechRate((float) 0.65);
            }
        });


        general = (Button) findViewById(R.id.news_general);
        tv = (TextView) findViewById(R.id.textviews);
        cricket = (Button) findViewById(R.id.new_cricket);
        health = (Button) findViewById(R.id.new_health);
        entertainment = (Button) findViewById(R.id.news_Entertainment);

        speakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(sentence, TextToSpeech.QUEUE_ADD, null);
              /*  speakbutton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        talkbutton.performClick();
                    }
                }, 1500);*/

            }
        });
        talkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak("",TextToSpeech.QUEUE_FLUSH,null);
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "VISION");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                general.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        s = "http://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
                    }
                },2500);
               // tts.speak(sentence1,TextToSpeech.QUEUE_FLUSH,null);
                s = "http://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";

                Intent intent = new Intent(newsfeed1.this, newsfeed.class);
                intent.putExtra("Value", s);
                startActivity(intent);

                //  intent = 0;

            }
        });
        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    cricket.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                s = "http://timesofindia.indiatimes.com/rssfeeds/4719161.cms";
                                            }
                                        },2500);
                 //   tts.speak(sentence1,TextToSpeech.QUEUE_FLUSH,null);
               // tts.speak(sentence1,TextToSpeech.QUEUE_FLUSH,null);
                Intent intent = new Intent(newsfeed1.this, newsfeed.class);
                intent.putExtra("Value", s);

                startActivity(intent);
                //   intent = 0;
            }
        });
        health.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                        health.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                s = "http://timesofindia.indiatimes.com/rssfeeds/3908999.cms  ";
                            }
                        },2500);
                     //   tts.speak(sentence1,TextToSpeech.QUEUE_FLUSH,null);

                        Intent intent = new Intent(newsfeed1.this, newsfeed.class);
                intent.putExtra("Value", s);
                startActivity(intent);
                //  intent = 0;
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override


                    public void onClick(View v){
                        entertainment.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                s = "http://timesofindia.indiatimes.com/rssfeeds/3908999.cms  ";
                            }
                        }, 2500);
                      //  tts.speak(sentence1, TextToSpeech.QUEUE_FLUSH, null);


                        s = "http://timesofindia.indiatimes.com/rssfeeds/1081479906.cms";

                        Intent intent = new Intent(newsfeed1.this, newsfeed.class);
                        intent.putExtra("Value", s);
                        startActivity(intent);
                        //   i = 0;
                    }

        });
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> matches_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        Log.e("Main2Activity", matches_text.toString());
        if (matches_text.contains("one") || matches_text.contains("won")) {
            //tts.speak("Did you say one?", TextToSpeech.QUEUE_FLUSH, null);


            Toast.makeText(newsfeed1.this, "1 has been said", Toast.LENGTH_SHORT).show();

            general.performClick();


        }
        if (matches_text.contains("two") || matches_text.contains("to")) {
            //tts.speak("Did you say one?", TextToSpeech.QUEUE_FLUSH, null);


            Toast.makeText(newsfeed1.this, "2 has been said", Toast.LENGTH_SHORT).show();

            entertainment.performClick();
        }
        if (matches_text.contains("three ") || matches_text.contains("tree") || matches_text.contains("thee")) {
            //tts.speak("Did you say one?", TextToSpeech.QUEUE_FLUSH, null);


            Toast.makeText(newsfeed1.this, "3 has been said", Toast.LENGTH_SHORT).show();

            cricket.performClick();
        }
        if (matches_text.contains("four ") || matches_text.contains("for")) {
            //tts.speak("Did you say one?", TextToSpeech.QUEUE_FLUSH, null);
            ;

            Toast.makeText(newsfeed1.this, "4 has been said", Toast.LENGTH_SHORT).show();

            health.performClick();
        }

    }
}


