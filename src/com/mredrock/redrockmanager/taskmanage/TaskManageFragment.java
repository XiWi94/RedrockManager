package com.mredrock.redrockmanager.taskmanage;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.redrockmanager.R;

@SuppressLint("NewApi")
public class TaskManageFragment extends Fragment{
	private Menu menu;
	private FragmentManager fm;
	private ArrayList<Fragment> alist;
	private Activity context;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context=activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		initFragment();
		fm=getChildFragmentManager();
//		fm=getActivity().getFragmentManager();
		fm.beginTransaction().replace(R.id.frag_task_container, alist.get(0)).commit();
		return inflater.inflate(R.layout.fragment_taskmanage, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
				
	}
		
	private void initFragment() {
		alist=new ArrayList<>();
		Fragment fragment=new TaskReceivedFragment();
		alist.add(fragment);
		fragment=new TaskPushedFragment();
		alist.add(fragment);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_taskmanage, menu);
		this.menu=menu;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId=item.getItemId();
		if(itemId==R.id.menu_pushed){
			
			item.setVisible(false);
			(menu.findItem(R.id.menu_received)).setVisible(true);
			
			fm.beginTransaction().replace(R.id.frag_task_container, alist.get(PUSHED)).commit();
		}else if(itemId==R.id.menu_received){
			item.setVisible(false);
			(menu.findItem(R.id.menu_pushed)).setVisible(true);
			fm.beginTransaction().replace(R.id.frag_task_container, alist.get(RECEIVE)).commit();
		}else if(itemId==R.id.menu_jpush){
			
			Intent intent=new Intent(context,JPushActivity.class);
			context.startActivity(intent);
		}else{
			//null
		}
//		fm.executePendingTransactions();
		return super.onOptionsItemSelected(item);
	}
	
	public static final int RECEIVE=0;
	public static final int PUSHED=1;
	public static final int JPUSH=2;
	
}
