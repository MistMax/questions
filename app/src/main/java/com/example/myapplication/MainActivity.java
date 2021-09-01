package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    Button showAnswer;
    Toast qResultToast;
    TextView questionTextView;
    int answerCount = 0;
    int questionIndex = 0;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
            };
    String[] questionsAndAnswers = new String[questions.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate() called");
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        showAnswer = findViewById(R.id.answer);
        questionTextView = findViewById(R.id.questionTextView);
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("questionIndex");
            questionsAndAnswers = savedInstanceState.getStringArray("qAndAnswers");
            answerCount = savedInstanceState.getInt("counter");
        }
        questionTextView.setText(questions[questionIndex].getQuestionText());
        View.OnClickListener yesNoOCL = view -> {
            switch (view.getId()) {
                case (R.id.noBtn): {
                    checkAnswer(false);
                    break;
                }
                case (R.id.yesBtn): {
                    checkAnswer(true);
                    break;
                }
            }
            questionTextView.setText(questions[questionIndex].getQuestionText());
            questionIndex++;
            if (questionIndex == questions.length) {
                questionIndex = 0;
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("qAndAnswers", questionsAndAnswers);
                intent.putExtra("counter", answerCount);
                startActivity(intent);
            }
            questionTextView.setText(questions[questionIndex].getQuestionText());
        };
        noBtn.setOnClickListener(yesNoOCL);
        yesBtn.setOnClickListener(yesNoOCL);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("trueAnswer", questions[questionIndex].isAnswerTrue());
                intent.putExtra("counter", answerCount);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("questionIndex", questionIndex);
        outState.putInt("counter", answerCount);
        outState.putStringArray("qAndAnswers", questionsAndAnswers);
        super.onSaveInstanceState(outState);
    }

    private void checkAnswer(Boolean answer) {
        if (qResultToast != null) {
            qResultToast.cancel();
        }
        if (questions[questionIndex].isAnswerTrue() == answer) {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ верный", Toast.LENGTH_LONG);
            answerCount++;
        } else {
            qResultToast = Toast.makeText(MainActivity.this, "Ответ не верный", Toast.LENGTH_LONG);
        }
        questionsAndAnswers[questionIndex] = attachAnswerQuestion(getString(questions[questionIndex].getQuestionText()), answer);
        qResultToast.show();
    }

    private String attachAnswerQuestion(String question, boolean answer) {
        return question + " - " + "ваш ответ: " + (answer ? getString(R.string.yes) : getString(R.string.no));
    }
}