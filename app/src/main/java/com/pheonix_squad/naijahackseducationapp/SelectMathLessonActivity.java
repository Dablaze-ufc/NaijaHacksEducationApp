package com.pheonix_squad.naijahackseducationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectMathLessonActivity extends AppCompatActivity {

    CardView lesson1, lesson2,lesson3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_math_lesson);

        lesson1 = findViewById(R.id.card_topic);
        lesson2 = findViewById(R.id.card_topic_two);
        lesson3 = findViewById(R.id.card_topic_three);

        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLesson1(view);

            }
        });

        lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLesson1(view);

            }
        });
        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLesson1(view);

            }
        });
    }


    public void openLesson1 (View view){
        Intent lessonOne = new Intent(this, MathLessonOneActivity.class);
        startActivity(lessonOne);
    }

    public void openLesson2 (View view){
        Intent lessonTwo = new Intent(this, MathLessonTwoActivity.class);
        startActivity(lessonTwo);
    }

    public void openLesson3 (View view){
        Intent lessonThree = new Intent(this, EnglishLessonThreeActivity.class);
        startActivity(lessonThree);
    }
}
