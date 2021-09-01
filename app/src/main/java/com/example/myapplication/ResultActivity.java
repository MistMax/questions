package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView q_AndAnswers;
    TextView resultScores;
    String[] resultArr;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        q_AndAnswers = findViewById(R.id.q_and_answers);
        resultScores = findViewById(R.id.youScores);
        resultArr = getIntent().getStringArrayExtra("qAndAnswers");
        for (int i = 0; i < resultArr.length; i++) {
            q_AndAnswers.append(resultArr[i] + "\n");
        }
        Bundle extras = getIntent().getExtras();
            if (extras != null) {
                result = extras.getInt("counter");
            }
        resultScores.append(result+"");
    }
}
