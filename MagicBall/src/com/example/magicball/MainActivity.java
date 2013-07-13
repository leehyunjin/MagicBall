//package com.example.magicball;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//
//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
//   
//}
//

package com.example.magicball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class MainActivity extends Activity {

	public void onCreate (Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main); //layoutResID
		
		Handler handle = new Handler();
		
		handle.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(MainActivity.this, RecordActivity2.class);
				startActivity(intent);
				finish();
				}
		}, 2000);
		
	}
}
