package com.autotraining.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private static final String KEY_INDEX = "index";

	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mPrevButton;
	private ImageButton mNextButton;
	private TextView mQuestionTextView;

	private Question[] mQuestions = new Question[]{
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
		Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_main);

		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
		}

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

		mPrevButton = (ImageButton) findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showPrevQuestion();
			}
		});

		mNextButton = (ImageButton) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showNextQuestion();
			}
		});

		updateQuestion();
	}

	@Override
	public void onStart() {
		super.onStart(); //“Calling the superclass implementation should be the first line of each callback method override implementation”
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	private void enableAnswerButtons(boolean enableValue) {
		mTrueButton.setEnabled(enableValue);
		mFalseButton.setEnabled(enableValue);
	}

	private void updateQuestion() {
		int question = mQuestions[mCurrentIndex].getTextResId();
		mQuestionTextView.setText(question);
		enableAnswerButtons(true);
	}

	private void checkAnswer(boolean userAnswer) {
		boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
		enableAnswerButtons(false);
		int messageResId = 0;
		if (userAnswer == answerIsTrue) {
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}

		Toast toast = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 200);
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
