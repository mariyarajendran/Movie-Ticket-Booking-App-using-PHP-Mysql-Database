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
import android.widget.TextView;
import android.widget.Toast;

public class Admincustomview extends Activity {
TextView movieid,customername,moviename,showtime,seatposition,fakeid;
Button delete;
         
ConnectionDetector cd;
Boolean isInternetPresent=false;

JSONParser jsonParser=new JSONParser();
ProgressDialog progressDialog;

String fakeids,Tag;
private static String url="http://192.168.1.104/MovieLogin/admindelete.php";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admincustomview);
		movieid=(TextView)findViewById(R.id.movieid);
		fakeid=(TextView)findViewById(R.id.fakeid);
		customername=(TextView)findViewById(R.id.customername);
		moviename=(TextView)findViewById(R.id.moviename);
		showtime=(TextView)findViewById(R.id.showtime);
		seatposition=(TextView)findViewById(R.id.seatposition);
		
		cd=new ConnectionDetector(getApplicationContext());
		
		delete=(Button)findViewById(R.id.delete);
		
		
		Intent intent=getIntent();
		String moviebookid=intent.getStringExtra("moviebookid");
		 fakeids=intent.getStringExtra("fakeid");
		String cusname=intent.getStringExtra("cusname");
		String movname=intent.getStringExtra("movname");
		String showtimes=intent.getStringExtra("showtime");
		String seatpositions=intent.getStringExtra("seatposition");
		
		
		
		movieid.setText(moviebookid);
		fakeid.setText(fakeids);
		customername.setText(cusname);
		moviename.setText(movname);
		showtime.setText(showtimes);
		seatposition.setText(seatpositions);
		
		
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Tag="delete";
				new check().execute();
				Intent intent=new Intent(Admincustomview.this,Adminpage.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		
		
	
		
		
	}

	
	
class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Admincustomview.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("deleteid", fakeids));
			
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
