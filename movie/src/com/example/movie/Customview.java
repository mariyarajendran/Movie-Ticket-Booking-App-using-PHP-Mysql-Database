package com.example.movie;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.movie.Admincustomview.check;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Customview extends Activity {
ImageView imgv;
TextView moviels,castls,directorls,producerls,musicls,genrels,datels,synopsisls,movid,movie;
Button book,delete,update;
String m,id,admin,status;

ConnectionDetector cd;
Boolean isInternetPresent=false;

JSONParser jsonParser=new JSONParser();
ProgressDialog progressDialog;
EditText statusedit;

String fakeids,Tag;
private static String url="http://192.168.1.104/MovieLogin/admindeletemovie.php";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customview);
		imgv=(ImageView)findViewById(R.id.imgv);
		//imgg=(ImageView)findViewById(R.id.imgg);
		moviels=(TextView)findViewById(R.id.moviels);
		castls=(TextView)findViewById(R.id.castls);
		directorls=(TextView)findViewById(R.id.directorls);
		producerls=(TextView)findViewById(R.id.producerls);
		musicls=(TextView)findViewById(R.id.musicls);
		genrels=(TextView)findViewById(R.id.genrels);
		datels=(TextView)findViewById(R.id.datels);
		synopsisls=(TextView)findViewById(R.id.synopsisls);
		movid=(TextView)findViewById(R.id.movid);
		movie=(TextView)findViewById(R.id.movie);
		
		book=(Button)findViewById(R.id.book);
		delete=(Button)findViewById(R.id.delete);
		
		statusedit=(EditText)findViewById(R.id.statusedit);
		
		update=(Button)findViewById(R.id.update);
		
		cd=new ConnectionDetector(getApplicationContext());
		
		Intent intent=getIntent();
		 id=intent.getStringExtra("lsmovid");
		//String d=intent.getStringExtra("lsdate");
		String c=intent.getStringExtra("lscast");
		String mu=intent.getStringExtra("lsmusic");
		//String g=intent.getStringExtra("lsgenre");
		String dir=intent.getStringExtra("lsdirector");
		String p=intent.getStringExtra("lsproducer");
		String s=intent.getStringExtra("lssynopsis");
		 m=intent.getStringExtra("lsmovie");
		 status=intent.getStringExtra("status");
		 admin=intent.getStringExtra("admin");
		//int pic =intent.getIntExtra("i", 0);
		
	Bitmap	bitmap = intent.getParcelableExtra("image");

	
	
	imgv.setImageBitmap(bitmap);
	//imgv.setImageBitmap(bitmap);
	moviels.setText(m);
	castls.setText(c);
	directorls.setText(dir);
	producerls.setText(p);
	musicls.setText(mu);
	//genrels.setText(g);
	//datels.setText(d);
	synopsisls.setText(s);
	movid.setText(id);
	movie.setText(m);
	genrels.setText(status);
	
	statusedit.setText(status);
		
		
	
	//Toast.makeText(getApplicationContext(), admin, Toast.LENGTH_SHORT).show();
	
	if(admin.equals("Admin")){
		
	delete.setVisibility(View.VISIBLE);
	book.setVisibility(View.INVISIBLE);
	update.setVisibility(View.INVISIBLE);
	statusedit.setVisibility(View.INVISIBLE);
	
	
		
	}
	else{
		
		delete.setVisibility(View.INVISIBLE);
		update.setVisibility(View.INVISIBLE);
		statusedit.setVisibility(View.INVISIBLE);
		if(status.equals("Available")){
			book.setVisibility(View.VISIBLE);
			
		}
		else{
			book.setVisibility(View.INVISIBLE);
			update.setVisibility(View.VISIBLE);
			
		}
	}
	
	
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Tag="delete";
				new check().execute();
				Intent intent=new Intent(Customview.this,Loadimageserver.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		
		book.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),DemoBook.class);
				
				i.putExtra("movie", m);
				i.putExtra("id",id );
				i.putExtra("admin", admin);
				
				startActivity(i);
				
			}
		});
		
		
	}
	
	
class check extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(Customview.this);
			progressDialog.setMessage("Logging In..");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
		
			
			ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
				
				param.add(new BasicNameValuePair("tag", Tag));
				param.add(new BasicNameValuePair("deleteid",id));
				param.add(new BasicNameValuePair("status",status));
			
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
