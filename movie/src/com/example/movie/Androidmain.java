package com.example.movie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Androidmain extends Activity {
	Button manageattendence,updatenewmovies;
	String admin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androidmain);
		manageattendence=(Button)findViewById(R.id.manageattendence);
		updatenewmovies=(Button)findViewById(R.id.updatenewmovies);
		
		
		Intent intent=getIntent();
		 admin=intent.getStringExtra("admin");
		
		
		manageattendence.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(Androidmain.this,Adminpage.class);
				startActivity(intent);
				
			}
		});
		
		
		updatenewmovies.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				

				Intent intent=new Intent(Androidmain.this,Image.class);
				intent.putExtra("admin", admin);
				startActivity(intent);
				
			}
		});
		
	}

	

}
