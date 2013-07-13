package com.example.magicball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class RecordActivity2 extends Activity {

	private ProgressBar mProgress; // ���α׷�����
	private SpeechRecognizer mRecognizer; // �����ν� ��ü

	private final int READY = 0, END = 1, FINISH = 2; // �ڵ鷯 �޽���. �����ν� �غ�, ��, ��
														// ����
	private ImageView myImage = null;
	private Animation an = null;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case READY:
//				mProgress.setVisibility(View.INVISIBLE); // �غ�Ǿ����� ���α׷����� ����
				findViewById(R.id.stt_ui).setVisibility(View.VISIBLE); // ����ũ�̹�������.
				break;
			case END:
				mProgress.setVisibility(View.VISIBLE); // ���� �������� ���α׷����� ���(�����ν���)
//				myImage.setVisibility(View.VISIBLE);
//				findViewById(R.id.ball).setVisibility(View.INVISIBLE);
				findViewById(R.id.stt_ui).setVisibility(View.INVISIBLE); // ����ũ�̹�������
				sendEmptyMessageDelayed(FINISH, 5000); // �ν� �ð� 5�ʷ� ����. 5�� ������Ű�Ⱦ�.
				an = AnimationUtils.loadAnimation(RecordActivity2.this, R.anim.rotate);

				break;
//			case FINISH:
//				finish(); // �� ����
//
//				break;
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.record);

		mProgress = (ProgressBar) findViewById(R.id.progress); // ���α׷�����

		
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // �����ν�
																			// intent����
		i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName()); // ������
																				// ����
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR"); // �����ν� ��� ����

		mRecognizer = SpeechRecognizer.createSpeechRecognizer(this); // �����ν� ��ü
		mRecognizer.setRecognitionListener(listener); // �����ν� ������ ���
		mRecognizer.startListening(i); // �����ν� ����

		 myImage = (ImageView) findViewById(R.id.ball);
		 
//		 myImage.setImageResource(R.drawable.ball);
//		 myImage.startAnimation(an);
	}

	// �����ν� ������
	private RecognitionListener listener = new RecognitionListener() {
		// �Է� �Ҹ� ���� ��
		@Override
		public void onRmsChanged(float rmsdB) {
			int step = (int) (rmsdB / 7); // �Ҹ� ũ�⿡ ���� step�� ����.
			// setVolumeImg(step); //�� 4�ܰ� �̹��� ����. ����. 1�ܰ�, 2�ܰ�, 3�ܰ�
		}

		// ���� �ν� ��� ����
		@Override
		public void onResults(Bundle results) {
			mHandler.removeMessages(END); // �ڵ鷯�� ���� �޽��� ����

			Intent i = new Intent(); // ��� ��ȯ�� intent
			i.putExtras(results); // ��� ���
			setResult(RESULT_OK, i); // ��� ����

//			finish(); // �� ����

		}

		// ���� �ν� �غ� �Ǿ�����
		@Override
		public void onReadyForSpeech(Bundle params) {
			mHandler.sendEmptyMessage(READY); // �ڵ鷯�� �޽��� ����
		}

		// ���� �Է��� ��������
		@Override
		public void onEndOfSpeech() {
			mHandler.sendEmptyMessage(END); // �ڵ鷯�� �޽��� ����
		}

		// ������ �߻��ϸ�
		@Override
		public void onError(int error) {
			setResult(error); // �� activity�� �����ڵ� ����
		}

		@Override
		public void onBeginningOfSpeech() {
		} // �Է��� ���۵Ǹ�

		@Override
		public void onPartialResults(Bundle partialResults) {
		} // �ν� ����� �Ϻΰ� ��ȿ�� ��

		@Override
		public void onEvent(int eventType, Bundle params) {
		} // �̷��� �̺�Ʈ�� �߰��ϱ� ���� �̸� ����Ǿ��� �Լ�

		@Override
		public void onBufferReceived(byte[] buffer) {
		} // �� ���� �Ҹ��� ���� ��
	};

//	public void finish() {
//		if (mRecognizer != null)
//			mRecognizer.stopListening(); // �����ν� ����
//		mHandler.removeMessages(READY); // �޽��� ����
//		mHandler.removeMessages(END); // �޽��� ����
//		mHandler.removeMessages(FINISH); // �޽��� ����
//		super.finish();
//	}
}