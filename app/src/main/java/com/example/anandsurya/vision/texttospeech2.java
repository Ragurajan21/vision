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

import static android.R.attr.data;

public class texttospeech2 extends AppCompatActivity {
    Button speak, pause, play;
    final int REQUEST_CODE = 1234;
    int i = 0, j = 0, k = 0, l = 0;
    public static TextToSpeech tts;
    Button talk;
    String sentence = " Choose the following topics from the history . 1 part 1,  2 part 2   . Press Button and Say the option after the beep sound",
            sentence1 = " Choose the following TOPICS from the science.  1.Part 1 .   Press Button and Say the option after the beep sound";
    String s;
    ArrayList<String> options = new ArrayList<String>();
    // String file="testfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texttospeech2);
        Bundle bundle = getIntent().getExtras();
        s = bundle.getString("book");
        speak = (Button) findViewById(R.id.speak);
        talk = (Button) findViewById(R.id.talk);
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int value) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float) 0.65);
            }
        });

                /*speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent in=new Intent(Main2Activity.this,MainActivity.class);
                in.putExtra("file",file);
                startActivity(in);
                Intent

            }*/
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s.matches("History")) {
                    tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);
                }
                if (s.matches("Science")) {
                    tts.speak(sentence1, TextToSpeech.QUEUE_FLUSH, null);
                }


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
            if (s.matches("History")) {
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

                        i++;
                        j = 0;
                        k = 0;
                        l = 0;

                    } else if (i == 1) {
                        i = 0;
                        tts.speak("Part 1 was opened", TextToSpeech.QUEUE_FLUSH, null);
                        Intent intent = new Intent(this, texttospeech.class);
                        intent.putExtra("book", "part 1.txt");
                        startActivity(intent);
                    }
                } else if (matches_text.contains("two") || matches_text.contains("to")) {
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

                        i = 0;
                        k = 0;
                        l = 0;

                        j++;

                    } else if (j == 1) {
                        j = 0;
                        tts.speak("Part 2 is activated", TextToSpeech.QUEUE_FLUSH, null);
                        Intent intent = new Intent(this, texttospeech.class);
                        intent.putExtra("book", "part 2.txt");
                        startActivity(intent);
                    }
                }
            }
            if (s.matches("Science")) {
                ArrayList<String> matches_text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Log.e("Main2Activity", matches_text.toString());
                if (matches_text.contains("one")) {
                    if ((i == 1 | j == 1 | l == 1) && k == 0) {
                        tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                        talk.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                talk.performClick();
                            }
                        }, 1500);

                        j = 0;
                        i = 0;
                        l = 0;
                    } else if (k == 0) {
                        Toast.makeText(this, "1 has been said", Toast.LENGTH_SHORT).show();
                        tts.speak("Say 1 again to confirm", TextToSpeech.QUEUE_FLUSH, null);
                        talk.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                talk.performClick();
                            }
                        }, 2500);
                        j = 0;
                        i = 0;
                        l = 0;
                        k++;

                    } else if (k == 1) {
                        k = 0;
                        tts.speak("Part 3 is activated", TextToSpeech.QUEUE_FLUSH, null);
                        Intent intent = new Intent(this, texttospeech.class);
                        intent.putExtra("book", "part 3.txt");
                        startActivity(intent);
                    }
                }
            }

        }
    }
}
