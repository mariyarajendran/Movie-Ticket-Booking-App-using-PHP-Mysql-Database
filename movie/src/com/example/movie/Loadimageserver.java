package com.example.movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Loadimageserver extends Activity {

	ListView listfromserver;
	
	public static final String url="http://192.168.1.104/MovieLogin/getallimage.php";
	 
    public Getallimagess getAlImages;
 
    public static final String BITMAP_ID = "BITMAP_ID";
    
	String admin;
    
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadimageserver);
		
		
		
		listfromserver=(ListView)findViewById(R.id.listfromserver);
		// listfromserver.setOnItemClickListener( this);
	        getURLs();
		
	        
	        Intent intent=getIntent();
	   admin= intent.getStringExtra("admin");
	      
	       

	        
	        listfromserver.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View v,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
					
					
					
			    	 String lsid = ((TextView) v.findViewById(R.id.ids)).getText().toString();
				   // String lsdat = ((TextView) v.findViewById(R.id.lstdate)).getText().toString();
				    String lsc = ((TextView) v.findViewById(R.id.casts)).getText().toString();
				    String lsmus = ((TextView) v.findViewById(R.id.musics)).getText().toString();
				   // String lsgen = ((TextView) v.findViewById(R.id.lstgenre)).getText().toString();
				    String lsdir = ((TextView) v.findViewById(R.id.directors)).getText().toString();
				    String lspro = ((TextView) v.findViewById(R.id.producers)).getText().toString();
				    String  lssyn = ((TextView) v.findViewById(R.id.syn)).getText().toString();
				    String lsmov = ((TextView) v.findViewById(R.id.movie)).getText().toString();
				    String status = ((TextView) v.findViewById(R.id.statuss)).getText().toString();
				   // String im = ((ImageView) v.findViewById(R.id.lstimgs)).getText().toString();
				    
				    ImageView im = (ImageView) v.findViewById(R.id.imageserver);

				    
				    im.buildDrawingCache();
				    Bitmap bitmap = im.getDrawingCache();
				    
				    
				    int imin = im.getId();
				    Intent intent = new Intent(Loadimageserver.this,Customview.class);

			        intent.putExtra("lsmovid", lsid);
				    //intent.putExtra("lsdate",lsdat);
				    intent.putExtra("lscast",lsc);
				    intent.putExtra("lsmusic",lsmus);
				    
				 //   intent.putExtra("lsgenre",lsgen);
				    
				    intent.putExtra("lsdirector",lsdir);
				    
				    intent.putExtra("lsproducer",lspro);
				    
				    intent.putExtra("lssynopsis",lssyn);
				    intent.putExtra("lsmovie",lsmov);
				    intent.putExtra("status",status);
				    intent.putExtra("admin",admin);
				    
				    
				    
				    
				   

				   
				    intent.putExtra("image", bitmap);
				    
				    
				    
				 
				    startActivity(intent);
				    
					
					
					
				}
			});
	        
		
		
	}

	
	
	
	 private void getImages(){
	        class GetImages extends AsyncTask<Void,Void,Void>{
	            ProgressDialog loading;
	            @Override
	            protected void onPreExecute() {
	                super.onPreExecute();
	                loading = ProgressDialog.show(Loadimageserver.this,"Downloading images...","Please wait...",false,false);
	            }
	 
	            @Override
	            protected void onPostExecute(Void v) {
              super.onPostExecute(v);
	                loading.dismiss();
	                //Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();
	                CustomList customList = new CustomList(Loadimageserver.this,Getallimagess.status,Getallimagess.imageURLs,Getallimagess.bitmaps,Getallimagess.movie,Getallimagess.synopsis,Getallimagess.director,Getallimagess.producer,Getallimagess.music,Getallimagess.cast,Getallimagess.id);
	                listfromserver.setAdapter(customList);
	                
	              
	             
	            }
	 
	            @Override
	            protected Void doInBackground(Void... voids) {
	                try {
	                    getAlImages.getAllImages();
	 
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                return null;
	            }
	        }
	        GetImages getImages = new GetImages();
	        getImages.execute();
	    }

	 
	 
	 
	 private void getURLs() {
	        class GetURLs extends AsyncTask<String,Void,String>{
	            ProgressDialog loading;
	 
	            @Override
	            protected void onPreExecute() {
	                super.onPreExecute();
	                loading = ProgressDialog.show(Loadimageserver.this,"Loading...","Please Wait...",true,true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s) {
	                super.onPostExecute(s);
	                loading.dismiss();
	                getAlImages = new Getallimagess(s);
	                getImages();
	            }
	 
	            @Override
	            protected String doInBackground(String... strings) {
	                BufferedReader bufferedReader = null;
	                try {
	                    URL url = new URL(strings[0]);
	                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	                    StringBuilder sb = new StringBuilder();
	 
	                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	 
	                    String json;
	                    while((json = bufferedReader.readLine())!= null){
	                        sb.append(json+"\n");
	                    }
	 
	                    return sb.toString().trim();
	 
	                }catch(Exception e){
	                    return null;
	                }
	            }
	        }
	        GetURLs gu = new GetURLs();
	        gu.execute(url);
	    }
	 
	

	 
		
}
