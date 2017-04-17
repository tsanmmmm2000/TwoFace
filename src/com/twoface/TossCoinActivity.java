package com.twoface;

import java.util.Random;

import com.twoface.CustomAnimDrawable.AnimationDrawableListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class TossCoinActivity extends Activity implements OnClickListener{

	ImageView iv = null;
	ImageView tryagain = null;
	String result;
	Boolean isRun = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss_coin);
             
        iv = (ImageView) findViewById(R.id.imagefront);        
        iv.setOnClickListener(this); 
        
        
        try
        {
        	Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();	
        	if(bundle.getString("again").equals("again"))
        	{
        		tryagain = (ImageView) findViewById(R.id.imagetryagain);
        		tryagain.setVisibility(View.VISIBLE);
        	}
        }
        catch(Exception e)
        {}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toss_coin, menu);
        return true;
    }
    
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub				
        int head_or_tail = getRandom(0,1);
        
        if(head_or_tail == 0) {
        	iv.setBackgroundResource(R.drawable.back_rotate);
        	result = "back";
        } else {
        	iv.setBackgroundResource(R.drawable.front_rotate);
        	result = "front";
        }

        	
	    AnimationDrawable anim = null;
	    Object ob = iv.getBackground();
	    anim = (AnimationDrawable) ob;

	    
	    CustomAnimDrawable cusAnim = new CustomAnimDrawable(anim);
	    cusAnim.setAnimationListener(new FrameAnimationListener());  
	    cusAnim.stop();
	    cusAnim.start();
	    	    
	}
	
	class FrameAnimationListener implements AnimationDrawableListener{
		@Override
		public void onAnimationEnd(AnimationDrawable animation) {
			if(isRun == true) {
				Intent intent = new Intent();
				intent.setClass(TossCoinActivity.this,ResultActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("result", result);
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
				isRun = false;
			}
		}
		@Override
		public void onAnimationStart(AnimationDrawable animation) {
			
		}
	}
    
	private int getRandom(int min,int max){
        long seed = System.currentTimeMillis();
        Random r = new Random(); 
        r.setSeed(seed);        
        return (min + r.nextInt(max-min+1));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK) { 
	    	Intent intent = new Intent();
	    	intent.setClass(TossCoinActivity.this,MainActivity.class);
			TossCoinActivity.this.startActivity(intent);
			TossCoinActivity.this.finish();
	    } else if(keyCode == KeyEvent.KEYCODE_MENU) {
	        
	    } else if(keyCode == KeyEvent.KEYCODE_HOME) {
	        
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
