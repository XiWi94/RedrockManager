package com.mredrock.redrockmanager.taskmanage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mredrock.redrockmanager.R;

public class JPushActivity extends FragmentActivity{

	private TextView tvPushRange;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jpush);
		initView();
	}
	private void initView() {
		tvPushRange=(TextView)findViewById(R.id.tv_jpush_range);
		tvPushRange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				JPushDialog jd=new JPushDialog();
				FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				jd.show(ft,"jpush");
			}
		});
	}
	

	
}
