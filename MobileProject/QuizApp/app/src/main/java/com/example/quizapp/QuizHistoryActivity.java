package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class QuizHistoryActivity extends AppCompatActivity {

    private ListView listViewQuizResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_history);

        listViewQuizResults = findViewById(R.id.listViewQuizHistory);

        // Retrieve the set of quiz results from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuizResults", Context.MODE_PRIVATE);
        Set<String> resultsSet = sharedPreferences.getStringSet("Results", new HashSet<>());

        // Create an ArrayList to store the quiz scores
        ArrayList<Integer> quizResultsList = new ArrayList<>();

        // Convert the set of results to integers and add them to the ArrayList
        for (String result : resultsSet) {
            quizResultsList.add(Integer.parseInt(result));
        }

        // Create an ArrayAdapter to display scores in the ListView
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizResultsList);
        listViewQuizResults.setAdapter(adapter);
    }
}