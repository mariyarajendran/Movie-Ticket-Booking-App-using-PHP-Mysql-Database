package com.example.movie;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SSS extends Activity {
	TextView bookmovid,bookmovie;
	Spinner showspin;
	ArrayAdapter<String> adapter;
	ArrayList<String> list;

	

	Button next;
	EditText name;
	

	String cusname,id,show,movie;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sss);
		
		
		
		
		bookmovid=(TextView)findViewById(R.id.bookmovid);
		bookmovie=(TextView)findViewById(R.id.bookmovie);
		
		
		
		showspin=(Spinner)findViewById(R.id.showspin);
		
		next=(Button)findViewById(R.id.next);
		
		name=(EditText)findViewById(R.id.name);
		
		
	
		list=new ArrayList<String>();
		
		list.add("Morning Show");
		list.add("Afternoon Show");
		list.add("Evening Show");
		list.add("Night Show");
		
				adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spintext , list);
				showspin.setAdapter(adapter);
		
		
		Intent intent=getIntent();
		
		String movi=intent.getStringExtra("movie");
		String movid=intent.getStringExtra("id");
		
		
		bookmovie.setText(movi);
	bookmovid.setText(movid);
		
		
		
	
		
	next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				cusname=name.getText().toString();
				id=bookmovid.getText().toString();
				movie=bookmovie.getText().toString();
				show=showspin.getSelectedItem().toString();
				
				
				Intent intent=new Intent(getApplicationContext(),SeatBooking.class);
				intent.putExtra("cusname",cusname);
				intent.putExtra("id",id );
				intent.putExtra("movie",movie );
				intent.putExtra("show",show );
				
				startActivity(intent);
				
			
				
			}
		});
	


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ss, menu);
		return true;
	}

}
