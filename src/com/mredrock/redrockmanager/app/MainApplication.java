package com.mredrock.redrockmanager.app;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.mredrock.redrockmanager.nethelper.RequestManager;

import android.app.Application;

public class MainApplication extends Application {

	public static RequestManager requestManager;

	@Override
	public void onCreate() {
	
		super.onCreate();
		requestManager=RequestManager.getInstance(this);
	}
	
	
}
