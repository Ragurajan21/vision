package com.example.anandsurya.vision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class credits extends AppCompatActivity {
    TextView textView1;
    //String team =  "TEAM : N.Ragavendran, R.Anand Surya, K.Keshav,K.Sivaranjani,R.Kaarthikeyan,T.Ragurajan";
    // String mentor = "Mentors : Dr.G.Nagappan, J. Jasmine Gabriel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        textView1 = (TextView)findViewById(R.id.tv123);
        textView1.setText("TEAM: \n"+ "N.Ragavendran \n"+ "R.Anand Surya \n "+ "K.Keshav \n" + "K.Sivaranjani \n" + "R.Kaarthikeyan \n" + "T.Ragurajan \n \n" + "MENTORS: \n"+ "Dr.G.Nagappan \n"+"V Perumal \n"+"J.Jasmine Gabriel \n"+ "Special Thanks to \n"+ "P.B.Vignesh \n"+"\n\n\nVersion :- 1.0");
        // textView2.setText("MENTORS : \n"+"Dr.G.Nagappan \n" + "J.Jasmine Gabriel");

    }
};
