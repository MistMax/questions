package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView qAndAnswers;
    String[] resultArr;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        qAndAnswers = findViewById(R.id.q_and_answers);
        resultArr = getIntent().getStringArrayExtra("qAndAnswer");
        for (int i = 0; i < resultArr.length; i++) {
            qAndAnswers.append(resultArr[i] + "\n");
        }
    }
}
