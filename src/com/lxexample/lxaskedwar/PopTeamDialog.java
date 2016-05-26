package com.lxexample.lxaskedwar;

import java.util.ArrayList;
import java.util.List;

import Util.WarMemberInfoModel;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PopTeamDialog extends AlertDialog.Builder implements OnItemClickListener {

	private ListView listView;
	private View textEntryView;
	private PopTeamAdapter adapter;
	private ArrayList<ArrayList<WarMemberInfoModel>> infoList = new ArrayList<ArrayList<WarMemberInfoModel>>(); 
	private List<WarMemberInfoModel> selectMembers = new ArrayList<WarMemberInfoModel>();
	private List<String> headerList = new ArrayList<String>();

	protected PopTeamDialog(Context context) {
		super(context);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		textEntryView = inflater.inflate(R.layout.pop_team_member,
				null);
		listView = (ListView) textEntryView.findViewById(R.id.list_add_red_team_member);
		adapter = new PopTeamAdapter(context);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	void showDialogLayout(final MainActivity context, final int type) {
		adapter.setInfoList(infoList);
		adapter.setSelectMembers(selectMembers);
		adapter.setHeaderList(headerList);
		
		setCancelable(false);
		setView(textEntryView);
		
		setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				context.setSelectMembers(adapter.getSelectMembers(), type);
				Log.e("click", "1"+whichButton);
			}
		});
		setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				context.setSelectMembers(null, type);
				Log.e("click", "2"+whichButton+adapter.getSelectMembers());
			}
		});
		show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

	public void setSelectMembers(List<WarMemberInfoModel> selectMembers) {
		this.selectMembers = selectMembers;
	}

	public void setInfoList(ArrayList<ArrayList<WarMemberInfoModel>> infoList) {
		this.infoList = infoList;
	}

	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList;
	}
}
