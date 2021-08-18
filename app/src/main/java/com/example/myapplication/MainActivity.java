package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button yesBtn;
    Button noBtn;
    TextView questionTextView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
            };
    int questionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate() called");
        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("questionIndex", 0);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(questions[questionIndex].getQuestionText());
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this,"Правильно", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Не верный ответ", Toast.LENGTH_SHORT).show();
                questionIndex++;
                questionTextView.setText(questions[questionIndex].getQuestionText());
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this,"Правильно", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Не верный ответ", Toast.LENGTH_SHORT).show();
                questionIndex++;
                questionTextView.setText(questions[questionIndex].getQuestionText());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("questionIndex", questionIndex);

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart() - CALLED");
    }
    @Override
    public  void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume() - CALLED");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause() - CALLED");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop() - CALLED");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy() - CALLED");
    }
}