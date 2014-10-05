package com.mredrock.redrockmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.mredrock.redrockmanager.communezone.CommuneZoneFragment;
import com.mredrock.redrockmanager.dailymanage.DailyManageFragment;
import com.mredrock.redrockmanager.launch.LoginActivity;
import com.mredrock.redrockmanager.personmanage.PersonManagerFragment;
import com.mredrock.redrockmanager.setting.SettingFragment;
import com.mredrock.redrockmanager.taskmanage.TaskManageFragment;
import com.mredrock.redrockmanager.userinfo.UserInfoFragment;

public class DrawerMenuListener implements OnItemClickListener{

	private Activity context;
	private DrawerLayout drawerLayout;
	private ArrayList<Fragment> fragmentList;
	
	public DrawerMenuListener(Activity context,DrawerLayout drawerLayout){
		this.context=context;
		this.drawerLayout=drawerLayout;
		fragmentList=new ArrayList<>();
		addFragment();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Toast.makeText(context,arg2+"", Toast.LENGTH_SHORT).show();
		FragmentManager fm=context.getFragmentManager();
		switch(arg2){
		case 0:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(0)).commit();
			break;
		case 1:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(1)).commit();
		 	break;
		case 2:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(2)).commit();
			break;
		case 3:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(3)).commit();
			break;
		case 4:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(4)).commit();
			break;
		case 5:
			Intent intent=new Intent(context,LoginActivity.class);
			context.startActivity(intent);
			break;
		case 6:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(5)).commit();
			break;
		}
		drawerLayout.closeDrawer(Gravity.START);
	}
	
	private void addFragment(){		
		
		Fragment fragment=new UserInfoFragment();
		fragmentList.add(fragment);
		
		fragment=new TaskManageFragment();
		fragmentList.add(fragment);
		
		fragment=new CommuneZoneFragment();
		fragmentList.add(fragment);
		
		fragment=new DailyManageFragment();
		fragmentList.add(fragment);
		
		fragment=new PersonManagerFragment();
		fragmentList.add(fragment);
		
		fragment=new SettingFragment();
		fragmentList.add(fragment);

	}
}
