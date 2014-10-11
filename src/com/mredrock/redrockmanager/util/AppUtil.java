package com.mredrock.redrockmanager.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;

public class AppUtil {
	public static final String KEYISFIRSTSTART="isFirstStart";
	public static final String KEYACCOUNT="account";
	public static final String KEYACCOUNTS="accounts";
	
	public static SharedPreferences getPreferences(Context context){
//		Toast.makeText(context,context.getPackageName(),Toast.LENGTH_SHORT).show();
		return context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
	}
	
	/**
	 * 关闭输入法
	 * @param act
	 */
	public static void closeInputMethod(Activity act){
		((InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE)).

	    hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
