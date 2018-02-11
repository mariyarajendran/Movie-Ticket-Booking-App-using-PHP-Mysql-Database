package com.example.movie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Getallimagess {
	   public static String[] status;
	 public static String[] imageURLs;
	    public static Bitmap[] bitmaps;
	    public static String[] movie;
	    public static String[] synopsis;
	    public static String[] director;
	    public static String[] producer;
	    public static String[] music;
	    public static String[] cast;
	    public static String[] id;
	 
	  //  public static final String JSON_ARRAY="result";
	  //  public static final String IMAGE_URL = "url";
	    private JSONArray urls;
	
	
	    public Getallimagess(String json){
	        try {
	            JSONObject jsonObject = new JSONObject(json);
	            urls = jsonObject.getJSONArray("result");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
	    private Bitmap getImage(JSONObject jo){
	        URL url = null;
	        Bitmap image = null;
	        try {
	            url = new URL(jo.getString("url"));
	            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return image;
	    }
	    
	    
	    public void getAllImages() throws JSONException {
	        bitmaps = new Bitmap[urls.length()];
	       status = new String[urls.length()];
	        imageURLs = new String[urls.length()];
	        movie=new String[urls.length()];
	       synopsis=new String[urls.length()];
	     director=new String[urls.length()];
	     producer=new String[urls.length()];
	     music=new String[urls.length()];
	    cast=new String[urls.length()];
	    id=new String[urls.length()];
	 
	        for(int i=0;i<urls.length();i++){
	        	  status[i] = urls.getJSONObject(i).getString("status");
	            imageURLs[i] = urls.getJSONObject(i).getString("url");
	            movie[i]=urls.getJSONObject(i).getString("movie");
	            synopsis[i]=urls.getJSONObject(i).getString("synopsis");
	            director[i]=urls.getJSONObject(i).getString("director");
	           producer[i]=urls.getJSONObject(i).getString("producer");
	        music[i]=urls.getJSONObject(i).getString("music");
	        cast[i]=urls.getJSONObject(i).getString("cast");
	        
	        
	           id[i]=urls.getJSONObject(i).getString("id");
	            JSONObject jsonObject = urls.getJSONObject(i);
	            bitmaps[i]=getImage(jsonObject);
	        }
	    }
	    

}
