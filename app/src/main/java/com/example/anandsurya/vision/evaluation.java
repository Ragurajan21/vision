package com.example.anandsurya.vision;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;

public class evaluation extends AppCompatActivity {
    private  evaluation1 mqueslib = new evaluation1();
    /*  private String mques[]= {"what were early Congress leaders  referred to?" ,
           "what was the real aim of the partition of bengal in 1905?",
           "who said Freedom is our birthright and we must have it?",
           "Which movement was started by Shoukat Ali and Mohammad Ali?",
           "Which Party was started by C. R. Das, Motilal Nehru ?",
           "WHich campaign was launched in OCtober 1940?",
           "Who realized the importance of boycott as a weapon that could be used to paralyze the whole British administrative ?",
           "The non-violent insistence for truth and justice is called",
           "Governments imposition of taxes on salt resulted in",
   };*/;
    private TextView mscoreview;
    private TextView mquesview;
    private TextView mchoice1,mchoice2,mchoice3;
    // private Button mchoice1,mchoice2,mchoice3,speech;
    private String mans;
    private int mscore=0,mquesnumber=0;
    String s1="correct answer";
    String s2=" try again";
    String s4= mqueslib.mques.toString();
    LinearLayout linearLayout;
    String s3= "the options are";
    int i=0,w=0;
    final int REQUEST_CODE = 1234;

    public  static TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        mscoreview = (TextView) findViewById(R.id.score);
        mquesview = (TextView) findViewById(R.id.question);

        mchoice1 = (TextView) findViewById(R.id.opt1);
        mchoice2 = (TextView) findViewById(R.id.opt2);
        mchoice3 = (TextView) findViewById(R.id.opt3);
        linearLayout= (LinearLayout) findViewById(R.id.layout);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int value) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float) 0.5);




            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tts.speak(mqueslib.mques[i],TextToSpeech.QUEUE_FLUSH, null);

                tts.speak(s3,TextToSpeech.QUEUE_ADD,null);
                tts.speak(mqueslib.mopt[i][0],TextToSpeech.QUEUE_ADD, null);
                tts.speak(mqueslib.mopt[i][1],TextToSpeech.QUEUE_ADD, null);
                tts.speak(mqueslib.mopt[i][2],TextToSpeech.QUEUE_ADD, null);


                //   tts.speak(mques[0],TextToSpeech.QUEUE_FLUSH,null);



                //  tts.speak(c1,TextToSpeech.QUEUE_FLUSH,null);


               /* tts.speak((String)s4,TextToSpeech.QUEUE_FLUSH,null);
                tts.speak(s3,TextToSpeech.QUEUE_FLUSH,null);
                tts.speak((String)c1,TextToSpeech.QUEUE_FLUSH,null);
                tts.speak((String)c2,TextToSpeech.QUEUE_FLUSH,null);
                tts.speak((String)c3,TextToSpeech.QUEUE_FLUSH,null);*/
            }
        });
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                startActivityForResult(intent, REQUEST_CODE);
                return false;
            }
        });
        updateques();
        /*mchoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice1.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(QuizActivity.this, "veryyy gooood", Toast.LENGTH_SHORT).show();
                    tts.speak(s1,TextToSpeech.QUEUE_FLUSH, null);
                    i++;


                } else {
                    Toast.makeText(QuizActivity.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });*/

       /* mchoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice2.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(QuizActivity.this, "veryyy gooood", Toast.LENGTH_SHORT).show();
                    tts.speak(s1,TextToSpeech.QUEUE_FLUSH, null);
                    i++;


                } else {
                    Toast.makeText(QuizActivity.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);


                }
            }
        }); */


       /* mchoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice3.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(QuizActivity.this, "veryyy goooood", Toast.LENGTH_SHORT).show();
                    tts.speak(s1,TextToSpeech.QUEUE_FLUSH, null);
                    i++;



                } else {
                    Toast.makeText(QuizActivity.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });*/


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> matches_text = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.e("MainActivity", matches_text.toString());
            if(matches_text.contains("one")||matches_text.contains(("won"))){
                //tts.speak("Did you say one?", TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(evaluation.this, "1 has been said",Toast.LENGTH_SHORT).show();
                if (mchoice1.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(evaluation.this, "veryyy gooood", Toast.LENGTH_SHORT).show();


                    i++;
                    if(w==0)
                        mscore=mscore + 10;
                    if (w==1)
                        mscore=mscore + 5;


                } else {
                    Toast.makeText(evaluation.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);
                    w=w+1;

                }

            }else if(matches_text.contains("two")||matches_text.contains("to")||matches_text.contains("too")){
                Toast.makeText(evaluation.this, "2 has been said",Toast.LENGTH_SHORT).show();
                if (mchoice2.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(evaluation.this, "veryyy gooood", Toast.LENGTH_SHORT).show();
                    tts.speak(s1,TextToSpeech.QUEUE_FLUSH, null);
                    i++;
                    if(w==0)
                        mscore=mscore + 10;
                    if (w==1)
                        mscore=mscore + 5;


                } else {
                    Toast.makeText(evaluation.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);
                    w=w+1;


                }

            }
            else if(matches_text.contains("three")||matches_text.contains("tree")){
                Toast.makeText(evaluation.this, "3 has been said",Toast.LENGTH_SHORT).show();
                if (mchoice3.getText() == mans) {
                    mscore = mscore + 1;
                    updatescore(mscore);
                    updateques();
                    Toast.makeText(evaluation.this, "veryyy goooood", Toast.LENGTH_SHORT).show();
                    tts.speak(s1,TextToSpeech.QUEUE_FLUSH, null);
                    i++;
                    if(w==0)
                        mscore=mscore + 10;
                    if (w==1)
                        mscore=mscore + 5;



                } else {
                    Toast.makeText(evaluation.this, "oops wrong answer", Toast.LENGTH_SHORT).show();
                    tts.speak(s2,TextToSpeech.QUEUE_FLUSH, null);
                    w=w+1;

                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);


    }





    private void updatescore(int pt)
    {mscoreview.setText(""+ mscore);
    }
    private void updateques()
    {
        mquesview.setText(mqueslib.getquestions(mquesnumber));
        mchoice1.setText(mqueslib.getchoice1(mquesnumber));
        mchoice2.setText(mqueslib.getchoice2(mquesnumber));
        mchoice3.setText(mqueslib.getchoice3(mquesnumber));
        mans= mqueslib.getcorrectans(mquesnumber);
        mquesnumber++;
    }
  /*  public String getQues()
    {String ques= mquesview.getText().toString();
        TextView mquestv = (TextView) findViewById()
    return ques;}*/
};




