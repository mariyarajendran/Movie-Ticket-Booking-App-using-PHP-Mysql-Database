package com.example.movie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.movie.Signup.check;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Image extends Activity {
	
	
	

	ConnectionDetector cd;
	Boolean isInternetPresent=false;
	
	JSONParser jsonParser=new JSONParser();
	ProgressDialog progressDialog;
	
	private static String url="http://192.168.1.104/MovieLogin/adminupdate.php";
	
	Spinner status;
String admin;
	
	ArrayList<String> list;
	
	ArrayAdapter<String> adapter;
	
	
Button upload,choose,insert,preview;
ImageView image;

public static final String UPLOAD_URL = "http://192.168.1.104/MovieLogin/upload.php";
public static final String UPLOAD_KEY = "image";

String dates,casts,musics,genres,directors,producers,synopsiss,movies,Tag,state;

private int PICK_IMAGE_REQUEST = 1;


private Bitmap bitmap;

private Uri filePath;
EditText date,cast,music,genre,director,producer,synopsis,movie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		list=new ArrayList<String>();
		
		date=(EditText)findViewById(R.id.date);
		cast=(EditText)findViewById(R.id.cast);
		music=(EditText)findViewById(R.id.music);
		genre=(EditText)findViewById(R.id.genre);
		director=(EditText)findViewById(R.id.director);
		producer=(EditText)findViewById(R.id.producer);
		synopsis=(EditText)findViewById(R.id.synopsis);
		movie=(EditText)findViewById(R.id.movie);
		
		status=(Spinner)findViewById(R.id.status);
		
		
		cd=new ConnectionDetector(getApplicationContext());
		
		insert=(Button)findViewById(R.id.insert);
		upload=(Button)findViewById(R.id.upload);
		choose=(Button)findViewById(R.id.choose);
		preview=(Button)findViewById(R.id.preview);
	
		
		image=(ImageView)findViewById(R.id.image);
		
		
		list.add("Available");
		list.add("Unavailable");
		
		adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnertext2, list);
		status.setAdapter(adapter);
		
		Intent intent=getIntent();
		admin=intent.getStringExtra("admin");
		
		
		
		
		insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				dates=date.getText().toString();
				casts=cast.getText().toString();
				musics=music.getText().toString();
				genres=genre.getText().toString();
				directors=director.getText().toString();
				producers=producer.getText().toString();
				synopsiss=synopsis.getText().toString();
				movies=movie.getText().toString();
				state=status.getSelectedItem().toString();
				
				
				
if(dates.equalsIgnoreCase("")||casts.equalsIgnoreCase("")||musics.equalsIgnoreCase("")||genres.equalsIgnoreCase("")||directors.equalsIgnoreCase("")||producers.equalsIgnoreCase("")||synopsiss.equalsIgnoreCase("")||movies.equalsIgnoreCase("")){
					
					Toast.makeText(getApplicationContext(), "Fill all fields",Toast.LENGTH_SHORT).show();
					
				}
				else{
					
					
					isInternetPresent=false;
					isInternetPresent=cd.isConnectingToInternet();
					
					if(isInternetPresent){
						
						Tag="insert";
						new check().execute();
					date.setText("");
					cast.setText("");
					music.setText("");
					genre.setText("");
					director.setText("");
					producer.setText("");
					synopsis.setText("");
					movie.setText("");
					
					
						
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
		
		
		
		preview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intent=new Intent(Image.this,Loadimageserver.class);
			intent.putExtra("admin", admin);
			startActivity(intent);
				
				
			}
		});
		
		
		
		
		
		choose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
		        intent.setType("image/*");
		        intent.setAction(Intent.ACTION_GET_CONTENT);
		        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
		   
				
				        
			}
		});
		
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				
				uploadImage();
				
			}
		});
		
		
		
	
		
		
		
		
	}

	
	
class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Image.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("date", dates));
				param.add(new BasicNameValuePair("cast", casts));
				param.add(new BasicNameValuePair("music", musics));
				param.add(new BasicNameValuePair("genre", genres));
				param.add(new BasicNameValuePair("director",directors));
				param.add(new BasicNameValuePair("producer", producers));
				param.add(new BasicNameValuePair("synopsis", synopsiss));
				param.add(new BasicNameValuePair("status", state));
				param.add(new BasicNameValuePair("movie", movies));
				
				
				
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
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
 
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	  public String getStringImage(Bitmap bmp){
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	        byte[] imageBytes = baos.toByteArray();
	        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
	        return encodedImage;
	    }
	
	  
	  
	  private void uploadImage(){
	        class UploadImage extends AsyncTask<Bitmap,Void,String>{
	 
	            ProgressDialog loading;
	            RequestHandler rh = new RequestHandler();
	 
	            @Override
	            protected void onPreExecute() {
	                super.onPreExecute();
	                loading = ProgressDialog.show(Image.this, "Uploading...", null,true,true);
	            }
	 
	            @Override
	            protected void onPostExecute(String s) {
	                super.onPostExecute(s);
	                loading.dismiss();
	                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
	            }
	 
	            @Override
	            protected String doInBackground(Bitmap... params) {
	                Bitmap bitmap = params[0];
	                String uploadImage = getStringImage(bitmap);
	 
	                HashMap<String,String> data = new HashMap<String, String>();
	 
	                data.put(UPLOAD_KEY, uploadImage);
	                data.put("date", dates);
	                data.put("cast", casts);
	                data.put("music", musics);
	                data.put("genre", genres);
	                data.put("director", directors);
	                data.put("producer", producers);
	                data.put("synopsis", synopsiss);
	                data.put("movie", movies);
	                
	                String result = rh.sendPostRequest(UPLOAD_URL,data);
	 
	                return result;
	            }
	        }
	 
	        UploadImage ui = new UploadImage();
	        ui.execute(bitmap);
	    }
	  
	 
	  private void viewImage() {
	        startActivity(new Intent(this, Loadimageserver.class));
	   
	    }
}
