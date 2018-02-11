package com.example.movie;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	Button logins,signup;
	EditText editusername,editpassword;
	
	ConnectionDetector cd;
	Boolean isInternetPresent=false;
	
	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;
	
	String edituser,editpass,Tag;
	
	private static String url="http://192.168.1.104/MovieLogin/index.php";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		cd=new ConnectionDetector(getApplicationContext());
		
		logins=(Button)findViewById(R.id.login);
		signup=(Button)findViewById(R.id.signup);
		editusername=(EditText)findViewById(R.id.editusername);
		editpassword=(EditText)findViewById(R.id.editpassword);
		
		
		 ActionBar bar = getActionBar();
			//for color
			bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
		//	bar.setTitle("View");
			
			Log.d("one", "1");
			
			logins.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					edituser=editusername.getText().toString();
					editpass=editpassword.getText().toString();
					
					
					if(edituser.equalsIgnoreCase("")||editpass.equalsIgnoreCase("")){
						
						Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
					}
					
					else{
						
						
						isInternetPresent=false;
						isInternetPresent=cd.isConnectingToInternet();
						
						if(isInternetPresent){
							
							Tag="logins";
							new check().execute();
							
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
			
			
			signup.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					Intent intent=new Intent(Login.this,Signup.class);
					startActivity(intent);
					
				}
			});
			
		
		
	}

	
	
	class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Login.this);
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

					final String Name=json.getString("name" );
					final String Result=json.getString("result" );
					final int error = json.getInt("error");
					if(error == 0){
						//with in doInBackground we must use runOnUiThread for display toast
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								
								editusername.setText("");
								editpassword.setText("");
							
								if(!Name.equals("Admin")){
								Intent intent=new Intent(Login.this,Loadimageserver.class);
								intent.putExtra("admin", Name);
								startActivity(intent);
								}else if(Name.equals("Admin")){
									Intent intent=new Intent(Login.this,Androidmain.class);
									intent.putExtra("admin", Name);
									startActivity(intent);
									
									
										
								}

								Toast.makeText(getApplicationContext(), Result , Toast.LENGTH_LONG).show();
							}
						});
						
					}else{
						//with in doInBackground we must use runOnUiThread for display toast
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								editusername.setText("");
								editpassword.setText("");
								
									Toast.makeText(getApplicationContext(), Result , Toast.LENGTH_LONG).show();
							}
						});

					}
					
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
