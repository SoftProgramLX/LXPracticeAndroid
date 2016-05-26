package com.lxexample.lxaskedwar;

import java.util.ArrayList;
import java.util.List;

import Util.WarMemberInfoModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AskedWarTeamAdapter extends BaseAdapter {

	private Context context;
	private List<WarMemberInfoModel> infoList = new ArrayList<WarMemberInfoModel>();
	
	public AskedWarTeamAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infoList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return infoList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LXAskedWarHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.asked_war_team_item, null);
			holder = new LXAskedWarHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (LXAskedWarHolder) convertView.getTag();
		}

		holder.updateUI(position);
		
		return convertView;
	}


	/**
	 * setter and getter
	 **************************************************************************************/
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	public List<WarMemberInfoModel> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<WarMemberInfoModel> infoList) {
		this.infoList = infoList;
	}

	/**
	 * listview缓存的holder类
	 **************************************************************************************/
	public class LXAskedWarHolder {
		TextView nameTextView;
		ImageView headerImg;

		public LXAskedWarHolder(View convertView) {
			nameTextView = (TextView) convertView.findViewById(R.id.text_war_team_member_name);
			headerImg = (ImageView) convertView.findViewById(R.id.img_war_team_member_header);
		}
		
		public void updateUI(int position) {
			WarMemberInfoModel model = infoList.get(position);
			nameTextView.setText(model.nameString);
		}
	}
}
