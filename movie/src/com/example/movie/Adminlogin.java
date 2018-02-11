package com.example.movie;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.movie.Login.check;

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

public class Adminlogin extends Activity {
	EditText user,pass;
	Button login;
	
	ConnectionDetector cd;
	Boolean isInternetPresent=false;
	
	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;
	
	String edituser,editpass,Tag;
	
	private static String url="http://192.168.1.104/MovieLogin/adminlogin.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminlogin);
		cd=new ConnectionDetector(getApplicationContext());
		
		user=(EditText)findViewById(R.id.user);
		pass=(EditText)findViewById(R.id.pass);
		login=(Button)findViewById(R.id.login);
		
		
	login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			edituser=user.getText().toString();
			editpass=pass.getText().toString();
			
			
			if(edituser.equalsIgnoreCase("")||editpass.equalsIgnoreCase("")){
				
				Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
			}
			
			else{
				
				
				isInternetPresent=false;
				isInternetPresent=cd.isConnectingToInternet();
				
				if(isInternetPresent){
					
					Tag="login";
					new check().execute();
					
					user.setText("");
					pass.setText("");
					
					Intent intent=new Intent(Adminlogin.this,Androidmain.class);
					startActivity(intent);
					
				}
				
				else if(!isInternetPresent){
					
					Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
				}
				
				else{
					
					Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_LONG).show();
					
				}
			}
			
			
			
		}
	});
		
		
		
		
		
	}


	
	
	class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Adminlogin.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("username", edituser));
				param.add(new BasicNameValuePair("password", editpass));
				
				
				JSONObject json=jsonParser.makeHttpRequest(url, "POST", param);
				
				
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
