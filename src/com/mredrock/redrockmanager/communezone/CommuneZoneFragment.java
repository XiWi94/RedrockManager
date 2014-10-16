package com.mredrock.redrockmanager.communezone;

import com.mredrock.redrockmanager.ActBarChangeInterface;
import com.mredrock.redrockmanager.DrawerMenuListener;
import com.mredrock.redrockmanager.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommuneZoneFragment extends Fragment{
	private ActBarChangeInterface aInterface;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		aInterface=(ActBarChangeInterface)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		aInterface.changeActionBar(DrawerMenuListener.COMMUNEZ);
		return inflater.inflate(R.layout.fragment_communezone, container, false);
	}
	

}
