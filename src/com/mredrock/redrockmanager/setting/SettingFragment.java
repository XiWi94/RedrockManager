package com.mredrock.redrockmanager.setting;

import com.mredrock.redrockmanager.ActBarChangeInterface;
import com.mredrock.redrockmanager.DrawerMenuListener;
import com.mredrock.redrockmanager.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends Fragment{
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
		aInterface.changeActionBar(DrawerMenuListener.SETTING);
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}
	

}
