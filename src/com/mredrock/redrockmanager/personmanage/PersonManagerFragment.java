package com.mredrock.redrockmanager.personmanage;

import com.mredrock.redrockmanager.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class PersonManagerFragment extends Fragment{
	private Menu menu;
	private FragmentManager fm;
	private Activity content;
	
	@Override
	public void onAttach(Activity activity) {
		content=activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		initFragment();
		fm=getChildFragmentManager();
		
		return inflater.inflate(R.layout.fragment_personmanage, container, false);
	}
	
	private void initFragment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_personmanage, menu);
		this.menu=menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId=item.getItemId();
		if(itemId==R.id.menu_student){
			item.setVisible(false);
			(menu.findItem(R.id.menu_colleague)).setVisible(true);
			
		}else if(itemId==R.id.menu_colleague){
			item.setVisible(false);
			(menu.findItem(R.id.menu_student)).setVisible(true);
			
		}else if(itemId==R.id.menu_person_search){
			
		}else{
			//null
		}
		return super.onOptionsItemSelected(item);
	}

	
}
