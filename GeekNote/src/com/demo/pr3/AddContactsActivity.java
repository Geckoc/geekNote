package com.demo.pr3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import com.UserTable.ContactsTable;
import com.UserTable.User;

/******************** (C) COPYRIGHT 2012********************
*增加号码记录操作界面
************************************************************/
public class AddContactsActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	TextView mDate;
	TextView mTime;
	static final int RG_REQUEST = 0;
	 Button BtnDate,BtnTime;
	private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

  
	//标题
	private  EditText nameEditText;  
	//内容
	private  EditText stuEditText;
	//时间
	private  EditText datesEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("编写日志 ");
        
        BtnDate=(Button)findViewById(R.id.BtnDate);
		BtnDate.setOnClickListener( this);
		BtnTime=(Button)findViewById(R.id.BtnTime);
		BtnTime.setOnClickListener( this);
        
		mDate = (TextView) findViewById(R.id.vdate);
        mTime = (TextView) findViewById(R.id.vtime);
        initTime();
        
        setDatetime();  
        //从已设置的页面布局查找对应的控件
        nameEditText=(EditText)findViewById(R.id.name); 
        stuEditText=(EditText)findViewById(R.id.stu);
        datesEditText=(EditText)findViewById(R.id.dates); 
    }
    /***
     * TEST
     */
    private void initTime(){
		Calendar c = Calendar. getInstance(TimeZone.getTimeZone("GMT+08:00"));
		mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
	}
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, false);
            case 2:
                return new DatePickerDialog(this,
                            mDateSetListener,
                            mYear, mMonth-1, mDay);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case 1:            	
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
            case 2:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth-1, mDay);
                break;
        }
    }    
	
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear+1;
                mDay = dayOfMonth;

                setDatetime();
            }
        };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                setDatetime();
            }
        };
	private void setDatetime(){
		mDate.setText(mYear+"-"+mMonth+"-"+mDay);
        mTime.setText(pad(mHour)+":"+pad(mMinute));
	}
    private static String pad(int c) {
            if (c >= 10)
                return String.valueOf(c);
            else
                return "0" + String.valueOf(c);
        }        

    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 创建菜单
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	   
	     menu.add(Menu.NONE,1, Menu.NONE, "保存");
	     menu.add(Menu.NONE,2, Menu.NONE, "返回");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * 菜单事件
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://保存
        	if(!nameEditText.getText().toString().equals(""))
			{
				User user=new User();
				user.setName(nameEditText.getText().toString());
				user.setStu(stuEditText.getText().toString());
				user.setDates(datesEditText.getText().toString());
				ContactsTable ct=new ContactsTable(AddContactsActivity.this);
				if(ct.addData(user))
				{
					Toast.makeText(AddContactsActivity.this, "添加成功！",
					Toast.LENGTH_SHORT).show();
					finish();
				}else
				{
					Toast.makeText(AddContactsActivity.this, "添加失败！",
					Toast.LENGTH_SHORT).show();
					
				}
			}else
			{
				Toast.makeText(AddContactsActivity.this, "请先输入数据！",
				Toast.LENGTH_SHORT).show();
			}
         
            break;
        case 2://返回
        	 finish();
        	break;
         default:
             break;
        }
		return super.onOptionsItemSelected(item);
	}
@Override
public void onClick(View v) {
	 if (v.equals(BtnTime)){
		showDialog(1);
	} else if (v.equals(BtnDate)){
		showDialog(2);
	}
}
}