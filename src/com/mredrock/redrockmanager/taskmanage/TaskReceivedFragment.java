package com.mredrock.redrockmanager.taskmanage;

import com.mredrock.redrockmanager.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskReceivedFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.frag_task_received, container,false);
	}
	

}
