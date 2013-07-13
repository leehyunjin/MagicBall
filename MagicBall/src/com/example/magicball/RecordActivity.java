package com.example.magicball;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class RecordActivity extends Activity {

	MediaRecorder mRecorder = null;
	String Path = "";

	public void onCreate (Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.record); //layoutResID
	
		Handler handle = new Handler();
		
		Path = "/sdcard/recaudio.3gp";
		
 		if(mRecorder == null) {
 			mRecorder = new MediaRecorder();
 		}
 		else {
 			mRecorder.reset();
 		}
 		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
 		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
 		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
 		mRecorder.setOutputFile(Path);
 		
 		try {
			mRecorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Toast.makeText(RecordActivity.this, "lllegalStartException", 1).show();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(RecordActivity.this, "IOException", 1).show();
			e.printStackTrace();
		}
 		mRecorder.start();
 	
 		//�� �� ��
 		handle.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mRecorder.stop();	
	 	 		mRecorder.release();
	 	 		mRecorder = null;

			}
		}, 3000);
//			mRecorder.stop();
// 	 		mRecorder.release();
// 	 		mRecorder = null;
	}	

	public void onDestroy() {
		super.onDestroy();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}
	}
}
