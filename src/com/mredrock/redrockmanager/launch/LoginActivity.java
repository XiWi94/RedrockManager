package com.mredrock.redrockmanager.launch;
//TODO 离线使用 

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.mredrock.redrockmanager.MainActivity;
import com.mredrock.redrockmanager.R;
import com.mredrock.redrockmanager.app.MainApplication;
import com.mredrock.redrockmanager.mode.login.LoginPreference;
import com.mredrock.redrockmanager.util.AppUtil;
import com.mredrock.redrockmanager.util.ResizedLayout;

public class LoginActivity extends Activity{
//	private static final String TAG="LoginActivity";

	private Button btnCancel;
	private ImageView imgYours;
	private EditText editUser;
	private ImageView imgSwitcher;
	private EditText editPassword;
	private Button btnLogin;
	private ListPopupWindow switcherList;
	private ListPopupAdapter popupAdapter;
	private ImageView imgDelUser;
	private ImageView imgDelPassword;
//	private ImageView imgDelListPopup;
	private LoginPreference loginUser;
	LoginPreference[] accounts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		setListener();
		initData();
	}

	private void initView() {
		btnCancel=(Button)findViewById(R.id.btn_login_cancel);
		imgYours=(ImageView)findViewById(R.id.icon_login_yours);
		editUser=(EditText)findViewById(R.id.text_login_user);
		imgSwitcher=(ImageView)findViewById(R.id.icon_login_switcher);
		editPassword=(EditText)findViewById(R.id.text_login_password);
		btnLogin=(Button)findViewById(R.id.btn_login);
		switcherList=new ListPopupWindow(LoginActivity.this);
		imgDelUser=(ImageView)findViewById(R.id.icon_login_delete_user);
		imgDelPassword=(ImageView)findViewById(R.id.icon_login_delete_psw);
		loginUser=new LoginPreference();
	}
	
	private void setListener() {

		InputHandler handler=new InputHandler();
		AppUtil.resizedLayout((ResizedLayout)findViewById(R.id.root_login), handler);
		editUser.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				if(arg1){
					imgDelUser.setVisibility(View.VISIBLE);
					imgDelUser.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							editUser.setText("");
							editPassword.setText("");
							imgYours.setImageResource(R.drawable.ic_launcher);
						}
					});
				}else{
					imgDelUser.setVisibility(View.INVISIBLE);
				}
				
			}
		});
		
		editPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				if(editPassword.isFocused()){
					imgDelPassword.setVisibility(View.VISIBLE);
					imgDelPassword.setOnClickListener(new OnClickListener() {
							
						@Override
						public void onClick(View arg0) {
							editPassword.setText("");
						}
					});
				}else{
					imgDelPassword.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				LoginActivity.this.finish();
			}
		});
		
		imgSwitcher.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				AppUtil.closeInputMethod(LoginActivity.this);
				new Handler().postDelayed(new Runnable(){
					@Override
					public void run() {
//						Log.i(TAG, switcherList+"");
						switcherList.show();
					}
				}, 100);
			}
		});
		
		switcherList.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				editUser.setText(((LoginPreference)popupAdapter.getItem(position)).getUserId());
				editPassword.setText(((LoginPreference)popupAdapter.getItem(position)).getPassword());
				getImageViewChanged(((LoginPreference)popupAdapter.getItem(position)).getImgUrl());
				switcherList.dismiss();
			}
		});

		btnLogin.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				switcherList.setWidth(btnLogin.getWidth());
				switcherList.setHeight(ListPopupWindow.WRAP_CONTENT);
				if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
					btnLogin.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				}else{
					btnLogin.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				}
			}
		});
		switcherList.setAnchorView(editUser);
		//意味着该窗口打开时其他窗口都被屏蔽
		switcherList.setModal(true);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				loginUser.setUserId(editUser.getText().toString());
				loginUser.setPassword(editPassword.getText().toString());
				if(loginUser.getUserId().equals("")||loginUser.getPassword().equals("")){
					Toast.makeText(LoginActivity.this,
							getResources().getString(R.string.toast_login_emptyhint), 
							Toast.LENGTH_SHORT).show();
				}else{
					requestFromNet();
				}
				
			}
		});
		
	}	

	private void initData() {
		
		String jsonAccounts=MainApplication.sp.getString(AppUtil.KEYACCOUNTS, "");
		accounts=new Gson().fromJson(jsonAccounts, LoginPreference[].class);
//		Log.i(TAG, new Gson().toJson(accounts));
		popupAdapter=new ListPopupAdapter(this, accounts);
		setAdapter();
	}
	
	private void setAdapter() {
		switcherList.setAdapter(popupAdapter);
	}

	private String getUrl(){
		Uri.Builder uriBuilder=Uri.parse(LoginBasic).buildUpon()
				.appendQueryParameter("username", editUser.getText().toString())
				.appendQueryParameter("userpsw", editPassword.getText().toString())
				.appendQueryParameter("identity", Identity);
		return uriBuilder.build().toString();
	
	}
	
	private String getImageUrl(){
		return "http://p4.qqgexing.com/touxiang/20120810/1649/5024cb06e9c97.jpg";
	}
	
	private void requestFromNet(){
		
		StringRequest stringRequest=new StringRequest(Method.POST,getUrl(),new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
//				Toast.makeText(getApplicationContext(), arg0, Toast.LENGTH_LONG).show();
				handleLoginResponse(arg0);
			}
		},new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				//TODO 离线情况未实现
				//				byte[] data=MainApplication.requestManager.getRequestQueue().getCache().get(stringRequest.getCacheKey()).data;

				Toast.makeText(LoginActivity.this, arg0.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
		ImageRequest imageRequest=new ImageRequest(getImageUrl(), new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {				
				imgYours.setImageBitmap(arg0);
			}
		}, 0, 0, null, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				imgYours.setImageResource(R.drawable.ic_launcher);
				
			}
		});
		
		MainApplication.requestManager.addToRequestQueue(stringRequest);
		MainApplication.requestManager.addToRequestQueue(imageRequest);
	}
	
	private synchronized void handleLoginResponse(String response){
		if(response.equals("success")){
			loginUser.setName("a");
			loginUser.setImgUrl(getImageUrl());
			saveData();
			Intent intent=new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
			this.finish();	
		}else if(response.equals("user_error")){
			Toast.makeText(this,getResources().getString(R.string.toast_login_usererror), Toast.LENGTH_SHORT).show();
		}else if(response.equals("psw_error")){
			Toast.makeText(this,getResources().getString(R.string.toast_login_pswerror), Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this,getResources().getString(R.string.toast_login_neterror), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private  synchronized void saveData(){
		
		Editor edit=MainApplication.sp.edit();
		edit.putString(AppUtil.KEYACCOUNT, new Gson().toJson(loginUser)).commit();
		String jsonAccounts=MainApplication.sp.getString(AppUtil.KEYACCOUNTS, "");
//		Log.i(TAG, "aa"+jsonAccounts);
		if(jsonAccounts.equals("")){
			accounts=new LoginPreference[1];
			accounts[0]=loginUser;
		}else{
//			accounts=new Gson().fromJson(jsonAccounts, LoginPreference[].class);
			int i;
			for(i=0;i<accounts.length;i++){
				if(accounts[i].getUserId().equals(loginUser.getUserId())){
					accounts[i]=loginUser;
					break;
				}
			}
			if(i>=accounts.length){
				LoginPreference[] a=new LoginPreference[accounts.length+1];
				a[0]=loginUser;
				for(int j=1;j<a.length;j++){
					a[j]=accounts[j-1];
				}
				accounts=a;
			}
		}
		
		edit.putString(AppUtil.KEYACCOUNTS, new Gson().toJson(accounts)).commit();
//		Log.i(TAG, new Gson().toJson(accounts));
	}
	
	private void getImageViewChanged(String imageUrl){
		ImageRequest iconRequest=new ImageRequest(imageUrl, 
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap arg0) {
						imgYours.setImageBitmap(arg0);
					}
		}, 0, 0, null, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				imgYours.setImageResource(R.drawable.ic_launcher);
				
			}
		});
		
		MainApplication.requestManager.addToRequestQueue(iconRequest);
	}

	public class InputHandler extends Handler{

		private int resize=1;
		@Override
		public void handleMessage(Message msg) {
			if(msg.what==resize){
				if(msg.arg1==AppUtil.RESIZEDBIG){
					imgYours.setVisibility(imgYours.VISIBLE);
				}else if(msg.arg1==AppUtil.RESIZEDSMALL){
					imgYours.setVisibility(imgYours.GONE);
				}
			}
			super.handleMessage(msg);
		}
		
	}

	private final static String LoginBasic="http://202.202.43.87/Homework1.0/LoginDeal";
	private final static String Identity="student";
//	private final static int ACCOUNTNUM=10;
}
