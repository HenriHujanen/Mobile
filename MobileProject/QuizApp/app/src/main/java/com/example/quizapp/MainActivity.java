package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quizButton = (Button) findViewById(R.id.idQuiz1);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizActivity.class);
                startIntent.putExtra("com.example.quizapp.MainActivity", "math");
                startActivity(startIntent);
            }
        });

        Button quizButton2 = (Button) findViewById(R.id.idQuiz2);
        quizButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizActivity.class);
                startIntent.putExtra("com.example.quizapp.MainActivity", "tv");
                startActivity(startIntent);
            }
        });

        Button quizButton3 = (Button) findViewById(R.id.idQuiz3);
        quizButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizActivity.class);
                startIntent.putExtra("com.example.quizapp.MainActivity", "country");
                startActivity(startIntent);
            }
        });

        Button quizButton4 = (Button) findViewById(R.id.idQuiz4);
        quizButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizActivity.class);
                startIntent.putExtra("com.example.quizapp.MainActivity", "music");
                startActivity(startIntent);
            }
        });

        Button results = (Button) findViewById(R.id.resultsButton);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizHistoryActivity.class);
                startIntent.putExtra("com.example.quizapp.MainActivity", "results");
                startActivity(startIntent);
            }
        });
    }
}