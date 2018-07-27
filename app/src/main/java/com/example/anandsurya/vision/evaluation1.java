package com.example.anandsurya.vision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class evaluation1 {
    public String mques []= {"what were early Congress leaders  referred to?" ,
            "what was the real aim of the partition of bengal in 1905?",
            "who said Freedom is our birthright and we must have it?",
            "Which movement was started by Shoukat Ali and Mohammad Ali?",
            "Which Party was started by C. R. Das, Motilal Nehru ?",
            "WHich campaign was launched in OCtober 1940?",
            "Who realized the importance of boycott as a weapon that could be used to paralyze the whole British administrative ?",
            "The non-violent insistence for truth and justice is called",
            "Governments imposition of taxes on salt resulted in",
    };
    public  String mopt[][]={
            {"1.ËXTREMIST","2.REVOLUTIONARIES","3.MODERATES"},
            {"1.DIVIDE AND RULE","2.UNITE INDIANS","3.DIVIDE MUSLIMS"},
            {"1.BAL GANGADHAR TILAK","2.MANGAL PANDEY","3.MAHATMA GANDHI"},
            {"1.QUIT INDIA","2.KHILAFATH MOVEMENT","3.SATHYAGRAHA"},
            {"1.CONGRESS PARTY","2.BJP PARTY","3.SWARAJ PARTY"},
            {"1.CIVIL DIS OBIDIENCE","2.SATHYAGRAHA","3.PURNA SWARAJ"},
            {"1.LALA LAJPAT RAI","2.BHAGAT SINGH","3.BAL GANGADHAR TILAK"},
            {"1.DIS OBIDIENCE","2.SATHYAGRAHA","3.QUIT INDIA"},
            {"1.PURNA SWARAJ","2.NON COOPERATION MOVEMENT","3.DANDI MARCH"}
    };
    public String mcorrect[]={
            "3.MODERATES","1.DIVIDE AND RULE","1.BAL GANGADHAR TILAK","2.KHILAFATH MOVEMENT","3.SWARAJ PARTY","1.CIVIL DIS OBIDIENCE",
            "3.BAL GANGADHAR TILAK","2.SATHYAGRAHA","3.DANDI MARCH"
    };




    public String getquestions(int a)
    {String question=mques[a];
        return question;}
    public String getchoice1(int a)
    {String opt1=mopt[a][0];
        return  opt1;}
    public String getchoice2(int a)
    {String opt2=mopt[a][1];
        return  opt2;}
    public String getchoice3(int a)
    {String opt3=mopt[a][2];
        return  opt3;}
    public String getcorrectans(int a)
    {String answer=mcorrect[a];
        return  answer;}
}
