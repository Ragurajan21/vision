package com.example.anandsurya.vision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;


public class login extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE1 = 1111, REQUEST_CODE2 = 2222;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    public static TextToSpeech tts;
    String sentence = "Tap on the top left corner for email tap on the top right corner for password tap the bottom half to login";


    public FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
      if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), HomeMenu.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);
        progressDialog = new ProgressDialog(this);
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        editTextEmail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startVoiceRecognitionActivity();
            }
        });
        editTextPassword.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startVoiceRecognitionActivitypass();
            }
        });
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setPitch(1);
                tts.setSpeechRate((float)0.8);
            }
        });
        tts.speak(sentence ,TextToSpeech.QUEUE_FLUSH, null);
    }
    private void startVoiceRecognitionActivitypass()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-IN");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak");
        startActivityForResult(intent, REQUEST_CODE2);
    }
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en_IN");
        final Intent intent1 = intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak");
        startActivityForResult(intent, REQUEST_CODE1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //pull all of the matches
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String topResult = matches.get(0);
            if (requestCode==REQUEST_CODE1) {
                EditText mail = (EditText) findViewById(R.id.editTextEmail);
                mail.setText(topResult.toString());

            }
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==REQUEST_CODE2) {
                EditText pass = (EditText) findViewById(R.id.editTextPassword);
                pass.setText(topResult.toString());
            }
        }

    }


    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging In Please Wait...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeMenu.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(login.this, login.class));
        }
    }
}