package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button yesBtn;
    Button noBtn;
    Button showAnswer;
    TextView questionTextView;
    int counter = 0;
    Question[] answerUser =new Question[5];
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
        showAnswer = findViewById(R.id.answer);
        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(questions[questionIndex].getQuestionText());
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
                intent.putExtra("count", counter);
                startActivity(intent);
            }
        });
    }
    private String attachAnswerQuestion(String question, boolean answer) {
        return question + " - " + "ваш ответ: " + (answer ? getString(R.string.yes) : getString(R.string.no));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("questionIndex", questionIndex);

    }
    public void checkAnswer(boolean btn) {
        if (questions[questionIndex].isAnswerTrue() && btn || (!questions[questionIndex].isAnswerTrue() && !btn)) {
            Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT).show();
            counter++;
        }else
            Toast.makeText(MainActivity.this, "Не верный ответ", Toast.LENGTH_SHORT).show();
        questionIndex = (questionIndex+1)%questions.length;
        questionTextView.setText(questions[questionIndex].getQuestionText());
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