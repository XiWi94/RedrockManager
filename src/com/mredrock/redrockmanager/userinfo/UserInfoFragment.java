package com.mredrock.redrockmanager.userinfo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.redrockmanager.R;
import com.mredrock.redrockmanager.launch.LoginActivity;

public class UserInfoFragment extends Fragment{

	private Activity context;
	
	@Override
	public void onAttach(Activity activity) {
		context=activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_userinfo, container, false);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_userinfo, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.menu_userinfo_edit){
			Intent intent=new Intent(context,LoginActivity.class);
			context.startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
}
