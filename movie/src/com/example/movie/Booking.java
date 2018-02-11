package com.example.movie;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Booking extends Activity {
TextView bookmovid,bookmovie,fakemovid,fakeshow,cost;
Spinner showspin;
ArrayAdapter<String> adapter;
ArrayList<String> list;

ConnectionDetector cd;
Boolean isInternetPresent=false;

Button create,seat;
EditText name;
JSONObject json;

JSONParser jsonParser=new JSONParser();
ProgressDialog progressDialog;

String movcus,movid,movishow,movimovi,movipos,Tag,costs;
TextView cu,i,mov,sho,pos;

private static String url="http://192.168.1.104/MovieLogin/Bookingmovie.php";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);
		cu=(TextView)findViewById(R.id.cu);
		i=(TextView)findViewById(R.id.i);
		mov=(TextView)findViewById(R.id.mov);
		sho=(TextView)findViewById(R.id.sho);
		pos=(TextView)findViewById(R.id.pos);
		cost=(TextView)findViewById(R.id.cost);
		
		create=(Button)findViewById(R.id.create);
				
		cd=new ConnectionDetector(getApplicationContext());
	
		Intent intent=getIntent();
		
		movcus=intent.getStringExtra("cusname");
	 movid=intent.getStringExtra("ids");
		 movimovi=intent.getStringExtra("movie");
		 movishow=intent.getStringExtra("show");
       movipos=intent.getStringExtra("pos");
       costs=intent.getStringExtra("cost");
       
      
     
		
       cu.setText("Name: "+movcus);
       i.setText(movid);
       mov.setText(movimovi);
       sho.setText("Show: "+movishow);
       pos.setText("Seat: "+movipos);
       cost.setText("Price: "+"Rs "+costs);
       
     
       
		
	
		
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				
				
					
					
					isInternetPresent=false;
					isInternetPresent=cd.isConnectingToInternet();
					
					if(isInternetPresent){
						
						Tag="create";
						new check().execute();
						
					//	Toast.makeText(getApplicationContext(), "Data insert", Toast.LENGTH_LONG).show();
						
						
						
						
						


						
						Intent intent=new Intent(getApplicationContext(),Payments.class);
						startActivity(intent);
						
						
					}
					
					else if(!isInternetPresent){
						
						Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
					}
					
					else{
						
						Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_LONG).show();
						
					}
				}
				
				
				
				
			
		});
	
	
	
	}
	
	
	public String remo(String s){
		
		 	s.endsWith(":");
				
				s.replace(":", "");
				Log.i("pooo", "pooo"+s);
				
			

return s;
		
	}
	
	
	
class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Booking.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			
		
			
			
		
ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("cusname", movcus));
				param.add(new BasicNameValuePair("id", movid));
				param.add(new BasicNameValuePair("movie", movimovi));
				param.add(new BasicNameValuePair("show", movishow));
				param.add(new BasicNameValuePair("pos", movipos));
			
				
				
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
			  Intent in=new Intent(Booking.this,Payments.class);
		       in.putExtra("cost", costs);
		       startActivity(in);
			progressDialog.dismiss();
		}
	
	
	}
		
		
		
		
		
	

	

}
