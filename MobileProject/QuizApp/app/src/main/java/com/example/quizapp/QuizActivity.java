package com.example.quizapp;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import com.google.android.material.bottomsheet.BottomSheetDialog;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Random;
        import java.util.Set;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private Button option1Btn, option2Btn, option3Btn, option4Btn;
    private ArrayList<QuizModel> quizModelArrayList;
    private ArrayList<Integer> askedQuestions;
    Random random;
    int currentScore = 0, questionAttempted = 0, currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view1);
        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        quizModelArrayList = new ArrayList<>();
        askedQuestions = new ArrayList<>();
        random = new Random();
        loadQuizData(getIntent().getExtras().getString("com.example.quizapp.MainActivity"));
        showNextQuestion(); // Show the first question
        currentPos = random.nextInt(quizModelArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().equalsIgnoreCase(option1Btn.getText().toString().trim())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().equalsIgnoreCase(option2Btn.getText().toString().trim())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().equalsIgnoreCase(option3Btn.getText().toString().trim())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModelArrayList.get(currentPos).getAnswer().trim().equalsIgnoreCase(option4Btn.getText().toString().trim())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
    }

    private void showNextQuestion() {
        if (questionAttempted == 10) {
            showBottomSheet();
        } else {
            int randomIndex = getRandomQuestionIndex();
            currentPos = randomIndex;
            askedQuestions.add(randomIndex);

            QuizModel currentQuestion = quizModelArrayList.get(randomIndex);
            questionTV.setText(currentQuestion.getQuestion());
            option1Btn.setText(currentQuestion.getOption1());
            option2Btn.setText(currentQuestion.getOption2());
            option3Btn.setText(currentQuestion.getOption3());
            option4Btn.setText(currentQuestion.getOption4());

            questionNumberTV.setText("Question: " + (questionAttempted + 1) + "/10");
        }
    }

    private int getRandomQuestionIndex() {
        int randomIndex = random.nextInt(quizModelArrayList.size());
        while (askedQuestions.contains(randomIndex)) {
            randomIndex = random.nextInt(quizModelArrayList.size());
        }
        return randomIndex;
    }
    private void showBottomSheet() {

        // Save the final score in SharedPreferences for later displaying them in results
        SharedPreferences sharedPreferences = getSharedPreferences("QuizResults", Context.MODE_PRIVATE);
        Set<String> previousResults = sharedPreferences.getStringSet("Results", new HashSet<>());

        // Add the current score to the set of previous results
        previousResults.add(String.valueOf(currentScore));

        // Save the updated set of previous results back to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("Results", previousResults);
        editor.apply();

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,
                (LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        scoreTV.setText("Your Score: \n"+currentScore+"/10");

        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPos = random.nextInt(quizModelArrayList.size());
                questionAttempted = 0;
                currentScore = 0;
                bottomSheetDialog.dismiss();
                setDataToViews(currentPos);
            }
        });

        Button menuButton = bottomSheetView.findViewById(R.id.idBtnMenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionAttempted = 0;
                currentScore = 0;
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startIntent.putExtra("com.example.quizapp.QuizActivity", "hello");
                startActivity(startIntent);
                setDataToViews(currentPos);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void shuffleOptions(ArrayList<String> options) {
        // Shuffle Algorithm
        Random random = new Random();
        for (int i = options.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = options.get(i);
            options.set(i, options.get(j));
            options.set(j, temp);
        }
    }

    private void setDataToViews(int currentPos) {
        questionNumberTV.setText("Question: " + questionAttempted + "/10");
        if (questionAttempted == 10) {
            showBottomSheet();
        } else {
            QuizModel currentQuestion = quizModelArrayList.get(currentPos);

            // Create an ArrayList of options to shuffle
            ArrayList<String> options = new ArrayList<>();
            options.add(currentQuestion.getOption1());
            options.add(currentQuestion.getOption2());
            options.add(currentQuestion.getOption3());
            options.add(currentQuestion.getOption4());

            // Shuffle the options
            shuffleOptions(options);

            questionTV.setText(currentQuestion.getQuestion());
            option1Btn.setText(options.get(0));
            option2Btn.setText(options.get(1));
            option3Btn.setText(options.get(2));
            option4Btn.setText(options.get(3));
        }
    }

    private void loadQuizData(String category) {
        Resources resources = getResources();
        int totalQuestions = 20; // The total number of questions for the specified category

        for (int i = 0; i < totalQuestions; i++) {
            String questionText = resources.getString(resources.getIdentifier(category + "_question_" + i, "string", getPackageName()));
            String option1 = resources.getString(resources.getIdentifier(category + "_option_" + i + "_1", "string", getPackageName()));
            String option2 = resources.getString(resources.getIdentifier(category + "_option_" + i + "_2", "string", getPackageName()));
            String option3 = resources.getString(resources.getIdentifier(category + "_option_" + i + "_3", "string", getPackageName()));
            String option4 = resources.getString(resources.getIdentifier(category + "_option_" + i + "_4", "string", getPackageName()));
            String answer = resources.getString(resources.getIdentifier(category + "_answer_" + i, "string", getPackageName()));

            quizModelArrayList.add(new QuizModel(questionText, option1, option2, option3, option4, answer));
        }
    }
}