package com.example.movie;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class Betweenseat extends Activity {

	ConnectionDetector cd;
	Boolean isInternetPresent=false;

	
	
	JSONObject json;

	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;

	String ttag,cusname,ids,movie,show,pos,cost;
	
	Button deletee;
	

	private static String url="http://192.168.1.104/MovieLogin/delete.php";

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_betweenseat);
		cd=new ConnectionDetector(Betweenseat.this);
		
		deletee=(Button)findViewById(R.id.deletee);
		
		
		
		Intent intent=getIntent();
		cusname=intent.getStringExtra("cusname");
		ids=intent.getStringExtra("ids");
		movie=intent.getStringExtra("movie");
		show=intent.getStringExtra("show");
		pos=intent.getStringExtra("pos");
		cost=intent.getStringExtra("cost");
		
		
		
		
		
		deletee.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				isInternetPresent=false;
				isInternetPresent=cd.isConnectingToInternet();
				
				if(isInternetPresent){
					
					ttag="deletee";
					new check().execute();
					
					
				
				
				}
				
			}
		});
		
		
		
		
	}

	
	
	
	class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Betweenseat.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			
		
			
			
		
ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tags", ttag));
				
				
				
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
			
			
			Intent intent=new Intent(Betweenseat.this,Booking.class);
			intent.putExtra("cusname",cusname );
			intent.putExtra("ids", ids);
			intent.putExtra("movie",movie );
			intent.putExtra("show",show);
			intent.putExtra("pos",pos);
			intent.putExtra("cost",cost);
			
			startActivity(intent);
			progressDialog.dismiss();
		}
	
	
	}
		
		
		
	

}
