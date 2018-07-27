package com.example.anandsurya.vision;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class newsfeed extends newsfeed1 {
    //TextView titleEditText, linkEditText, descriptionEditText, pubTextView;
    ListView listView;
    //ListView listview1;
    static int i=0;
    TextToSpeech tts;
    String a;
    Button fetchButton,resultButton,playbutton;
    @Override
    public void onStop()
    {
        super.onStop();
        tts.shutdown();
    }
    HandleXML xmlHandle;
    static ArrayList<String> titleList;
    //static ArrayList<String> descList;
    static  String desc;
    static String list;
    static int lsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        a= getIntent().getExtras().getString("Value");
        final String url =a;
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float)0.7);
            }
        });
        //titleEditText = (TextView) findViewById(R.id.title_edit_text);
        //linkEditText = (TextView) findViewById(R.id.link_edit_text);
        //descriptionEditText = (TextView) findViewById(R.id.description_edit_text);
        //pubTextView = (TextView) findViewById(R.id.image_view);
        fetchButton = (Button) findViewById(R.id.fetch__button);
        resultButton = (Button) findViewById(R.id.result__button);
        playbutton = (Button) findViewById(R.id.play_button);
        listView = (ListView) findViewById(R.id.list_view);
        titleList = new ArrayList<String>();
        xmlHandle = new HandleXML(url);
        xmlHandle.fetchXML();
        tts.speak("TODAY NEWS HEADLINES",TextToSpeech.QUEUE_FLUSH,null);
        while (xmlHandle.parsingComplete) ;
        //titleList.add(xmlHandle.getTitle());
        //linkEditText.setText(xmlHandle.getLink());
        //descriptionEditText.setText(xmlHandle.getDescription());
        //pubTextView.setText(xmlHandle.getPubDate());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(newsfeed.this, android.R.layout.simple_list_item_1, titleList);
        listView.setAdapter(arrayAdapter);

        list = titleList.get(i);
        lsize =titleList.size();

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = titleList.   get(i);
                tts.speak(list,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                i++;
                if (i < lsize) {

                    list = titleList.get(i);
                    //Toast.makeText(MainActivity.this, list, 2000).show();
                    //Toast.makeText(MainActivity.this,String.valueOf(i), 2000).show();
                    tts.speak(list,TextToSpeech.QUEUE_FLUSH,null);
                }

                else if(i >= lsize)
                {
                    tts.speak("Thats all for Today",TextToSpeech.QUEUE_FLUSH,null);
                    i=1;
                }


            }
        });

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*xmlHandle = new HandleXML(url);
                xmlHandle.fetchXML();

                while (xmlHandle.parsingComplete) ;
                //titleList.add(xmlHandle.getTitle());
                //linkEditText.setText(xmlHandle.getLink());
                //descriptionEditText.setText(xmlHandle.getDescription());
                //pubTextView.setText(xmlHandle.getPubDate());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titleList);
                listView.setAdapter(arrayAdapter);*/
                //Toast.makeText(MainActivity.this,String.valueOf(lsize) , Toast.LENGTH_SHORT).show();
                i--;
                if (i > 0 && i < lsize) {
                    list = titleList.get(i);
                    //Toast.makeText(MainActivity.this, list, 2000).show();
                    //Toast.makeText(MainActivity.this,String.valueOf(i), 2000).show();
                    tts.speak(list,TextToSpeech.QUEUE_FLUSH,null);
                }
                else if(i == 0 )
                {
                    tts.speak("Thats all for Today", TextToSpeech.QUEUE_FLUSH, null);
                    i=1;
                }
            }
        });

    }
}
