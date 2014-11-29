package com.mredrock.redrockmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
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
	public ArrayList<Fragment> fragmentList;
/** 
 * 定义点击DrawerMenu的监听
 * */	
	public DrawerMenuListener(Activity context,DrawerLayout drawerLayout){
		this.context=context;
		this.drawerLayout=drawerLayout;
		fragmentList=new ArrayList<>();
		addFragment();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Toast.makeText(context,arg2+"", Toast.LENGTH_SHORT).show();
		context.invalidateOptionsMenu();
		Log.i("Main", "invail");
		FragmentManager fm=context.getFragmentManager();
		switch(arg2){
		case USERINFO:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(USERINFO)).commit();
			break;
		case TASKMANA:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(TASKMANA)).commit();
		 	break;
		case COMMUNEZ:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(COMMUNEZ)).commit();
			break;
		case DAILYMAN:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(DAILYMAN)).commit();
			break;
		case PERSONMA:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(PERSONMA)).commit();
			break;
		case SETTING:
			Intent intent=new Intent(context,LoginActivity.class);
			context.startActivity(intent);
			break;
		case SETTING+1:
			fm.beginTransaction().replace(R.id.layout_content, fragmentList.get(SETTING)).commit();
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
	
	public final static int USERINFO=0;
	public final static int TASKMANA=1;
	public final static int COMMUNEZ=2;
	public final static int DAILYMAN=3;
	public final static int PERSONMA=4;
	public final static int SETTING =5;
	
	
}
