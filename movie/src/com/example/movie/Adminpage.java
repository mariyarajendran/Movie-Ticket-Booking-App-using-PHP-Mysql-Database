package com.example.movie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Adminpage extends Activity {
	
	
	String myJSON;
    JSONArray peoples = null;
	    ProgressDialog pDialog;
		int i;
		   ArrayList<HashMap<String, String>> personList;
		   ListAdapter adapter;
		   
		   ListView ls;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminpage);
		ls=(ListView)findViewById(R.id.ls);
		
		personList=new ArrayList<HashMap<String,String>>();
		
		
		getData();
		
	
	
	
	
	
	
	
	 ls.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				
				
				Intent intent = new Intent(Adminpage.this,Admincustomview.class);
		    	 String moviebookid = ((TextView) v.findViewById(R.id.id)).getText().toString();
		    	 String fakeid  = ((TextView) v.findViewById(R.id.fakeid)).getText().toString();
		    	 String cusname= ((TextView) v.findViewById(R.id.cusname)).getText().toString();
		    	  String movname = ((TextView) v.findViewById(R.id.movname)).getText().toString();
		    	  String showtime = ((TextView) v.findViewById(R.id.showtime)).getText().toString();
		    	  String seatposition = ((TextView) v.findViewById(R.id.seatposition)).getText().toString();
			  
			  
			  
			  

		        intent.putExtra("moviebookid", moviebookid);
		        intent.putExtra("fakeid", fakeid);
			      intent.putExtra("cusname",cusname);
			    intent.putExtra("movname",movname);
			    intent.putExtra("showtime",showtime);
			    intent.putExtra("seatposition",seatposition);
			     startActivity(intent);
			     finish();
				
				
				
			}
		});
     
	
	
}

	protected void showList(){
        try {
        	
        	 
            JSONObject jsonObj = new JSONObject(myJSON);
          
           
            peoples=jsonObj.getJSONArray("result");
            
           for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String id=c.getString("id");
                String cusname="Name: "+c.getString("cusname");
                String movname=c.getString("movname");
                String showtime=c.getString("showtime");
                String seatposition=c.getString("seatposition");
                
              
                HashMap<String,String> persons = new HashMap<String,String>();  
                
                persons.put("id", "Ticket No: "+id);
                persons.put("fakeid", id);
                persons.put("cusname", cusname);
                persons.put("movname", movname);
                persons.put("showtime", showtime);
                persons.put("seatposition", seatposition);
                
                
                personList.add(persons);
                
             
            
            }
           adapter=new SimpleAdapter(Adminpage.this, personList, R.layout.adminpagelist, new String[]{"id","fakeid","cusname","movname","showtime","seatposition"}, new int[]{R.id.id,R.id.fakeid,R.id.cusname,R.id.movname,R.id.showtime,R.id.seatposition});
            
          ls.setAdapter(adapter);
            
          
           

      
        }
            

            catch (JSONException e) {
               e.printStackTrace();
           }
    
       }
   	
    
        
	 public void getData(){
	        class GetDataJSON extends AsyncTask<String, Void, String>{
	 
	        	
	        	
	     		@Override
	     		protected void onPreExecute() {
	     			super.onPreExecute();
	     			pDialog = new ProgressDialog(Adminpage.this);
	     			pDialog.setMessage("Logging In..");
	     			pDialog.setIndeterminate(false);
	     			pDialog.setCancelable(false);
	     			pDialog.show();}
	        	
	        	
	            @Override
	            protected String doInBackground(String... params) {
	                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
	                Log.d("1", "1");
	                HttpPost httppost = new HttpPost("http://192.168.1.104/MovieLogin/admin.php");
	                Log.d("2", "2");
	                // Depends on your web service
	                httppost.setHeader("Content-type", "application/json");
	 
	                InputStream inputStream = null;
	                String result = null;
	                try {
	                    HttpResponse response = httpclient.execute(httppost);
	                    HttpEntity entity = response.getEntity();
	 
	                    inputStream = entity.getContent();
	                    // json is UTF-8 by default
	                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	                    StringBuilder sb = new StringBuilder();
	 
	                    String line = null;
	                    while ((line = reader.readLine()) != null)
	                    {
	                        sb.append(line + "\n");
	                    }
	                    result = sb.toString();
	                } catch (Exception e) {
	                    // Oops
	                }
	                finally {
	                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	                }
	                return result;
	            }
	 
	            @Override
	            protected void onPostExecute(String result){
	                myJSON=result;
	                showList();
	                
	                
	               
	                    	pDialog.dismiss();
	            
	            }
	        }
	        GetDataJSON g = new GetDataJSON();
	        g.execute();
	        
	        
	        
	     
	    }
	 

	
	
	
	

}
