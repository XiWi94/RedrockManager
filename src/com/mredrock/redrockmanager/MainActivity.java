package com.mredrock.redrockmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mredrock.redrockmanager.app.MainApplication;
import com.mredrock.redrockmanager.communezone.CommuneZoneFragment;
import com.mredrock.redrockmanager.util.AppUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	
	private final static int[] drawableId={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

	private DrawerLayout drawerLayout;
	private ListView listView;
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
	}
	
	private void setAdapter() {
		addHeader();
		listView.setAdapter(new SimpleAdapter(this, getData(),R.layout.drawer_item, new String[]{"icon","text"}, new int[]{R.id.icon_drawer_item,R.id.text_drawer_item}));
	}

	private void initFragment(){
		this.getFragmentManager().beginTransaction().replace(R.id.layout_content, new CommuneZoneFragment()).commit();
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
		
		listView.setOnItemClickListener(new DrawerMenuListener(this,drawerLayout));
		
		if(!MainApplication.sp.getString(AppUtil.KEYISFIRSTSTART, "").equals("no")){
			drawerLayout.openDrawer(Gravity.START);
			MainApplication.sp.edit().putString(AppUtil.KEYISFIRSTSTART, "no").commit();
		}
//		Toast.makeText(this,sp.getString(AppUtil.keyIsFirstStart, "") ,Toast.LENGTH_SHORT).show();
		
	}
	
}
