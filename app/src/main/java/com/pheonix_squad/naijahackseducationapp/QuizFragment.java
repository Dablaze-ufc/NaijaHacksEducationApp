package com.pheonix_squad.naijahackseducationapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    TextView questions;
    RadioGroup question1_options, question2_options, question3_options, question4_options, question5_options, question6_options, question7_options, question8_options, question9_options, question10_options;
    RadioButton optionA, optionB, optionC, optionD, optionE;
    RadioButton option2A, option2B, option2C, option2D, option2E;

    Button submit;

    int score = 0;


    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);

//        LayoutInflater inflater1 = getLayoutInflater();
       final View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) root.findViewById(R.id.toast_layout_root));

        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.excited2);


        question1_options = root.findViewById(R.id.Question_1_Options);
        question2_options = root.findViewById(R.id.Question_2_Options);
        question3_options = root.findViewById(R.id.Question_3_Options);
        question4_options = root.findViewById(R.id.Question_4_Options);
        question5_options = root.findViewById(R.id.Question_5_Options);
        question6_options = root.findViewById(R.id.Question_6_Options);
        question7_options = root.findViewById(R.id.Question_7_Options);
        question8_options = root.findViewById(R.id.Question_8_Options);
        question9_options = root.findViewById(R.id.Question_9_Options);
        question10_options = root.findViewById(R.id.Question_10_Options);


        optionC = root.findViewById(R.id.option_C);
        optionB = root.findViewById(R.id.option_B);
        optionA = root.findViewById(R.id.option_A);
        optionD = root.findViewById(R.id.option_D);

        submit = root.findViewById(R.id.SubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int answer1 = question1_options.getCheckedRadioButtonId();
                if (answer1 == R.id.option_B) {
                    score++;
                }
                if (question2_options.getCheckedRadioButtonId() == R.id.Option_2A) {
                    score++;
                }
                if (question3_options.getCheckedRadioButtonId() == R.id.Option_3B) {
                    score++;
                }
                if (question4_options.getCheckedRadioButtonId() == R.id.Option_4B) {
                    score++;
                }
                if (question5_options.getCheckedRadioButtonId() == R.id.Option_5C) {
                    score++;
                }

                if (question6_options.getCheckedRadioButtonId() == R.id.Option_6B) {
                    score++;
                }
                if (question7_options.getCheckedRadioButtonId() == R.id.Option_7D) {
                    score++;
                }
                if (question8_options.getCheckedRadioButtonId() == R.id.Option_8C) {
                    score++;
                }
                if (question9_options.getCheckedRadioButtonId() == R.id.Option_9C) {
                    score++;
                }
                if (question10_options.getCheckedRadioButtonId() == R.id.Option_10C) {
                    score++;
                }

                if (answer1 == -1 || question2_options.getCheckedRadioButtonId() == -1 || question3_options.getCheckedRadioButtonId() == -1 ||
                        question4_options.getCheckedRadioButtonId() == -1 || question5_options.getCheckedRadioButtonId() == -1 || question6_options.getCheckedRadioButtonId() == -1 || question7_options.getCheckedRadioButtonId() == -1 || question8_options.getCheckedRadioButtonId() == -1 || question9_options.getCheckedRadioButtonId() == -1 || question10_options.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "Make sure you answer all questions", Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Are you sure you want to submit?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    TextView text = layout.findViewById(R.id.text);

                                    text.setText("Congratulations! you scored " + score + " out of 10");

                                    Toast toast = new Toast(getContext());
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);
                                    toast.setDuration(Toast.LENGTH_LONG);
                                    toast.setView(layout);
                                    toast.show();

//                            score = 0;
//                                Intent openAnswers = new Intent(MainActivity.this, AnswerActivity.class);
//                                startActivity(openAnswers);
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .create().show();
                }
            }
        });

        return root;


    }

//    public void submit(View view) {
//
//
//    }

}
