package com.mredrock.redrockmanager.launch;
//TODO ListPopup没装 网络没弄 数据没弄 
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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
import com.android.volley.toolbox.StringRequest;
import com.mredrock.redrockmanager.R;
import com.mredrock.redrockmanager.app.MainApplication;
import com.mredrock.redrockmanager.mode.login.UserInfo;

public class LoginActivity extends Activity{

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
	}
	
	private void setListener() {
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
				switcherList.show();
				
			}
		});
		switcherList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				
			}
		});

		btnLogin.setOnClickListener(new OnClickListener() {
		//TODO	
			@Override
			public void onClick(View arg0) {
				String name=editUser.getText().toString();
				String password=editPassword.getText().toString();
				if(name.equals("")||password.equals("")){
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
//TODO
		UserInfo[] users = null;
		popupAdapter=new ListPopupAdapter(this, users);
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
	
	private void requestFromNet(){
//		StringRequest stringRequest=new StringRequest(Method.POST,getUrl(),new Listener<String>() {
		final StringRequest stringRequest=new StringRequest(Method.POST,getUrl(),new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				Toast.makeText(getApplicationContext(), arg0, Toast.LENGTH_LONG).show();
//				Intent intent=new Intent()
				
			}
		},new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				//TODO 保留stringRequest.getCacheKey()到sp中，学号为key, getCacheKey为值。把data放置到内容中，提示已过期，须联网
//				byte[] data=MainApplication.requestManager.getRequestQueue().getCache().get(stringRequest.getCacheKey()).data;
//				Toast.makeText(getApplicationContext(),new String(data) , Toast.LENGTH_SHORT).show();
				
			}
		});
		MainApplication.requestManager.addToRequestQueue(stringRequest);
		
	}
	private final static String LoginBasic="http://202.202.43.87/Homework1.0/LoginDeal";
	private final static String Identity="student";
}
