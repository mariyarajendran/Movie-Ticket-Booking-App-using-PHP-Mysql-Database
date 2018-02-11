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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FakeSeat extends Activity {


ConnectionDetector cd;
Boolean isInternetPresent=false;

Button create,seat;

JSONObject json;

JSONParser jsonParser=new JSONParser();
ProgressDialog progressDialog;


String fakeid,fakeshow,Tag;

private static String url="http://192.168.1.104/MovieLogin/Bookingmovie.php";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fake_seat);
		
		seat=(Button)findViewById(R.id.seat);
		cd=new ConnectionDetector(getApplicationContext());
		
		
		Intent intent=getIntent();
		 fakeid=intent.getStringExtra("fakeid");
		 fakeshow=intent.getStringExtra("fakeshow");
		
		Toast.makeText(getApplicationContext(),fakeid+  ""+fakeshow ,Toast.LENGTH_LONG).show();
		
		
		seat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				isInternetPresent=false;
				isInternetPresent=cd.isConnectingToInternet();
				
				if(isInternetPresent){
				Tag="seat";
				new check().execute();}
				
		//Intent intent=new Intent(getApplicationContext(),Secondseatfake.class);
		//startActivity(intent);
				
			}
		});
	}
class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(FakeSeat.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("fakeids", fakeid));
				param.add(new BasicNameValuePair("fakeshows", fakeshow));
				
				
				 json=jsonParser.makeHttpRequest(url, "POST", param);
				
			
			
			
			
			
				try 
				{
					final String Result=json.getString("result" );
					
					//with in doInBackground we must use runOnUiThread for display toast
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(getApplicationContext(), Result , Toast.LENGTH_LONG).show();
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
