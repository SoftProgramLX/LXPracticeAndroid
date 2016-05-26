package com.lxexample.lxaskedwar;

import java.util.ArrayList;
import java.util.List;

import Util.LXListView;
import Util.TimeModel;
import Util.WarMemberInfoModel;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	//赛制
	private RadioButton doubleRadioButton;
	private RadioButton singleRadioButton;
	private RadioButton threeRadioButton;
	private RadioButton fiveRadioButton;
	//开始时间
	private TextView startTimetTextView;

	//红队
	private LXListView redTeamListView;
	private AskedWarTeamAdapter redAdapter;
	private Button addRedTeamButton;
	//蓝队
	private LXListView blueTeamListView;
	private AskedWarTeamAdapter blueAdapter;
	private Button addBlueTeamButton;

	//发布
	private Button rleaseButton;

	private RadioButton selectRadioButton;//记录当前选中的比赛类型
	private TimeModel timeModel = new TimeModel();
	private ArrayList<ArrayList<WarMemberInfoModel>> allRedMembers = new ArrayList<ArrayList<WarMemberInfoModel>>();
	private ArrayList<ArrayList<WarMemberInfoModel>> allBlueMembers = new ArrayList<ArrayList<WarMemberInfoModel>>();
	private List<WarMemberInfoModel> selectRedMembers = new ArrayList<WarMemberInfoModel>();
	private List<WarMemberInfoModel> selectBlueMembers = new ArrayList<WarMemberInfoModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initSetup();
		downLoadData();
		downLoadData2();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		redAdapter.notifyDataSetChanged();
		blueAdapter.notifyDataSetChanged();
	}

	private void initViews() {
		doubleRadioButton = (RadioButton) findViewById(R.id.radiob_war_doubles);
		singleRadioButton = (RadioButton) findViewById(R.id.radiob_war_singles);
		threeRadioButton = (RadioButton) findViewById(R.id.radiob_war_3v3);
		fiveRadioButton = (RadioButton) findViewById(R.id.radiob_war_5v5);

		startTimetTextView = (TextView) findViewById(R.id.text_askedWar_start_time);
		
		redTeamListView = (LXListView) findViewById(R.id.listView_red_team);
		addRedTeamButton = (Button) findViewById(R.id.btn_add_red_team);

		blueTeamListView = (LXListView) findViewById(R.id.listView_blue_team);
		addBlueTeamButton = (Button) findViewById(R.id.btn_add_blue_team);
		
		rleaseButton = (Button) findViewById(R.id.btn_release_asked_war);
	}

	private void initSetup() {

		selectRedMembers.add(new WarMemberInfoModel("1"));
		selectBlueMembers.add(new WarMemberInfoModel("2"));
		
		doubleRadioButton.setSelected(true);
		selectRadioButton = doubleRadioButton;
		doubleRadioButton.setOnClickListener(this);
		singleRadioButton.setOnClickListener(this);
		threeRadioButton.setOnClickListener(this);
		fiveRadioButton.setOnClickListener(this);

		startTimetTextView.setOnClickListener(this);
		startTimetTextView.setText(timeModel.getTimeStr());

		redAdapter = new AskedWarTeamAdapter(this);
		redTeamListView.setAdapter(redAdapter);
		redAdapter.setInfoList(selectRedMembers);
		addRedTeamButton.setOnClickListener(this);
		
		blueAdapter = new AskedWarTeamAdapter(this);
		blueTeamListView.setAdapter(blueAdapter);
		blueAdapter.setInfoList(selectBlueMembers);
		addBlueTeamButton.setOnClickListener(this);
		
		rleaseButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.radiob_war_doubles:
			setMatchTypeWithRadio(doubleRadioButton);
			break;

		case R.id.radiob_war_singles:
			setMatchTypeWithRadio(singleRadioButton);
			break;

		case R.id.radiob_war_3v3:
			setMatchTypeWithRadio(threeRadioButton);
			break;

		case R.id.radiob_war_5v5:
			setMatchTypeWithRadio(fiveRadioButton);
			break;
			
		case R.id.btn_add_red_team:
			PopTeamDialog redDialog = new PopTeamDialog(MainActivity.this);
			redDialog.setInfoList(allRedMembers);
			redDialog.setHeaderList(downLoadHeaderList());
			redDialog.setSelectMembers(selectRedMembers);
			redDialog.showDialogLayout(MainActivity.this, 1);
			break;

		case R.id.btn_add_blue_team:
			PopTeamDialog blueDialog = new PopTeamDialog(MainActivity.this);
			blueDialog.setInfoList(allBlueMembers);
			blueDialog.setHeaderList(downLoadHeaderList());
			blueDialog.setSelectMembers(selectBlueMembers);
			blueDialog.showDialogLayout(MainActivity.this, 2);
			break;

		case R.id.text_askedWar_start_time:
			PopSelectStartTimeDialog.selectStratTime(this);
			break;

		case R.id.btn_release_asked_war:
			break;

		default:
			break;
		}
	}

	private void setMatchTypeWithRadio(RadioButton radioButton) {
		if (!selectRadioButton.equals(radioButton)) {
			selectRadioButton.setSelected(false);
			radioButton.setSelected(true);
			selectRadioButton = radioButton;
			
		}
	}
	
	public void setSelectMembers(List<WarMemberInfoModel> selectMember, int type) {
		if (selectMember != null) {
			if (type == 1) {
				this.selectRedMembers = selectMember;
				redAdapter.setInfoList(this.selectRedMembers);
				redAdapter.notifyDataSetChanged();
			} else {
				this.selectBlueMembers = selectMember;
				blueAdapter.setInfoList(this.selectBlueMembers);
				blueAdapter.notifyDataSetChanged();
			}
		}
	}

	public void updateStartTime(TimeModel timeModel) {
		this.timeModel = timeModel;

		Log.e("updatetime", timeModel.getTimeStr());
		startTimetTextView.setText(timeModel.getTimeStr());
	}

	public AskedWarTeamAdapter getRedAdapter() {
		return redAdapter;
	}

	public TimeModel getTimeModel() {
		return timeModel;
	}

	private void downLoadData() {
		
		//组合listview所需二维数据
		ArrayList<WarMemberInfoModel> itemArrayList0 = new ArrayList<WarMemberInfoModel>();
		itemArrayList0.add(new WarMemberInfoModel("0"));
		allRedMembers.add(itemArrayList0);
		
		ArrayList<WarMemberInfoModel> itemArrayList1 = new ArrayList<WarMemberInfoModel>();
		WarMemberInfoModel model = new WarMemberInfoModel("1");
		model.checkBoolean = true;
		itemArrayList1.add(model);
		allRedMembers.add(itemArrayList1);
		
		ArrayList<WarMemberInfoModel> itemArrayList2 = new ArrayList<WarMemberInfoModel>();
		itemArrayList2.add(new WarMemberInfoModel("2"));
		itemArrayList2.add(new WarMemberInfoModel("22"));
		allRedMembers.add(itemArrayList2);

		ArrayList<WarMemberInfoModel> itemArrayList3 = new ArrayList<WarMemberInfoModel>();
		itemArrayList3.add(new WarMemberInfoModel("3"));
		itemArrayList3.add(new WarMemberInfoModel("33"));
		itemArrayList3.add(new WarMemberInfoModel("333"));
		allRedMembers.add(itemArrayList3);

		ArrayList<WarMemberInfoModel> itemArrayList4 = new ArrayList<WarMemberInfoModel>();
		itemArrayList4.add(new WarMemberInfoModel("4"));
		itemArrayList4.add(new WarMemberInfoModel("44"));
		itemArrayList4.add(new WarMemberInfoModel("444"));
		itemArrayList4.add(new WarMemberInfoModel("4444"));
		allRedMembers.add(itemArrayList4);

		ArrayList<WarMemberInfoModel> itemArrayList5 = new ArrayList<WarMemberInfoModel>();
		itemArrayList5.add(new WarMemberInfoModel("5"));
		itemArrayList5.add(new WarMemberInfoModel("55"));
		itemArrayList5.add(new WarMemberInfoModel("555"));
		itemArrayList5.add(new WarMemberInfoModel("5555"));
		itemArrayList5.add(new WarMemberInfoModel("55555"));
		allRedMembers.add(itemArrayList5);
		
	}
	private void downLoadData2() {
		
		//组合listview所需二维数据
		ArrayList<WarMemberInfoModel> itemArrayList0 = new ArrayList<WarMemberInfoModel>();
		itemArrayList0.add(new WarMemberInfoModel("0"));
		allBlueMembers.add(itemArrayList0);
		
		ArrayList<WarMemberInfoModel> itemArrayList1 = new ArrayList<WarMemberInfoModel>();
		itemArrayList1.add(new WarMemberInfoModel("1"));
		allBlueMembers.add(itemArrayList1);
		
		ArrayList<WarMemberInfoModel> itemArrayList2 = new ArrayList<WarMemberInfoModel>();
//		itemArrayList2.add(new WarMemberInfoModel("2"));
		WarMemberInfoModel model = new WarMemberInfoModel("2");
		model.checkBoolean = true;
		itemArrayList2.add(model);
		itemArrayList2.add(new WarMemberInfoModel("22"));
		allBlueMembers.add(itemArrayList2);

		ArrayList<WarMemberInfoModel> itemArrayList3 = new ArrayList<WarMemberInfoModel>();
		itemArrayList3.add(new WarMemberInfoModel("3"));
		itemArrayList3.add(new WarMemberInfoModel("33"));
		itemArrayList3.add(new WarMemberInfoModel("333"));
		allBlueMembers.add(itemArrayList3);

		ArrayList<WarMemberInfoModel> itemArrayList4 = new ArrayList<WarMemberInfoModel>();
		itemArrayList4.add(new WarMemberInfoModel("4"));
		itemArrayList4.add(new WarMemberInfoModel("44"));
		itemArrayList4.add(new WarMemberInfoModel("444"));
		itemArrayList4.add(new WarMemberInfoModel("4444"));
		allBlueMembers.add(itemArrayList4);

		ArrayList<WarMemberInfoModel> itemArrayList5 = new ArrayList<WarMemberInfoModel>();
		itemArrayList5.add(new WarMemberInfoModel("5"));
		itemArrayList5.add(new WarMemberInfoModel("55"));
		itemArrayList5.add(new WarMemberInfoModel("555"));
		itemArrayList5.add(new WarMemberInfoModel("5555"));
		itemArrayList5.add(new WarMemberInfoModel("55555"));
		allBlueMembers.add(itemArrayList5);
		
	}
	
	private List<String> downLoadHeaderList() {
		List<String> headerList = new ArrayList<String>();
		headerList.add("A");
		headerList.add("B");
		headerList.add("C");
		headerList.add("D");
		headerList.add("E");
		headerList.add("F");
		return headerList;
	}
}
