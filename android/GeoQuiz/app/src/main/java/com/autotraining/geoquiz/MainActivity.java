package com.autotraining.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

		private Button mTrueButton;
		private Button mFalseButton;
		private Button mPrevButton;
		private Button mNextButton;
		private TextView mQuestionTextView;

		private Question[] mQuestions = new Question[] {
			new Question(R.string.question_australia, true),
			new Question(R.string.question_oceans, true),
			new Question(R.string.question_mideast, false),
			new Question(R.string.question_africa, false),
			new Question(R.string.question_america, true),
			new Question(R.string.question_asia, true),
		};

		private int mCurrentIndex = 0;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);

				mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
				mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

				mTrueButton = (Button) findViewById(R.id.true_button);
				mTrueButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								checkAnswer(true);
						}
				});

				mFalseButton = (Button) findViewById(R.id.false_button);
				mFalseButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								checkAnswer(false);
						}
				});

				mPrevButton = (Button) findViewById(R.id.prev_button);
				mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevQuestion();
            }
        });

				mNextButton = (Button) findViewById(R.id.next_button);
				mNextButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								showNextQuestion();
						}
				});

				updateQuestion();
		}

		private void updateQuestion() {
				int question = mQuestions[mCurrentIndex].getTextResId();
				mQuestionTextView.setText(question);
		}

		private void checkAnswer(boolean userAnswer) {
		    boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
		    int messageResId = 0;
		    if (userAnswer == answerIsTrue) {
		        messageResId = R.string.correct_toast;
        } else {
		        messageResId = R.string.incorrect_toast;
        }

        Toast toast = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,200);
        toast.show();
    }

    private void showNextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
        updateQuestion();
    }

    private void showPrevQuestion() {
        mCurrentIndex = mCurrentIndex > 0 ? (mCurrentIndex - 1) : (mQuestions.length - 1);
        updateQuestion();
    }
}
