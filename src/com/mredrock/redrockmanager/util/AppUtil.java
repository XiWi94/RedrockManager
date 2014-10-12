package com.mredrock.redrockmanager.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;

import com.mredrock.redrockmanager.util.ResizedLayout.ResizedListener;

public class AppUtil {
	public static final String KEYISFIRSTSTART="isFirstStart";
	public static final String KEYACCOUNT="account";
	public static final String KEYACCOUNTS="accounts";
	
	public static final int RESIZEDBIG=1;
	public static final int RESIZEDSMALL=2;
	
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
	
	/*public static boolean getInputState(Activity act){
		((InputMethodManager)act
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(showFlags, hideFlags)
		return ((InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE)).isAcceptingText();
	}*/

	public static void resizedLayout(ResizedLayout layout,final Handler handler){
		layout.setOnResizedListener(new ResizedListener() {
			
			@Override
			public void onResize(int w, int h, int oldw, int oldh) {
				int change=RESIZEDBIG;
				if(h<oldh){
					change=RESIZEDSMALL;
				}
				Message mMessage=new Message();
				mMessage.what=1;
				mMessage.arg1=change;
				handler.sendMessage(mMessage);
			}
		});
		
	}	
}
