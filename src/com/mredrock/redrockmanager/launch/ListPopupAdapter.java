package com.mredrock.redrockmanager.launch;

import java.util.Arrays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mredrock.redrockmanager.R;
import com.mredrock.redrockmanager.app.MainApplication;
import com.mredrock.redrockmanager.mode.login.LoginPreference;
import com.mredrock.redrockmanager.util.AppUtil;

public class ListPopupAdapter extends BaseAdapter{
	private LayoutInflater layoutInflater;
	private LoginPreference[] users;
	
	public ListPopupAdapter(Context context,LoginPreference[] users){
		layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.users=users;
	}
	
	@Override
	public int getCount() {
		if(users==null)
			return 0;
		return users.length;
	}

	@Override
	public Object getItem(int arg0) {
		
		return users[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public View getView(final int position, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;
		if(arg1==null){
			arg1=layoutInflater.inflate(R.layout.popup_item_login,arg2,false);
			viewHolder=new ViewHolder();
			viewHolder.id=(TextView) arg1.findViewById(R.id.text_popup_id);
			viewHolder.name=(TextView)arg1.findViewById(R.id.text_popup_name);
			viewHolder.del=(ImageView)arg1.findViewById(R.id.icon_popup_del);
			arg1.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) arg1.getTag();
		}
//		Log.i("tag",viewHolder+"");
		Log.i("tag",((LoginPreference)getItem(position)).getUserId());
		viewHolder.id.setText(((LoginPreference)getItem(position)).getUserId());
		viewHolder.name.setText(((LoginPreference)getItem(position)).getName());
		viewHolder.del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//TODO 理想情况下，会把该账户相关的记录都删除掉。
				if(position==0){
					users=null;
					MainApplication.sp.edit().putString(AppUtil.KEYACCOUNTS, "").commit();
					notifyDataSetChanged();
				}else{
					for(int i=position;i<users.length;i++){
						
						
						users[position]=users[position+1];	
					}
					users=Arrays.copyOf(users, users.length-1);
					MainApplication.sp.edit().putString(AppUtil.KEYACCOUNTS, new Gson().toJson(users)).commit();
					notifyDataSetChanged();	
				}
				
			}
		});
		return arg1;
	}
	
	private class ViewHolder {
		public TextView id;
		public TextView name;
		public ImageView del;
	}
	

}
