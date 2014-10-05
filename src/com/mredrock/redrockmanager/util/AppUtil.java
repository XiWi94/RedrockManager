package com.mredrock.redrockmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppUtil {
	public static final String keyIsFirstStart="isFirstStart";

	public static SharedPreferences getPreferences(Context context){
//		Toast.makeText(context,context.getPackageName(),Toast.LENGTH_SHORT).show();
		return context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
	}
}
