package com.mredrock.redrockmanager.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.mredrock.redrockmanager.nethelper.RequestManager;
import com.mredrock.redrockmanager.util.AppUtil;

public class MainApplication extends Application {

	public static RequestManager requestManager;
	public static SharedPreferences sp;
	
	@Override
	public void onCreate() {
	
		super.onCreate();
		requestManager=RequestManager.getInstance(this);
		sp=AppUtil.getPreferences(this);
	}
	
	
}
