package com.mredrock.redrockmanager.dailymanage;

import com.mredrock.redrockmanager.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class DailyManageFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_dailymanage, container, false);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.menu_dailymanage, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId=item.getItemId();
		if(itemId==R.id.menu_daily_add){
			
		}else if(itemId==R.id.menu_daily_search){
			
		}else{
			//null
		}
		return super.onOptionsItemSelected(item);
	}
	
}
