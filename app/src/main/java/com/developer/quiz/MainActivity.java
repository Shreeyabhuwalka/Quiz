package com.developer.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mTrueButton;

    Button mFalseButton;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mScore;
    TextView mQuestionTextView;
    int mIndex;
    int mQuestion;
    // TODO: Declare member variables here:


    ///TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            mScore = savedInstanceState.getInt("scoreKey");
            mIndex = savedInstanceState.getInt("indexKey");
        }
        else
        {
            mIndex =0;
            mScore = 0;
        }
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mScoreTextView = (TextView) findViewById(R.id.score);

        mScoreTextView.setText("Score"+mScore+"/"+mQuestionBank.length);


        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        final View.OnClickListener myListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("msg","True pressed");
                checkAnswer(true);
                updateQuestion();
            }
        };
        mTrueButton.setOnClickListener(myListner);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("msg","False pressed");
                checkAnswer(false);
                updateQuestion();

            }
        });



    }
    private void updateQuestion()
    {
        mIndex = (mIndex+1) % mQuestionBank.length;
        Log.i("Msg", String.valueOf(mIndex));
        if(mIndex==0)
        {
            Log.i("Msg", "Came");

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            //cancelable help to remove alert box by clicking outside the alert box. By seting it to false we r nt allowing to disable alert
            alert.setCancelable(false);
            alert.setMessage("Your scored "+mScore+" points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score"+mScore+"/"+mQuestionBank.length);
    }
    private void checkAnswer(boolean userSelection)
    {
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if(userSelection == correctAnswer)
        {
            mScore = mScore +1;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }

    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreKey",mScore);
        outState.putInt("indexKey",mIndex);
    }

}
