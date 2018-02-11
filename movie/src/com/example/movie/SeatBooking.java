package com.example.movie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SeatBooking extends Activity {
	
	ConnectionDetector cd;
	Boolean isInternetPresent=false;
	JSONObject json;

	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;

	String Tags,seatfinal="";
	int countt=0;
	
	private static String url="http://192.168.1.104/MovieLogin/delete.php";
	
	ArrayList<String> select;
	ArrayList<String> unselect;
   	String myJSON,m,Tag;
    JSONArray peoples = null;
	    ProgressDialog pDialog;
	    
		int i;
	ArrayList<Integer>  dt=new ArrayList<Integer>();	
	ArrayList<String> posi;
	private GridView gv;
	private Adapter adapter;
		ArrayList<Seat> seat = new ArrayList<Seat>();
		String cusname,ids,movie,show;
		int count;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv  = (GridView) findViewById(R.id.gridView1);
        posi=new ArrayList<String>();
        
        cd=new ConnectionDetector(SeatBooking.this);
        
        select=new ArrayList<String>();
        unselect=new ArrayList<String>();
        
        getData();
        
        Intent intent=getIntent();
        cusname=intent.getStringExtra("cusname");
        ids=intent.getStringExtra("id");
        movie=intent.getStringExtra("movie");
        show=intent.getStringExtra("show");
       
        gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				Seat _seat = seat.get(pos);
				
				
				//Toast.makeText(SeatBooking.this,_seat.getSeatno() , Toast.LENGTH_LONG).show();
				if( _seat.getImg() == R.drawable.rred){
					
					seat.remove(pos);
					seat.add(pos, new Seat(_seat.getSeatno(), R.drawable.rgreen));
					
					//Toast.makeText(getApplicationContext(),con +po+":", Toast.LENGTH_LONG).show();
					String po=String.valueOf(pos);
					
					select.add(po);
				//	Toast.makeText(getApplicationContext(),select+"", Toast.LENGTH_LONG).show();
					
					
					
					adapter.notifyDataSetChanged();
				}else if( _seat.getImg() == R.drawable.rgreen){
					seat.remove(pos);
					seat.add(pos, new Seat(_seat.getSeatno(), R.drawable.rred));
					
					String po=String.valueOf(pos);
					
					unselect.add(po);
					//Toast.makeText(getApplicationContext(),unselect+"", Toast.LENGTH_LONG).show();
					
					
					adapter.notifyDataSetChanged();
				}
			}
		});
        
        
       
	}
	
	
	@Override
	public void onBackPressed() {
	// TODO Auto-generated method stub
		
			
			Tags="deletee";
			new check().execute();
			
			finish();	
		
		
		

	}
	
	
	
	class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(SeatBooking.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			
		
			
			
		
ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tags", Tags));
				
				
				
				
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
		
	
        
   
	
	protected void showList(){
        try {
        	
        	 
            JSONObject jsonObj = new JSONObject(myJSON);
          
            int er=jsonObj.getInt("error");
            
            String e=String.valueOf(er);
          
            peoples=jsonObj.getJSONArray("selected_seat");
            
           for(int i=1;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                int id = c.getInt("selected");
              
               
                  dt.add(id);
            
            }
            
          
            
            if(er==0){
               // peoples = jsonObj.getJSONArray("selected_seat");
            	for(int i=1;i<=100;i++){
            		Log.d("sear1", "sear1");
            	          	if(dt.contains(i)){
            			Log.i("sear2", "sear2"+dt.contains(i));
            			seat.add(new Seat("S"+i, R.drawable.rblack)); 
            			Log.d("sear3", "sear3");
            	          	}
            	          	
            	          	
            	          	
            				else{
                				seat.add(new Seat("S"+i, R.drawable.rred));	
                     			
                			}}
            			}
            adapter = new Adapter(this, seat);
	        gv.setAdapter(adapter);
	        gv.setSelector(R.drawable.transparent);

      
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
	     			pDialog = new ProgressDialog(SeatBooking.this);
	     			pDialog.setMessage("Logging In..");
	     			pDialog.setIndeterminate(false);
	     			pDialog.setCancelable(false);
	     			pDialog.show();}
	        	
	        	
	            @Override
	            protected String doInBackground(String... params) {
	                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
	                Log.d("1", "1");
	                HttpPost httppost = new HttpPost("http://192.168.1.104/MovieLogin/seatp.php");
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
	 
	 
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.seat_booking, menu);
			
			
			return true;
		}
	 
	
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		  int id = item.getItemId();
		
	    if (id == R.id.save) {
	    	
	    	for(String m:unselect){
				
				select.remove(m);
			}
    
			
			if(select.isEmpty()){
				
				Toast.makeText(getApplicationContext(), "please select your seat", Toast.LENGTH_LONG).show();	
			}
			
			
			else{
				
				String seats="0";
				for(String pos:select){
					
					countt=countt+1;
					
					seatfinal=seats+=":"+pos;
					
				}
				
				
				int n=countt*200;
				
			String c=String.valueOf(n);
		//	Toast.makeText(getApplicationContext(), "count is : "+c, Toast.LENGTH_LONG).show();
				
			Intent intent=new Intent(SeatBooking.this,Betweenseat.class);
	    	
	    	intent.putExtra("cusname", cusname);
	    	intent.putExtra("ids", ids);
	    	intent.putExtra("movie", movie);
	    	intent.putExtra("show", show);
	    	intent.putExtra("pos", seatfinal);
	    	intent.putExtra("cost", c);
	    	
	    	startActivity(intent);
	    	countt=0;
			}
					
					
			}
				
		
	  	
	    		
	    
	        return true;
	    }
	 
	
	 

}
