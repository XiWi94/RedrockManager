package com.mredrock.redrockmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mredrock.redrockmanager.app.MainApplication;
import com.mredrock.redrockmanager.communezone.CommuneZoneFragment;
import com.mredrock.redrockmanager.util.AppUtil;
//TODO 当进入其他activity，保存此时位于第几个fragment,返回时直接退到该fragment。如果默认没有对应模块的fragment,如登录，就返回默认的fragment.
public class MainActivity extends Activity{
//	private final static String TAG="MainActivity";
	//TODO 
	private final static int[] drawableId={R.drawable.ic_action_event,R.drawable.menu_home,R.drawable.menu_calendar,R.drawable.menu_profile,R.drawable.ic_logo,R.drawable.menu_settings};
	private DrawerLayout drawerLayout;
	
	private ListView listView;
	private FragmentManager fm;
	private DrawerMenuListener drawerListener;
	int flag=DrawerMenuListener.COMMUNEZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setAdapter();
		initFragment();
		setListener();
	}

	private void initView() {
		drawerLayout=(DrawerLayout)findViewById(R.id.layout_drawer);
		listView=(ListView)findViewById(R.id.list_menu);
		fm=getFragmentManager();
	}
	
	private void setAdapter() {
		addHeader();
		listView.setAdapter(new SimpleAdapter(this, 
											getData(),
											R.layout.drawer_item, 
											new String[]{"icon","text"}, 
											new int[]{R.id.icon_drawer_item,R.id.text_drawer_item}));
	}

	private void initFragment(){
		fm.beginTransaction().replace(R.id.layout_content, new CommuneZoneFragment()).commit();
	}
	
	private List<HashMap<String, Object>> getData() {
		String[] textItem=getResources().getStringArray(R.array.drawer_item);
		
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map;
		for(int i=0;i<textItem.length;i++){
			map=new HashMap<String,Object>();
			map.put("icon", drawableId[i]);
			map.put("text", textItem[i]);
			list.add(map);
		}
		return list;
	}

	private void addHeader() {
		View view=getLayoutInflater().inflate(R.layout.drawer_item_userinfo, listView, false);
		listView.addHeaderView(view);
		
	}
	
	private void setListener() {
		drawerListener=new DrawerMenuListener(this,drawerLayout);
		listView.setOnItemClickListener(drawerListener);
		
		if(!MainApplication.sp.getString(AppUtil.KEYISFIRSTSTART, "").equals("no")){
			drawerLayout.openDrawer(Gravity.START);
			MainApplication.sp.edit().putString(AppUtil.KEYISFIRSTSTART, "no").commit();
		}
//		Toast.makeText(this,sp.getString(AppUtil.keyIsFirstStart, "") ,Toast.LENGTH_SHORT).show();
		
	}
}