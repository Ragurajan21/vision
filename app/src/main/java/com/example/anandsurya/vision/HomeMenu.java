package com.example.anandsurya.vision;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.speech.RecognizerIntent;
        import android.speech.tts.TextToSpeech;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

        import java.util.ArrayList;
        import java.util.Locale;

public class HomeMenu extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    Button speak,pause,play;
    final int REQUEST_CODE = 1234;
    int i=0,j=0,k=0,l=0,m=0;
    static int a=0;
    public static TextToSpeech tts;
    Button talk;

    public MediaPlayer mp;
    String sentence = ""+" Hello.  1. Read Text Books.   2. Scan and Read.  3. News Feed.  4. Evaluation. 5.Settings.    Press the lower half of the screen, and Say the option after the beep sound";
    String sentence1= "Welcome to NEWS feed. Press the  bottom right corner of the screen to play the next news. Press the bottom left corner of the screen to play the previous news. ";

    ArrayList<String> options = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);


        /*Boolean isfirstrun = getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                .getBoolean("isfirstrun",true);

        if(isfirstrun){
            tts.speak("USER MANUAL",TextToSpeech.QUEUE_ADD,null);
            Intent intent = new Intent(HomeMenu.this, texttospeech.class);
            intent.putExtra("book","usermanual.txt");
            startActivity(intent);


            getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit()
                    .putBoolean("isfirstrun",true).commit();

        }
        else {
            isfirstrun = getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                    .getBoolean("isfirstrun",false);

        }*/














        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, login.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
      //  textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
       // buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
      //  textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button
      //  buttonLogout.setOnClickListener(this);
        speak=(Button)findViewById(R.id.speak);
        talk = (Button) findViewById(R.id.talk);

        //pause = (Button) findViewById(R.id.pause);
        //play =(Button) findViewById(R.id.play);
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int value){
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float)0.8);
            }
        });
        speakOut();
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(HomeMenu.this, R.raw.tone );
                mp.start();
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
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, login.class));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches_text = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.e("MainActivity", matches_text.toString());
            if (matches_text.contains("one")) {
                if((j == 1 | k == 1 | l == 1)&& i==0)
                {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    j=0;
                    k=0;
                    l=0;
                }
                else if (i == 0) {

                    Toast.makeText(HomeMenu.this, "1 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 1 to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    }, 3000);

                    i++;
                    j=0;
                    k=0;
                    l=0;

                } else if(i == 1){
                    i=0;
                    tts.speak("YOUR BOOKS ARE READY. ", TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent = new Intent(this,texttospeech3.class);
                    startActivity(intent);
                }
            }
            else if(matches_text.contains("two")||matches_text.contains("to")) {
                if((i == 1 | k == 1 | l == 1)&& j==0)
                {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    i=0;
                    k=0;
                    l=0;
                }
                else if (j == 0) {
                    Toast.makeText(HomeMenu.this, "2 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 2 to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    i=0;
                    k=0;
                    l=0;

                    j++;

                } else if(j == 1){
                    j=0;
                    tts.speak("KEEP THE PHONE FACING THE DOCUMENT OR BOOK. AND LONG PRESS THE SCREEN TO READ IT OUT", TextToSpeech.QUEUE_FLUSH, null);
                    Intent i = new Intent(this,ocr.class);
                    startActivity(i);
                }
            }
            else if(matches_text.contains("3")){
                if((i == 1 | j == 1 | l == 1)&& k==0)
                {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    j=0;
                    i=0;
                    l=0;
                }
                else if (k == 0) {
                    Toast.makeText(this, "3 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 3 to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);
                    j=0;
                    i=0;
                    l=0;
                    k++;

                } else if(k == 1){
                    k=0;
                   Intent i=new Intent(this,newsfeed1.class);
                    //  tts.speak("NEWS has been activated...", TextToSpeech.QUEUE_FLUSH, null);
                   // if(a==0){
                     //   tts.speak(sentence1,TextToSpeech.QUEUE_ADD,null);
                       // a++;}
                   tts.speak("WELCOME TO NEWS HEADLINES. PRESS THE TOP HALF OF THE SCREEN TO GET THE CATEGORIES",TextToSpeech.QUEUE_ADD,null);
                    startActivity(i);

                }
            }
            else if(matches_text.contains("four")){
                if((i == 1 |j == 1 | k == 1)&& l==0)
                {
                    tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    i=0;
                    j=0;
                    k=0;
                }
                else if (l == 0) {
                    Toast.makeText(this, "4 has been said", Toast.LENGTH_SHORT).show();
                    tts.speak("Say 4 to confirm", TextToSpeech.QUEUE_FLUSH, null);
                    talk.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            talk.performClick();
                        }
                    },1500);

                    i=0;
                    j=0;
                    k=0;
                    l++;

                } else if(l == 1){
                    l=0;
                    tts.speak("Welcome to Evaluation. tap once for question. long press to say the answer ", TextToSpeech.QUEUE_FLUSH, null);
                    Intent i = new Intent(this, evaluation.class);
                    startActivity(i);
                }
            }

        else if(matches_text.contains("five")){
            if((i == 1 |j == 1 | k == 1| l == 1)&& m==0)
            {
                tts.speak("Sorry Try again", TextToSpeech.QUEUE_FLUSH, null);
                talk.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        talk.performClick();
                    }
                },1500);

                i=0;
                j=0;
                k=0;
            }
            else if (m == 0) {
                Toast.makeText(this, "5 has been said", Toast.LENGTH_SHORT).show();
                tts.speak("Say 5 to confirm", TextToSpeech.QUEUE_FLUSH, null);
                talk.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        talk.performClick();
                    }
                },1500);

                i=0;
                j=0;
                k=0;
                l=0;
                m++;

            } else if(m == 1){
                m=0;
                tts.speak("WELCOME TO SETTINGS. ", TextToSpeech.QUEUE_FLUSH, null);
                 Intent i = new Intent(this, support.class);
                 startActivity(i);
            }
        }
    }
        else if(resultCode == RESULT_CANCELED)
        {
            i=0;
            j=0;
            k=0;
            l=0;

            tts.speak("BYE", TextToSpeech.QUEUE_FLUSH, null);
            /*talk.postDelayed(new Runnable() {
                @Override
                public void run() {
                    talk.performClick();
                }
            },1500);*/
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void speakOut(){
        tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);
    }
}

