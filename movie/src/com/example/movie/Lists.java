package com.example.movie;

import java.util.ArrayList;
import java.util.HashMap;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class Lists extends Activity {
	TabHost host;
	ListView listView;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lists);
		listView=(ListView)findViewById(R.id.listView1);
		ArrayList<HashMap<String, String>> li=new ArrayList<HashMap<String,String>>();
		
		host=(TabHost)findViewById(R.id.tabhost01);
		host.setup();
		
		
		TabHost.TabSpec spec=host.newTabSpec("Latest");
		spec.setContent(R.id.tab01);
		spec.setIndicator("Latest");
		host.addTab(spec);
		
		spec=host.newTabSpec("Upcoming");
		spec.setContent(R.id.tab02);
		spec.setIndicator("Upcoming");
		host.addTab(spec);
		
		 ActionBar bar = getActionBar();
			//for color
			bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
		//	bar.setTitle("View");
		
	
			/*new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                final Intent mainIntent = new Intent(Lists.this, Booking.class);
	          Lists.this.startActivity(mainIntent);
	               Lists.this.finish();
	            }
	        }, 5000);*/
			
		
		
String[] name=new String[]  {
	"Bahubali 2",
	"Visvaroopam 2",
	"Thirunal",
	"24"
	
};



int[] flags=new int[]{
		
		R.drawable.b,
		R.drawable.v,
		R.drawable.thi,
		R.drawable.t4
		
		
};

for(int i=0;i<4;i++){
	HashMap<String, String> hm=new HashMap<String, String>();
	hm.put("mov",""+name[i]);
	
	hm.put("im",Integer.toString(flags[i]));
	li.add(hm);
}
		String[] from={"im","mov","pric"};
		
int[] to={R.id.imageView01,R.id.movie,R.id.price};
SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(), li, R.layout.customlist, from, to);
listView.setAdapter(adapter);


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lists, menu);
		return true;
	}

}
