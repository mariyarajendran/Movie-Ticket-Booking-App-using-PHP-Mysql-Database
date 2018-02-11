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

public class Signup extends Activity {
	
	
	
	ConnectionDetector cd;
	Boolean isInternetPresent=false;
	
	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	
	private static String url="http://192.168.1.104/MovieLogin/index.php";
	
	
	String use,pas,Tag,names,mailids,phonenos,citys;
	
	EditText user,pass,name,mailid,phoneno,city;
	Button signup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		user=(EditText)findViewById(R.id.user);
		pass=(EditText)findViewById(R.id.pass);
		name=(EditText)findViewById(R.id.name);
		mailid=(EditText)findViewById(R.id.mailid);
		phoneno=(EditText)findViewById(R.id.phoneno);
		city=(EditText)findViewById(R.id.city);
		
		signup=(Button)findViewById(R.id.signup);
		
		cd=new ConnectionDetector(getApplicationContext());
		
		
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				use=user.getText().toString();
				pas=pass.getText().toString();
				names=name.getText().toString();
				mailids=mailid.getText().toString();
				phonenos=phoneno.getText().toString();
				citys=city.getText().toString();
				
				
if(use.equalsIgnoreCase("")||pas.equalsIgnoreCase("")||pas.equalsIgnoreCase("")||names.equalsIgnoreCase("")||mailids.equalsIgnoreCase("")||phonenos.equalsIgnoreCase("")||citys.equalsIgnoreCase("")){
					
					Toast.makeText(getApplicationContext(), "Fill all fields",Toast.LENGTH_SHORT).show();
					
				}
				else{
					
					
					isInternetPresent=false;
					isInternetPresent=cd.isConnectingToInternet();
					
					if(isInternetPresent){
						
						Tag="signup";
						new check().execute();
						
					//	Toast.makeText(getApplicationContext(), "Data insert", Toast.LENGTH_LONG).show();
						user.setText("");
						pass.setText("");
						name.setText("");
						mailid.setText("");
						phoneno.setText("");
						city.setText("");
						
						Intent intent=new Intent(Signup.this,Login.class);
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
			progressDialog = new ProgressDialog(Signup.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("username", use));
				param.add(new BasicNameValuePair("password", pas));
				param.add(new BasicNameValuePair("name", names));
				param.add(new BasicNameValuePair("mailid", mailids));
				param.add(new BasicNameValuePair("phoneno", phonenos));
				param.add(new BasicNameValuePair("city", citys));
				
				
				
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
