package com.demo.pr3;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class JoinActivity extends Activity{
	private Handler mHandler = new Handler();

	ImageView imageview;
	TextView textview;
	int alpha = 255;
	int b = 0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.join);

		imageview = (ImageView) this.findViewById(R.id.ImageView01);
		textview = (TextView) this.findViewById(R.id.TextView01);

		imageview.setAlpha(alpha);

		new Thread(new Runnable() {
			public void run() {
			//	initApp();
				
				while (b < 2) {
					try {
						if (b == 0) {
							Thread.sleep(1000);
							b = 1;
						} else {
							Thread.sleep(50);
						}
						updateApp();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} 
		}).start();

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				imageview.setAlpha(alpha);
				imageview.invalidate();

			}
		};

	}

	public void updateApp() {
		alpha -= 5;

		if (alpha <= 0) {
			b = 2;
			Intent in = new Intent(JoinActivity.this, IndexActivity.class);
			startActivity(in);
			this.finish();
		}

		mHandler.sendMessage(mHandler.obtainMessage());

	}
	
/*	public void initApp(){
		 ContactsTable billdb=new ContactsTable(this);
  	     billdb.FirstStart(); 	     
  	     billdb.close(); 
	}  */

}

