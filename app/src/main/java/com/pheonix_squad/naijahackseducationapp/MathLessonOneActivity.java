package com.pheonix_squad.naijahackseducationapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MathLessonOneActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyDH6Ct6DG1fmnTxnw9l SLDN_3QcRPHmjco";
    public static final String YOUTUBE_VIDEO_CODE = "LvvFW4u8hsQ&pbjreload=10";   // key for STUDY VIDEO!!
    public static final int RECOVERY_DIALOG_REQUEST = 1;
    private String nail;
    private YouTubePlayerView youTubeView;

    TextView questions;
    RadioGroup question1_options, question2_options, question3_options, question4_options, question5_options, question6_options, question7_options, question8_options, question9_options, question10_options;
    RadioButton optionA, optionB, optionC, optionD, optionE;
    RadioButton option2A, option2B, option2C, option2D, option2E;

    Button submit;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_lesson_one);

        youTubeView = (YouTubePlayerView)findViewById(R.id.youtubeview);
        youTubeView.initialize(API_KEY, this);


        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.toast_layout_root));

        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.greatjob);


        question1_options = findViewById(R.id.Question_1_Options);
        question2_options = findViewById(R.id.Question_2_Options);
        question3_options = findViewById(R.id.Question_3_Options);
        question4_options = findViewById(R.id.Question_4_Options);
        question5_options = findViewById(R.id.Question_5_Options);
        question6_options = findViewById(R.id.Question_6_Options);
        question7_options = findViewById(R.id.Question_7_Options);
        question8_options = findViewById(R.id.Question_8_Options);
        question9_options = findViewById(R.id.Question_9_Options);
        question10_options = findViewById(R.id.Question_10_Options);


        optionC = findViewById(R.id.option_C);
        optionB = findViewById(R.id.option_B);
        optionA = findViewById(R.id.option_A);
        optionD = findViewById(R.id.option_D);

        submit = findViewById(R.id.SubmitButton);

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
                    Toast.makeText(getApplicationContext(), "Make sure you answer all questions", Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MathLessonOneActivity.this);
                    builder.setMessage("Are you sure you want to submit?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    TextView text = layout.findViewById(R.id.text);

                                    text.setText("Congratulations! you scored " + score + " out of 10");

                                    Toast toast = new Toast(MathLessonOneActivity.this);
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

    }

    private YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView)findViewById(R.id.youtubeview);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(!wasRestored){
            player.cueVideo(nail);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else {
            String errorMessage = String.format(
                    "Error playing video: %s", errorReason.toString());
            Toast.makeText(this, "errorMessage", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==RECOVERY_DIALOG_REQUEST){
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }
}
