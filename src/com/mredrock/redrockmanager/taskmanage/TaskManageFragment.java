package com.mredrock.redrockmanager.taskmanage;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.redrockmanager.ActBarChangeInterface;
import com.mredrock.redrockmanager.DrawerMenuListener;
import com.mredrock.redrockmanager.R;

public class TaskManageFragment extends Fragment{
	private ActBarChangeInterface aInterface;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		aInterface=(ActBarChangeInterface)activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		aInterface.changeActionBar(DrawerMenuListener.TASKMANA);
		return inflater.inflate(R.layout.fragment_taskmanage, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent intent=new Intent(getActivity(),JPushActivity.class);
		startActivity(intent);
		super.onCreate(savedInstanceState);
	}
	
	

}
