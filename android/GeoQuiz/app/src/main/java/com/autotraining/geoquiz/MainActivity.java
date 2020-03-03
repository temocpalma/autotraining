package com.autotraining.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private static final String KEY_INDEX = "index";
	private static final String ANSWERS_STRING = "answers";
	private static final int REQUEST_CODE_CHEAT = 0;
	private static final String IS_CHEATER = "isCheater";
	private static final String CHEATS_COUNT = "cheats_count";
	private static final int MAX_CHEATS = 3;

	private Button mTrueButton;
	private Button mFalseButton;
	private Button mCheatButton;
	private ImageButton mPrevButton;
	private ImageButton mNextButton;
	private TextView mQuestionTextView;
	private TextView mCheatsCountTextView;

	private Question[] mQuestions = new Question[]{
		new Question(R.string.question_australia, true),
		new Question(R.string.question_oceans, true),
		new Question(R.string.question_mideast, false),
		new Question(R.string.question_africa, false),
		new Question(R.string.question_america, true),
		new Question(R.string.question_asia, true),
	};

	private int mCurrentIndex = 0;
	private List<Integer> mAnswers = new ArrayList<Integer>();
	private BigDecimal score = new BigDecimal(0);
	private boolean mIsCheater;
	private int mCheatsCount = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_main);
		initAnswersList();

		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
			mAnswers = loadAnswersSaved(savedInstanceState.getString(ANSWERS_STRING));
			mIsCheater = savedInstanceState.getBoolean(IS_CHEATER);
			mCheatsCount = savedInstanceState.getInt(CHEATS_COUNT);
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

		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
				Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
				startActivityForResult(intent, REQUEST_CODE_CHEAT);
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
		checkCheatsCount();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult.....");
		if (resultCode != Activity.RESULT_OK) {
			return;
		}

		if (requestCode == REQUEST_CODE_CHEAT) {
			if (data == null) {
				return;
			}
			mIsCheater = CheatActivity.wasAnswerShown(data);
			if (mIsCheater) {
				mCheatsCount--;
				checkCheatsCount();
			}
		}
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
		savedInstanceState.putString(ANSWERS_STRING, mAnswers.toString().replace("[", "").replace("]", ""));
		savedInstanceState.putBoolean(IS_CHEATER, mIsCheater);
		savedInstanceState.putInt(CHEATS_COUNT, mCheatsCount);
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

	private void initAnswersList() {
		for (int i = 0; i < mQuestions.length; i++) {
			mAnswers.add(-1); //no answer
		}
	}

	private List<Integer> loadAnswersSaved(String answers) {
		List<Integer> intAnswersList = new ArrayList<Integer>();
		List<String> stringAnswersList = new ArrayList<String>(Arrays.asList(answers.split(",")));
		for (int i = 0; i < stringAnswersList.size(); i++) {
			intAnswersList.add(Integer.parseInt(stringAnswersList.get(i).trim()));
		}
		return intAnswersList;
	}

	private void enableAnswerButtons(boolean enableValue) {
		mTrueButton.setEnabled(enableValue);
		mFalseButton.setEnabled(enableValue);
		if (mCheatsCount > 0) {
			mCheatButton.setEnabled(enableValue);
		} else {
			mCheatButton.setEnabled(false);
		}
	}

	private void saveAnswer(int answer) {
		mAnswers.set(mCurrentIndex, answer);
	}

	private void updateQuestion() {
		int question = mQuestions[mCurrentIndex].getTextResId();
		mQuestionTextView.setText(question);
		if (mAnswers.get(mCurrentIndex) != -1) {
			enableAnswerButtons(false);
		} else {
			enableAnswerButtons(true);
		}
	}

	private void checkAnswer(boolean userAnswer) {
		boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
		int messageResId = 0;

		if (mIsCheater) {
			messageResId = R.string.judgement_toast;
		} else {
			enableAnswerButtons(false);
			if (userAnswer == answerIsTrue) {
				messageResId = R.string.correct_toast;
				saveAnswer(1);
			} else {
				messageResId = R.string.incorrect_toast;
				saveAnswer(0);
			}
		}

		Toast toast = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 200);
		toast.show();
	}

	private void showNextQuestion() {
		mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
		mIsCheater = false;
		updateQuestion();
		checkAllQuestionsAnswered();
	}

	private void showPrevQuestion() {
		mCurrentIndex = mCurrentIndex > 0 ? (mCurrentIndex - 1) : (mQuestions.length - 1);
		updateQuestion();
		checkAllQuestionsAnswered();
	}

	private void checkAllQuestionsAnswered() {
		if (!mAnswers.contains(-1)) {
			computeScore();
			displayScore();
		}
	}

	private void computeScore() {
		double totalOkAnswers = 0.0;
		for (int i = 0; i < mAnswers.size(); i++) {
			totalOkAnswers += mAnswers.get(i).intValue();
		}
		score = new BigDecimal((totalOkAnswers / mAnswers.size()) * 100);
	}

	private void displayScore() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String scoreMessage = "Score: " + df.format(score) + "%";
		Toast toast = Toast.makeText(MainActivity.this, scoreMessage, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 200);
		toast.show();
	}

	private void checkCheatsCount() {
		String mAvailableCheatMessage = getString(R.string.available_cheats, mCheatsCount, MAX_CHEATS);

		mCheatsCountTextView = (TextView) findViewById(R.id.cheats_count_text_view);
		mCheatsCountTextView.setText(mAvailableCheatMessage);
		if (mCheatsCount == 0) {
			mCheatButton.setEnabled(false);
		}
	}

}
