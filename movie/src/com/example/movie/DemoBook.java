package com.example.movie;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DemoBook extends Activity {

	TextView bookmovid,bookmovie,name;
	Spinner showspin;
	ArrayAdapter<String> adapter;
	ArrayList<String> list;

	

	Button next;
	
	

	String cusname,id,show,movie;


	ConnectionDetector cd;
	Boolean isInternetPresent=false;

	
	
	JSONObject json;

	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;

	String Tag;
	

	private static String url="http://192.168.1.104/MovieLogin/seat.php";



		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_demo_book);
			
			bookmovid=(TextView)findViewById(R.id.bookmovid);
			bookmovie=(TextView)findViewById(R.id.bookmovie);
			
			
			
			showspin=(Spinner)findViewById(R.id.showspin);
			
			next=(Button)findViewById(R.id.next);
			
			name=(TextView)findViewById(R.id.name);
			
			cd=new ConnectionDetector(getApplicationContext());
		
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
			String adminname=intent.getStringExtra("admin");
			
			
			bookmovie.setText(movi);
		bookmovid.setText(movid);
		name.setText("Name: "+adminname);
		
	
			
			
			
		
			
		next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					cusname=name.getText().toString();
					id=bookmovid.getText().toString();
					movie=bookmovie.getText().toString();
					show=showspin.getSelectedItem().toString();
					
					
					isInternetPresent=false;
					isInternetPresent=cd.isConnectingToInternet();
					
					
					
					if(isInternetPresent){
						
						Tag="next";
						new check().execute();}
					
					
					Intent intent=new Intent(getApplicationContext(),SeatBooking.class);
					intent.putExtra("cusname",cusname);
					intent.putExtra("id",id );
					intent.putExtra("movie",movie );
					intent.putExtra("show",show );
					
					startActivity(intent);
					
					
					
					}
			});
		
	
		
		
		}
		
		
		
	
		class check extends AsyncTask<String, String, String>{
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progressDialog = new ProgressDialog(DemoBook.this);
				progressDialog.setMessage("Logging In..");
				progressDialog.setIndeterminate(false);
				progressDialog.setCancelable(false);
				progressDialog.show();
			}

			@Override
			protected String doInBackground(String... arg0) {
			
				
				
			
				
				
			
	ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
					
					param.add(new BasicNameValuePair("tag", Tag));
					param.add(new BasicNameValuePair("cusname", cusname));
					param.add(new BasicNameValuePair("id", id));
					param.add(new BasicNameValuePair("show", show));
					
					
					
					 json=jsonParser.makeHttpRequest(url, "POST", param);
					
					
			
					try 
					{
						final String Result=json.getString("result" );
						
						//with in doInBackground we must use runOnUiThread for display toast
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								//Toast.makeText(getApplicationContext(), Result , Toast.LENGTH_LONG).show();
							}
						});
						
					}
					catch (JSONException e) 
					{
							e.printStackTrace();
					}
					
					
					
				
			return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				progressDialog.dismiss();
			}
		
		
		}
			
			
			
		

		

		}
