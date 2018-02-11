package com.example.movie;

import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		// ActionBar bar = getActionBar();
			//for color
		//	bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
		//	bar.setTitle("View");
		
		


new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Start.this, Login.class);
           Start.this.startActivity(mainIntent);
               Start.this.finish();
            }
        }, 2000);


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
