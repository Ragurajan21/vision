package com.example.anandsurya.vision;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class support extends AppCompatActivity {
    Button volume, credits, user_manual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        volume = (Button) findViewById(R.id.vol);
        credits = (Button) findViewById(R.id.credits);
        user_manual = (Button) findViewById(R.id.manual);

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(support.this, volume.class);
                startActivity(intent);
            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(support.this, credits.class);
                startActivity(intent);
            }
        });
        user_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(support.this, texttospeech.class);
                intent.putExtra("book","usermanual.txt");
                startActivity(intent);
            }

        });
    }
}
