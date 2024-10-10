package com.example.geo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.content.Intent;
import com.example.geo.CheatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;
    private static final String KEY_INDEX="index";
    private static final String TAG="MainActivity";

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    @SuppressLint("MissingInflatedId")

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPreButton=findViewById(R.id.pre_button);
        mCheatButton =(Button)findViewById(R.id.cheat_button);

        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion(); // 获取当前问题的答案
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue); // 传递答案
                startActivity(i);
            }
        });
        mTrueButton.setOnClickListener(v -> checkAnswer(true));
        mFalseButton.setOnClickListener(v -> checkAnswer(false));
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });
        mPreButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex - 1+mQuestionBank.length) % mQuestionBank.length;
            updateQuestion();
        });
        if (savedInstanceState !=null)
        {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion(); // 显示第一个问题
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion(); // 使用正确方法
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = userPressedTrue == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        if( userPressedTrue == answerIsTrue) {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        }
    }
}

