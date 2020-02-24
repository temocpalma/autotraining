package com.autotraining.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

	private static final String EXTRA_ANSWER_IS_TRUE = "com.autotraining.geoquiz.answer_is_true";
	private static final String EXTRA_ANSWER_SHOWN = "com.autotraining.geoquiz.answer_shown";
	private static final String TAG = "CheatActivity";


	private boolean mAnswerIsTrue;
	private boolean mAnswerShown = false;

	private TextView mAnswerTextView;
	private Button mShowAnswerButton;
	private TextView mBuildVersion;

	public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
		Intent intent = new Intent(packageContext, CheatActivity.class);
		intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return intent;
	}

	public static boolean wasAnswerShown(Intent result) {
		return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mBuildVersion = (TextView) findViewById(R.id.version_text_view);

		mBuildVersion.setText("Api Level " + new Integer(Build.VERSION.SDK_INT).toString());

		mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showAnswer();
				setAnswerShownResult(true);
				mShowAnswerButton.setEnabled(false);
				mAnswerShown = true;

				// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

				int cx = mShowAnswerButton.getWidth() / 2;
				int cy = mShowAnswerButton.getHeight() / 2;
				float radius = mShowAnswerButton.getWidth();
				Animator anim = ViewAnimationUtils
					.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
				anim.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						mShowAnswerButton.setVisibility(View.INVISIBLE);
					}
				});
				anim.start();
				// } else {
				//            mShowAnswerButton.setVisibility(View.INVISIBLE);
				//        }
			}
		});

		if (savedInstanceState != null) {
			mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
			if (mAnswerShown) {
				showAnswer();
				setAnswerShownResult(true);
				mShowAnswerButton.setEnabled(false);
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
		savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerShown);
		setAnswerShownResult(mAnswerShown);
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

	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

	private void showAnswer() {
		if(mAnswerIsTrue) {
			mAnswerTextView.setText(R.string.true_button);
		} else {
			mAnswerTextView.setText(R.string.false_button);
		}

	}
}
