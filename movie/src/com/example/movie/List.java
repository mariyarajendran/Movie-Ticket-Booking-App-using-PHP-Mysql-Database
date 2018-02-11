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
import android.os.Handler;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class List extends Activity {
	
	   //latest
    String myJSON;
    String results; 
 
    private static final String TAG_RESULTS="result";
    private static final String TAG_DATE = "date";
    private static final String TAG_CAST = "cast";
    private static final String TAG_MUSIC = "music";
    private static final String TAG_GENRE = "genre";
    private static final String TAG_DIRECTOR = "director";
    private static final String TAG_PRODUCER = "producer";
    private static final String TAG_SYNOPSIS = "synopsis";
    private static final String TAG_MOVIE = "movie";
    private static final String TAG_MOVIEID = "id";
    
ImageView imgs;
HashMap<String,String> persons;
 
    JSONArray peoples = null;
    ProgressDialog pDialog,ppDialog;
 
    ArrayList<HashMap<String, String>> personList;
ListView lst1;
ListView uplst;

//latest



TabHost host;



//upcoming

MYJSONParser jsonParser = new MYJSONParser();
private static String url = "http://192.168.1.104/MovieLogin/upcoming.php";

private static final String TAG_UPRESULTS="upresult";
private static final String TAG_UPDATE = "date";
private static final String TAG_UPCAST = "cast";
private static final String TAG_UPMUSIC = "music";
private static final String TAG_UPGENRE = "genre";
private static final String TAG_UPDIRECTOR = "director";
private static final String TAG_UPPRODUCER = "producer";
private static final String TAG_UPSYNOPSIS = "synopsis";
private static final String TAG_UPMOVIE = "movie";

JSONArray uppeoples = null;

HashMap<String,String> uppersons;
ArrayList<HashMap<String, String>> uppersonList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		imgs=(ImageView)findViewById(R.id.imgs);
		
lst1=(ListView)findViewById(R.id.lst1);
uplst=(ListView)findViewById(R.id.uplst);
host=(TabHost)findViewById(R.id.movietab);
host.setup();


TabHost.TabSpec spec=host.newTabSpec("Now Showing");
spec.setContent(R.id.movietab1);
spec.setIndicator("Now Showing");
host.addTab(spec);

spec=host.newTabSpec("Comming Soon");
spec.setContent(R.id.movietab2);
spec.setIndicator("Comming Soon");
host.addTab(spec);

getDatas();
personList = new ArrayList<HashMap<String,String>>();
uppersonList = new ArrayList<HashMap<String,String>>();
getData();

lst1.setOnItemClickListener(new OnItemClickListener() {


    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int pos,
            long id) {
    	Log.d("ome", "omee");
    	Intent intent = new Intent(List.this,Customview.class);
    	 String lsid = ((TextView) v.findViewById(R.id.lstmovid)).getText().toString();
	    String lsdat = ((TextView) v.findViewById(R.id.lstdate)).getText().toString();
	    String lsc = ((TextView) v.findViewById(R.id.lstcast)).getText().toString();
	    String lsmus = ((TextView) v.findViewById(R.id.lstmusic)).getText().toString();
	    String lsgen = ((TextView) v.findViewById(R.id.lstgenre)).getText().toString();
	    String lsdir = ((TextView) v.findViewById(R.id.lstdirector)).getText().toString();
	    String lspro = ((TextView) v.findViewById(R.id.lstproducer)).getText().toString();
	    String  lssyn = ((TextView) v.findViewById(R.id.lstsynopsis)).getText().toString();
	    String lsmov = ((TextView) v.findViewById(R.id.lstmovie)).getText().toString();
	   // String im = ((ImageView) v.findViewById(R.id.lstimgs)).getText().toString();
	    
	    ImageView im = (ImageView) v.findViewById(R.id.lstimgs);

	    
	    im.buildDrawingCache();
	    Bitmap bitmap = im.getDrawingCache();
	    
	    
	    int imin = im.getId();

        intent.putExtra("lsmovid", lsid);
	    intent.putExtra("lsdate",lsdat);
	    intent.putExtra("lscast",lsc);
	    intent.putExtra("lsmusic",lsmus);
	    
	    intent.putExtra("lsgenre",lsgen);
	    
	    intent.putExtra("lsdirector",lsdir);
	    
	    intent.putExtra("lsproducer",lspro);
	    
	    intent.putExtra("lssynopsis",lssyn);
	    intent.putExtra("lsmovie",lsmov);
	    
	    
	    
	    
	   

	   
	    intent.putExtra("image", bitmap);
	    
	    
	    
	   // intent.putExtra("i", imin);
	    startActivity(intent);

    }


});




uplst.setOnItemClickListener(new OnItemClickListener() {


    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int pos,
            long id) {
    	Log.d("ome", "omee");
    	Intent intent = new Intent(List.this,Customview.class);

	    String ID = ((TextView) v.findViewById(R.id.id)).getText().toString();
	   // String MOVIES = ((TextView) v.findViewById(R.id.movies)).getText().toString();
	   // String im = ((ImageView) v.findViewById(R.id.lstimgs)).getText().toString();
	    
	    ImageView IMAGES = (ImageView) v.findViewById(R.id.imgs);

	    
	    IMAGES.buildDrawingCache();
	    Bitmap bitmaps = IMAGES.getDrawingCache();
	    
	    
	  //  int imin = im.getId();


	    intent.putExtra("upm",ID);
	   // intent.putExtra("upc",MOVIES);
	    
	    
	   

	   
	    intent.putExtra("upimage", bitmaps);
	    
	    
	    
	   // intent.putExtra("i", imin);
	    startActivity(intent);

    }


});








	}
	
	
	
	






	

	
	
	
	


	
int[] flags=new int[]{
			
			R.drawable.a5,
			R.drawable.a1,
			R.drawable.a3,
			R.drawable.a4,
			R.drawable.a2,
			R.drawable.a6,
			R.drawable.a7
			
			
			
	};

int[] Upflags=new int[]{
		
		R.drawable.b3,
		R.drawable.b2,
		R.drawable.b4,
		R.drawable.b1,
		
		
		
		
};
 





	
	
	protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
 
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String movid=c.getString(TAG_MOVIEID);
                String da = c.getString(TAG_DATE);
                String ca = c.getString(TAG_CAST);
                String mu = c.getString(TAG_MUSIC);
                String ge = c.getString(TAG_GENRE);
                String dir = c.getString(TAG_DIRECTOR);
                String prod = c.getString(TAG_PRODUCER);
                String syn = c.getString(TAG_SYNOPSIS);
                String mov = c.getString(TAG_MOVIE);
              
               
 
                 persons = new HashMap<String,String>();
                 persons.put(TAG_MOVIEID, movid);
                persons.put(TAG_DATE,da);
                persons.put(TAG_CAST,ca);
                persons.put(TAG_MUSIC,mu);
                persons.put(TAG_GENRE,ge);
                persons.put(TAG_DIRECTOR,dir);
                persons.put(TAG_PRODUCER,prod);
                persons.put(TAG_SYNOPSIS,syn);
                persons.put(TAG_MOVIE,mov);
             
                persons.put("image", Integer.toString(flags[i]));
 
                personList.add(persons);
            }
 
 
 String[] from=new String[]{
		 TAG_MOVIEID,
		 TAG_DATE,
		 TAG_CAST,
		 TAG_MUSIC,
		 TAG_GENRE,
		 TAG_DIRECTOR,
		 TAG_PRODUCER,
		 TAG_SYNOPSIS,
		 TAG_MOVIE,
		 
		 "image"
		 
		 
	 } ;          
	 
	 
	 int[] to=new int[]{
			 R.id.lstmovid,
			R.id.lstdate,
			R.id.lstcast,
			R.id.lstmusic,
			R.id.lstgenre,
			R.id.lstdirector,
			R.id.lstproducer,
			R.id.lstsynopsis,
			R.id.lstmovie,
			R.id.lstimgs
			
	 };
 
            ListAdapter adapter = new SimpleAdapter(
                    List.this, personList, R.layout.listv,
                   from,
                    to
            );
 
            lst1.setAdapter(adapter);
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
    }
	

	
	
	
	
	
	
	
	protected void showListUpcoming(){
        try {
            JSONObject jsonObj = new JSONObject(MYJSONParser.json);
            uppeoples = jsonObj.getJSONArray(TAG_UPRESULTS);
 
            for(int i=0;i<uppeoples.length();i++){
                JSONObject c = uppeoples.getJSONObject(i);
                String upda = c.getString(TAG_UPDATE);
                String upca = c.getString(TAG_UPCAST);
                String upmu = c.getString(TAG_UPMUSIC);
                String upge = c.getString(TAG_UPGENRE);
                String updir = c.getString(TAG_UPDIRECTOR);
                String upprod = c.getString(TAG_UPPRODUCER);
                String upsyn = c.getString(TAG_UPSYNOPSIS);
                String upmov = c.getString(TAG_UPMOVIE);
 
                 uppersons = new HashMap<String,String>();
 
                 uppersons.put(TAG_UPDATE,upda);
                 uppersons.put(TAG_UPCAST,upca);
                 uppersons.put(TAG_UPMUSIC,upmu);
                 uppersons.put(TAG_UPGENRE,upge);
                 uppersons.put(TAG_UPDIRECTOR,updir);
                 uppersons.put(TAG_UPPRODUCER,upprod);
                 uppersons.put(TAG_UPSYNOPSIS,upsyn);
                 uppersons.put(TAG_UPMOVIE,upmov);
                uppersons.put("image", Integer.toString(Upflags[i]));
 
                uppersonList.add(uppersons);
            }
 
 
 String[] from=new String[]{
		 
		 TAG_UPDATE,
		 TAG_UPCAST,
		 TAG_UPMUSIC,
		 TAG_UPGENRE,
		 TAG_UPDIRECTOR,
		 TAG_UPPRODUCER,
		 TAG_UPSYNOPSIS,
		 TAG_UPMOVIE,
		 "image"
		 
		 
	 } ;          
	 
	 
	 int[] to=new int[]{
			 
			R.id.lst2date,
			R.id.lst2cast,
			R.id.lst2music,
			R.id.lst2genre,
			R.id.lst2director,
			R.id.lst2producer,
			R.id.lst2synopsis,
			R.id.lst2movie,
			R.id.imgs 
	 };
 
            ListAdapter adapter = new SimpleAdapter(
                    List.this, uppersonList, R.layout.uplistv,
                   from,
                    to
            );
 
            uplst.setAdapter(adapter);
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
    }
	
	
	
	
	
	
	 public void getDatas(){
	        class GetDataJSON extends AsyncTask<String, Void, String>{
	 
	        	
	        	
	     		@Override
	     		protected void onPreExecute() {
	     			super.onPreExecute();
	     			pDialog = new ProgressDialog(List.this);
	     			pDialog.setMessage("Logging In..");
	     			pDialog.setIndeterminate(false);
	     			pDialog.setCancelable(false);
	     			pDialog.show();}
	        	
	        	
	            @Override
	            protected String doInBackground(String... params) {
	            	
	            	
	            	
	            	
	            	
	            	//	con("http://10.0.2.2/MovieLogin/movieexpress.php");
	            		
	            
	            	/*	new Handler().postDelayed(new Runnable() {
	                        @Override
	                        public void run() {
	                        	con("http://10.0.2.2/MovieLogin/upcoming.php");
	        	            	
	                        }
	                    }, 5000);*/
	            	
	            		
	            		con("http://192.168.1.104/MovieLogin/movieexpress.php");
	            		
	            		//cons("http://10.0.2.2/MovieLogin/upcoming.php");
	            	
	            	
	            		
	            	
	            	
	            	//con("http://10.0.2.2/MovieLogin/upcoming.php");
	            	// jsonParser.makeHttpRequest(url,"POST");
	            	 
	            	
	            	return results;
	              
	            	
	                
	            }
	 
	            @Override
	            protected void onPostExecute(String result){
	                myJSON=result;
	               
	                showList();
	               pDialog.dismiss(); 
	               // MYJSONParser.json=result;
	              //  showListUpcoming();
	              //  pDialog.dismiss(); 
	                
	               
	                
	            }
	        }
	        GetDataJSON g = new GetDataJSON();
	        g.execute();
	    }
	 
	 
	 public String con(String c){
		 
		 DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
         Log.d("1", "1");
         HttpPost httppost = new HttpPost(c);
         Log.d("2", "2");
         // Depends on your web service
         httppost.setHeader("Content-type", "application/json");

         InputStream inputStream = null;
          results = null;
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
             results = sb.toString();
         } catch (Exception e) {
             // Oops
         }
         finally {
             try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
         }
         return results;
		 
		 
	 }
	 
	 
	 public void getData(){
	        class GetDataJSON extends AsyncTask<String, Void, String>{
	 
	        	
	        	
	     		@Override
	     		protected void onPreExecute() {
	     			super.onPreExecute();
	     			ppDialog = new ProgressDialog(List.this);
	     			ppDialog.setMessage("Logging In..");
	     			ppDialog.setIndeterminate(false);
	     		ppDialog.setCancelable(false);
	     			ppDialog.show();}
	        	
	        	
	            @Override
	            protected String doInBackground(String... params) {
	            		cons("http://192.168.1.104/MovieLogin/upcoming.php");
	            	 
	            	
	            	return results;
	              
	            	
	                
	            }
	 
	            @Override
	            protected void onPostExecute(String result){
	               // myJSON=result;
	               
	             //   showList();
	             //  pDialog.dismiss(); 
	                MYJSONParser.json=result;
	                showListUpcoming();
	                ppDialog.dismiss(); 
	                
	               
	                
	            }
	        }
	        GetDataJSON g = new GetDataJSON();
	        g.execute();
	    }
	 
	 
	 
 public String cons(String c){
		 
		 DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
         Log.d("1", "1");
         HttpPost httppost = new HttpPost(c);
         Log.d("2", "2");
         // Depends on your web service
         httppost.setHeader("Content-type", "application/json");

         InputStream inputStream = null;
          results = null;
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
             results = sb.toString();
         } catch (Exception e) {
             // Oops
         }
         finally {
             try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
         }
         return results;
		 
		 
         
         
         
	 
 }
 


 
	
}

 
	 


