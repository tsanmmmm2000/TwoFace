package com.twoface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class ResultActivity extends Activity{
	
	ImageView pic = null;
    ImageView words = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();	
        if(bundle.getString("result").equals("front")) 
        {
        	setContentView(R.layout.live);      
        	pic = (ImageView) findViewById(R.id.imagesmile);
        	words = (ImageView) findViewById(R.id.imagelive); 
        }
        if(bundle.getString("result").equals("back"))
        {
        	setContentView(R.layout.die);
        	pic = (ImageView) findViewById(R.id.imagedead);
        	words = (ImageView) findViewById(R.id.imagedie);
        }
         
        pic.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View arg0) {
        	    // TODO Auto-generated method stub
        		Intent intent = new Intent();
            	intent.setClass(ResultActivity.this,TossCoinActivity.class);
            	Bundle bundle = new Bundle();
				bundle.putString("again", "again");
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
				ResultActivity.this.finish();
        	}
        	
        });
        
        words.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View arg0) {
        	    // TODO Auto-generated method stub
        		Intent intent = new Intent();
            	intent.setClass(ResultActivity.this,TossCoinActivity.class);
            	Bundle bundle = new Bundle();
				bundle.putString("again", "again");
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
				ResultActivity.this.finish();
        	}
        	
        });
    }
    
               
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK) { 
	    	Intent intent = new Intent();
	    	intent.setClass(ResultActivity.this,TossCoinActivity.class);
        	Bundle bundle = new Bundle();
			bundle.putString("again", "again");
			intent.putExtras(bundle);
			startActivityForResult(intent, 0);
			ResultActivity.this.finish();
	    } else if(keyCode == KeyEvent.KEYCODE_MENU) {
	        
	    } else if(keyCode == KeyEvent.KEYCODE_HOME) {
	        
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
