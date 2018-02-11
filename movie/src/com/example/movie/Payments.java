package com.example.movie;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Payments extends Activity {
	
	Spinner sp;
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	TextView amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payments);
		sp=(Spinner)findViewById(R.id.sp);
		amount=(TextView)findViewById(R.id.amount);
		
		list=new ArrayList<String>();
		list.add("DEBIT CARD");
		list.add("CREDIT CARD");
		
		adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.spintext, list);
		sp.setAdapter(adapter);
		
		Intent intent=getIntent();
	String cost=	intent.getStringExtra("cost");
	amount.setText("Rs "+cost);
		
		
	}

	

}
