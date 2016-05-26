package com.lxexample.lxaskedwar;

import java.util.Calendar;

import Util.TimeModel;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class PopSelectStartTimeDialog {

	public static void selectStratTime(final MainActivity contet) {
		View view = View.inflate(contet, R.layout.pop_selecet_start_time, null);
		final DatePicker datePicker = (DatePicker) view
				.findViewById(R.id.new_act_date_picker);
		final TimePicker timePicker = (TimePicker) view
				.findViewById(R.id.new_act_time_picker);

		final TimeModel timeModel = new TimeModel();
		TimeModel contentModel = contet.getTimeModel();
		if (contentModel != null) {
			timeModel.year = contentModel.year;
			timeModel.month = (contentModel.month+12-1)%12;//contentModel.month-1;//
			timeModel.day =  contentModel.day;
			timeModel.hour = contentModel.hour;
			timeModel.minute = contentModel.minute;
			Log.e("oldtime", timeModel.getTimeStr());
		} else {
			final Calendar c = Calendar.getInstance();
			timeModel.year = c.get(Calendar.YEAR);
			timeModel.month = c.get(Calendar.MONTH);
			timeModel.day = c.get(Calendar.DAY_OF_MONTH);
			timeModel.hour = c.get(Calendar.HOUR_OF_DAY);
			timeModel.minute = c.get(Calendar.MINUTE);
		}
		datePicker.init(timeModel.year, timeModel.month, timeModel.day, null);

		timePicker.setIs24HourView(true);
		timePicker.setCurrentHour(timeModel.hour);
		timePicker.setCurrentMinute(timeModel.minute);

		AlertDialog.Builder builder = new AlertDialog.Builder(contet);
		builder.setView(view);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						timeModel.year = datePicker.getYear();
						timeModel.month = datePicker.getMonth()+1;
						timeModel.day = datePicker.getDayOfMonth();
						timeModel.hour = timePicker.getCurrentHour();
						timeModel.minute = timePicker.getCurrentMinute();
						contet.updateStartTime(timeModel);
					}
				});
		
		builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Log.e("quixoa", "quxiao");
			}
		});
		builder.show();
	}
}
