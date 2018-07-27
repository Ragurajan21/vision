package com.example.anandsurya.vision;


import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class texttospeech extends AppCompatActivity {
    ListView listView;
    TextToSpeech tts;
    static TextToSpeech tts1;
    static ArrayList<String> ContentList;
    Button add, read, play, next, prev;
    String s, text1;
    String total;
    static int i = 0, j = 0, k = 8, l = 1, p = -1;
    String txt;
    String result;
    TextView textView;
    static String list;
    Boolean completed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        txt = bundle.getString("book");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texttospeech);
        read = (Button) findViewById(R.id.read_button);
        listView = (ListView) findViewById(R.id.list_view);
        textView = (TextView) findViewById(R.id.textbox);
        add = (Button) findViewById(R.id.add_button);
        next = (Button) findViewById(R.id.next_button);
        prev = (Button) findViewById(R.id.previous_button);
        play = (Button) findViewById(R.id.play_button);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float) 0.7);
            }
        });
        tts1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts1.setLanguage(Locale.ENGLISH);
                tts1.setPitch(1);
                tts1.setSpeechRate((float) 0.7);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "HELLO", Toast.LENGTH_SHORT).show();
                try {
                    //Toast.makeText(MainActivity.this, "WORKING", Toast.LENGTH_SHORT).show();
                    InputStream inputStream = getAssets().open(txt);
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    text1 = new String(buffer);
                } catch (IOException e) {
                    //Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                textView.setText(text1);
                read.setVisibility(findViewById(R.id.read_button).GONE);
                next.setVisibility(findViewById(R.id.next_button).VISIBLE);
                prev.setVisibility(findViewById(R.id.previous_button).VISIBLE);

                add.performClick();
            }
        });
        // TextSet();
        {


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s = textView.getText().toString();
                    final String splited[] = s.split("\\.");
                    ContentList = new ArrayList<String>();

                    Toast.makeText(texttospeech.this, String.valueOf(splited.length), Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(texttospeech.this, android.R.layout.simple_list_item_1, ContentList);
                    listView.setAdapter(arrayAdapter);
                    //while(i <= (splited.length)) {

                    //  for(j=l;j<=k&&k<splited.length;j++)
                    //{
                    //    total=total+" "+splited[j];
                    //}
                    //ContentList.add(total);
                    //total="";
                    //tts.speak(splited[i],TextToSpeech.QUEUE_ADD,null);
                    //i=i+8;
                    //l=k+1;
                    //k=k+8;
                    for (i = 0; i < splited.length; i++) {
                        ContentList.add(splited[i]);
                    }
                    //}
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tts1.isSpeaking()) {
                        tts1.stop();
                    }

                    if (p < ContentList.size()) {
                        p++;
                        list = ContentList.get(p);
                        //Toast.makeText(MainActivity.this, list, 2000).show();
                        //Toast.makeText(MainActivity.this,String.valueOf(i), 2000).show();
                        tts.speak(list, TextToSpeech.QUEUE_FLUSH, null);
                    } else if (p >= ContentList.size()) {
                        tts.speak("The END", TextToSpeech.QUEUE_FLUSH, null);
                        p = -1;
                    }
                    //Toast.makeText(MainActivity.this, String.valueOf(p), Toast.LENGTH_SHORT).show();
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tts1.isSpeaking()) {
                        tts1.stop();
                    }

                    if (p == -1) {
                        tts.speak("Beginning", TextToSpeech.QUEUE_FLUSH, null);
                        p = -1;
                    }

                    if (p >= 0 && p < ContentList.size()) {
                        p--;
                        if (p == -1) {
                            tts.speak("Beginning", TextToSpeech.QUEUE_FLUSH, null);
                            p = 0;
                        }
                        list = ContentList.get(p);
                        //Toast.makeText(MainActivity.this, list, 2000).show();
                        //Toast.makeText(MainActivity.this,String.valueOf(i), 2000).show();
                        tts.speak(list, TextToSpeech.QUEUE_FLUSH, null);

                    }

                    //Toast.makeText(MainActivity.this, String.valueOf(p), Toast.LENGTH_SHORT).show();
                }
            });
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this, String.valueOf(p), Toast.LENGTH_SHORT).show();
                    while (p < ContentList.size()) {
                        p++;
                        //Toast.makeText(MainActivity.this,String.valueOf(p), Toast.LENGTH_SHORT).show();
                    /*    if(p < ContentList.size())
                    //{
                        list = ContentList.get(p);
                        //Toast.makeText(MainActivity.this, list, 2000).show();
                        //Toast.makeText(MainActivity.this,String.valueOf(i), 2000).show();
                         tts.speak(list, TextToSpeech.QUEUE_ADD, null);
                    }*/
                        //list = ContentList.get(p);
                        //tts1.speak(list, TextToSpeech.QUEUE_ADD, null);

                        play.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //                 next.performClick();
                                next.performClick();
                            }
                        }, 2500);

                        play.post(new Runnable() {
                            @Override
                            public void run() {
                                next.performClick();

                            }
                        });

                        //else
                        //{
                        //  tts.speak("The END", TextToSpeech.QUEUE_ADD, null);
                        //p = 1;
                        //}

                    }


                }
            });
            play.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    tts1.stop();
                }
            });

        }
    }
}

   /* private void TextSet() {
        try {
            //Toast.makeText(MainActivity.this, "WORKING", Toast.LENGTH_SHORT).show();
            InputStream inputStream = getAssets().open(txt);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text1 = new String(buffer);
        } catch (IOException e) {
            //Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        textView.setText(text1);
       // read.setVisibility(findViewById(R.id.read_button).GONE);
    }*/
