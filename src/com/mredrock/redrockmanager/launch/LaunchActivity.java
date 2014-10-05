package com.mredrock.redrockmanager.launch;

import com.mredrock.redrockmanager.R;
import com.mredrock.redrockmanager.util.AppUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LaunchActivity extends Activity{
	
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

	}
	
}
