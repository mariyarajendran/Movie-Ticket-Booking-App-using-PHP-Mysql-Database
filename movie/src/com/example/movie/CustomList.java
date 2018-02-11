package com.example.movie;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{
	 private String[] status;
	  private String[] urls;
	  private String[] moviee;
	  private String[] synopsis;
	  private String[] producer;
	  private String[] director;
	  private String[] music;
	  private String[] cast;
	  private String[] idd;
	    private Bitmap[] bitmaps;
	    private Activity context;
	    ArrayList<HashMap<String, String>> personList;
	 
	
	    public CustomList(Activity context,String[] status, String[] urls, Bitmap[] bitmaps,String[] moviee,String[] synopsis,String[] producer,String[] director,String[] music,String[] cast,String[] idd) {
	        super(context, R.layout.image_list_view, urls);
	        this.context = context;
	        this.status= status;
	        this.urls= urls;
	        this.bitmaps= bitmaps;
	        this.moviee=moviee;
	        this.synopsis=synopsis;
	        this.producer=producer;
	        this.director=director;
	        this.music=music;
	        this.cast=cast;
	        this.idd=idd;
	   
	    }
	

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.image_list_view, null, true);
	        TextView textViewURL = (TextView) listViewItem.findViewById(R.id.txtserver);
	        TextView statuss = (TextView) listViewItem.findViewById(R.id.statuss);
	        TextView syn = (TextView) listViewItem.findViewById(R.id.syn);
	        TextView movi = (TextView) listViewItem.findViewById(R.id.movie);
	        TextView producers = (TextView) listViewItem.findViewById(R.id.producers);
	        TextView directors = (TextView) listViewItem.findViewById(R.id.directors);
	        TextView musics = (TextView) listViewItem.findViewById(R.id.musics);
	        TextView casts = (TextView) listViewItem.findViewById(R.id.casts);
	        TextView ids = (TextView) listViewItem.findViewById(R.id.ids);
	        
	        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageserver);
	        statuss.setText(status[position]);
	        textViewURL.setText(urls[position]);
	        image.setImageBitmap(bitmaps[position]);
	       movi.setText(moviee[position]);
	       syn.setText(synopsis[position]);
	      producers.setText(producer[position]);
	      directors.setText(director[position]);
	      musics.setText(music[position]);
	      casts.setText(cast[position]);
	      ids.setText(idd[position]);
	       
	       
	        
	        return  listViewItem;
	    }
	    
}
