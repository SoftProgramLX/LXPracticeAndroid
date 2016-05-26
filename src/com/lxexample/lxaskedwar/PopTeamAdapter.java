package com.lxexample.lxaskedwar;

import java.util.ArrayList;
import java.util.List;

import Custom.LXBaseAdapter;
import Custom.LXIndexPath;
import Util.WarMemberInfoModel;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class PopTeamAdapter extends LXBaseAdapter  {

	private Context context;
	private List<ArrayList<WarMemberInfoModel>> infoList = new ArrayList<ArrayList<WarMemberInfoModel>>();
	private List<String> headerList = new ArrayList<String>();
	private final int TYPE_ITEM = 0;
	private final int TYPE_HEADER_VIEW = 1;
	private List<WarMemberInfoModel> selectMembers = new ArrayList<WarMemberInfoModel>();

	public PopTeamAdapter(Context context) {
		this.context = context;
		super.setSubClass(this);//这句代码必须调用。
	}

	/**
	 * 下面的方法必须实现
	 * *************************************************************************************/
	@Override
	public int getSectionInListView() {
		if (infoList == null) {
			return 0;
		}
		return infoList.size();
	}

	@Override
	public int getCountInSection(int section) {
		ArrayList<WarMemberInfoModel> arr = infoList.get(section);
		return arr.size();
	}

	@Override
	public View getItemView(LXIndexPath indexPath, View convertView,
			ViewGroup parent) {
		LXInfoItemHolder holder = null;
		if (convertView == null) {
			convertView = createViewByType(TYPE_ITEM);
			holder = new LXInfoItemHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (LXInfoItemHolder) convertView.getTag();
		}

		holder.updateUI(indexPath);
		
		return convertView;
	}

	/**
	 * 下面的方法可选实现
	 * *************************************************************************************/
	public boolean showHeaderViewInSection(int section) {
		return true;
	}

	public View getHeaderViewInSection(int section, View convertView,
			ViewGroup parent) {

		LXInfoHeaderHolder holder = null;
		if (convertView == null) {
			holder = new LXInfoHeaderHolder();
			convertView = createViewByType(TYPE_HEADER_VIEW);
			holder.headerTextView = (TextView) convertView
					.findViewById(R.id.text_headerView);
			convertView.setTag(holder);
			
		} else {
			holder = (LXInfoHeaderHolder) convertView.getTag();
		}

		holder.headerTextView.setText(headerList.get(section));//"第"+ section +"组的头视图"
		return convertView;
	}
	
	public int getOnlyItemViewTypeCountSum() {
		return 1;
	}

	public int getOnlyItemViewType(LXIndexPath indexPath) {
		return TYPE_ITEM;
	}

	/**
	 * private method
	 * **************************************************************************************/
	private View createViewByType(int type) {
		switch (type) {
		case TYPE_ITEM:
			return LayoutInflater.from(context).inflate(
					R.layout.pop_team_item, null);

		default:
			return LayoutInflater.from(context).inflate(
					R.layout.pop_team_header, null);
		}
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

	public List<ArrayList<WarMemberInfoModel>> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<ArrayList<WarMemberInfoModel>> infoList) {
		this.infoList = infoList;
	}

	public List<String> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList;
	}
	
	public List<WarMemberInfoModel> getSelectMembers() {
		return selectMembers;
	}
	
	public void setSelectMembers(List<WarMemberInfoModel> selectMembers) {
		this.selectMembers = selectMembers;
	}

	/**
	 * 下面是listview缓存不同布局的holder类
	 **************************************************************************************/
	public class LXInfoItemHolder {
		TextView nameTextView;
		ImageView headerImg;
		CheckBox selectButton;
		
		public LXInfoItemHolder(View convertView) {
			nameTextView = (TextView) convertView
					.findViewById(R.id.text_pop_team_member_name);
			selectButton = (CheckBox)convertView.findViewById(R.id.radio_pop_team_seleect);
			headerImg = (ImageView) convertView.findViewById(R.id.img_pop_team_member_header);
			selectButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					LXIndexPath indexPath = (LXIndexPath) view.getTag();
					Log.e("selectMemberscount", selectMembers.size()+"");
					WarMemberInfoModel model = infoList.get(indexPath.section).get(indexPath.row);
					if (model.checkBoolean) {
						model.checkBoolean = false;
						Log.e("mem1", selectMembers.size()+":");
						for (int i = 0; i < selectMembers.size(); i++) {
							WarMemberInfoModel selectModel = selectMembers.get(i);
							if (selectModel.nameString.equals(model.nameString)) {
								selectMembers.remove(selectModel);
								break;
							}
						}
						Log.e("mem2", selectMembers.size()+":");
					} else {
						model.checkBoolean = true;
						selectMembers.add(model);
					}
//					Boolean contain = false;
//					for (int i = 0; i < selectMembers.size(); i++) {
//						LXIndexPath newIndexPath = selectMembers.get(i);
//						if (newIndexPath.section == indexPath.section && newIndexPath.row == indexPath.row) {
//							contain = true;
//							selectMembers.remove(newIndexPath);
//							break;
//						}
//					}
//					if (!contain) {
//						selectMembers.add(indexPath);
//					}
				}
			});
		}
		
		public void updateUI(LXIndexPath indexPath) {
			selectButton.setTag(indexPath);
			WarMemberInfoModel model = infoList.get(indexPath.section).get(indexPath.row);
//			selectButton.setChecked(false);
//			for (int i = 0; i < selectMembers.size(); i++) {
//				LXIndexPath newIndexPath = selectMembers.get(i);
//				if (newIndexPath.section == indexPath.section && newIndexPath.row == indexPath.row) {
//					selectButton.setChecked(true);
//					break;
//				}
//			}
			selectButton.setChecked(model.checkBoolean);
//			
			nameTextView.setText(model.nameString);
		}
	}

	public class LXInfoHeaderHolder {
		TextView headerTextView;
	}
}
