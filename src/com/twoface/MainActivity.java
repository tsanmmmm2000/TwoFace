package com.twoface;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity{
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.activity_main);
        
        ImageView twoface = (ImageView) findViewById(R.id.imagetwoface);    
        ImageView twoface2 = (ImageView) findViewById(R.id.imagetwoface2); 
        twoface.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View arg0) {
        	    // TODO Auto-generated method stub
        		Intent intent = new Intent();
            	intent.setClass(MainActivity.this,TossCoinActivity.class);
            	MainActivity.this.startActivity(intent);
        	}
        	
        });
        
        twoface2.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View arg0) {
        	    // TODO Auto-generated method stub
        		Intent intent = new Intent();
            	intent.setClass(MainActivity.this,TossCoinActivity.class);
            	MainActivity.this.startActivity(intent);
        	}
        	
        });
            
    }
    
               
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
	@Override 
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            dialog();
            
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }

    protected void dialog(){
        new AlertDialog.Builder(this)
        .setTitle("提示")
        .setMessage("確定要退出嗎？")
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent home = new Intent(Intent.ACTION_MAIN);  
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
                home.addCategory(Intent.CATEGORY_HOME);  
                startActivity(home);  
            }
        })
        .show();
    }
}
