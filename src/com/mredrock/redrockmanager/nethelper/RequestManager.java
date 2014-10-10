package com.mredrock.redrockmanager.nethelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RequestManager {
	private static RequestManager rm;
	private Context context;
	private ImageLoader mImageLoader;
	private RequestQueue mRequestQueue;
	
	private RequestManager(Context context){
		this.context=context;
		mRequestQueue=getRequestQueue();
		mImageLoader=getImageLoader();
	}
	
	public static synchronized RequestManager getInstance(Context context){
		if(rm==null){
			rm=new RequestManager(context);
		}
		return rm;
	}
	
	public RequestQueue getRequestQueue(){
		if(mRequestQueue==null){
			mRequestQueue=Volley.newRequestQueue(context.getApplicationContext());
		}
		return mRequestQueue;
	}

	public ImageLoader getImageLoader(){
		if(mImageLoader==null){
			mImageLoader=new ImageLoader(mRequestQueue,null);
		}
		return mImageLoader;
	}
	public <T>void addToRequestQueue(Request<T> request){
		mRequestQueue.add(request);
	}
}
