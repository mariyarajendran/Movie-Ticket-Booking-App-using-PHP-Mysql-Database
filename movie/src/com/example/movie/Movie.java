package com.example.movie;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class Movie extends Activity {
TextView txt,txt2,txt3,txt4,txt5,txt6;
Button details,details2,details3,details4,details5,details6;
Button front,back,upper;
Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);
		
		TabHost host=(TabHost)findViewById(R.id.tabHost);		
		host.setup();
		txt=(TextView)findViewById(R.id.textView1);
		details=(Button)findViewById(R.id.details);
		
		txt2=(TextView)findViewById(R.id.textView2);
		details2=(Button)findViewById(R.id.details2);
		
		txt3=(TextView)findViewById(R.id.textView3);
		details3=(Button)findViewById(R.id.details3);
		
		txt4=(TextView)findViewById(R.id.textView4);
		details4=(Button)findViewById(R.id.details4);
		
		txt5=(TextView)findViewById(R.id.textView5);
		details5=(Button)findViewById(R.id.details5);
		
		txt6=(TextView)findViewById(R.id.textView6);
		details6=(Button)findViewById(R.id.details6);
		
		front=(Button)findViewById(R.id.front);
		back=(Button)findViewById(R.id.back);
		upper=(Button)findViewById(R.id.upper);
		
		spinner=(Spinner)findViewById(R.id.spinner1);
		
		 List<String> categories = new ArrayList<String>();
	      categories.add("Kabali");
	      categories.add("Theri");
	      categories.add("24");
	      
	      
	      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
	      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      
	      
	      spinner.setAdapter(dataAdapter);
		
		
		TabHost.TabSpec spec=host.newTabSpec("Latest");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Latest");
		host.addTab(spec);
		
		
		 spec=host.newTabSpec("Upcoming");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Upcoming");
		host.addTab(spec);
		
		
		 spec=host.newTabSpec("Ticket");
		spec.setContent(R.id.tab3);
		 
		spec.setIndicator("Ticket");
		host.addTab(spec);
		
		
		details.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				// TODO Auto-generated method stub
				txt.setText("\n\t\t\t\t\tKabali (Superstar Rajinikanth) is a revered Malaysian Tamil don who gets released after serving a jail term of 25 years. He has lost his wife Kumudhavalli (Radhika Apte) when she was pregnant and does not know whether she is still alive. He has fought for the equal rights for the oppressed Tamils of Malaysia and has emerged as respected do-gooder don among them. He runs a school to refine the Tamil adolescents who roam around as spoilt brats in Malaysia and make them as good citizens." +
						"\n\t\t\t\t\tBut this is not enough to save the Tamil youngsters from getting spoilt. He has to bring down the drug and prostitution mafia run by a Chinese don Tony Lee (Winston Chao) and his accomplice Veerasekaran (Kishore)." +
						"\n\t\t\t\t\tThe rest is how ‘Kabali’ does this by winning over a dreadful empire of gangsters.");
						
						
			}
		});
		
		details2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				txt2.setText("\n\t\t\t\t\tTheri is an upcoming Tamil Action movie set to release on 14th Jan, 2016. This Action flick stars Superstar Vijay which is his 59th Film. Vijay plays a double role in this film and one of them is cop. Alongside Vijay, Samantha and Amy Jackson are also part of the movie.");
			}
		});
		
		details3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt3.setText("\n\t\t\t\t\t\t");
			}
		});
		
		details4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt4.setText("");
			}
		});
		
		details5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt5.setText("");
			}
		});
		
		
		
		
		
		upper.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(Movie.this,List.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie, menu);
		return true;
	}

}
