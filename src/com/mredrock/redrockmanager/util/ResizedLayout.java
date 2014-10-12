package com.mredrock.redrockmanager.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ResizedLayout extends LinearLayout{   
//     private static int count = 0;   
     private ResizedListener mResizedListener;
     
     public interface ResizedListener{
    	 public void onResize(int w,int h,int oldw,int oldh);
       }
     
     public void setOnResizedListener(ResizedListener l){
    	 mResizedListener=l;
       }
     
     public ResizedLayout(Context context, AttributeSet attrs) {   
       super(context, attrs);   
       }   
	       
	  @Override   
	  protected void onSizeChanged(int w, int h, int oldw, int oldh) {       
		  super.onSizeChanged(w, h, oldw, oldh);   
		  
		  if(mResizedListener!=null){
			  mResizedListener.onResize(w, h, oldw, oldh);
		  }
	          
//		  Log.e("onSizeChanged " + count++, "=>onResize called! w="+w + ",h="+h+",oldw="+oldw+",oldh="+oldh);   
	   }   
	 
}