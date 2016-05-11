package com.demo.pr3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.UserTable.ContactsTable;
import com.UserTable.User;
/******************** (C) COPYRIGHT 2012********************
*显示联系人界面 
************************************************************/
public class ContactsMessageActivity extends Activity {
    /** Called when the activity is first created. */
	//标题
	private  TextView nameTextView;  
	//内容
	private  TextView stuTextView;
	//时间
	private  TextView datesTextView;

	//修改的事件
	private  User user; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        setTitle("记事本信息");
        
        //从已设置的页面布局查找对应的控件
        nameTextView=(TextView)findViewById(R.id.name); 
        stuTextView=(TextView)findViewById(R.id.stu); 
        datesTextView=(TextView)findViewById(R.id.dates); 
        
        //将要编辑记事本数据付值到用户界面显示
        Bundle localBundle = getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByID(id);
        nameTextView.setText("标题:"+user.getName());
        stuTextView.setText("内容:"+user.getStu());
        datesTextView.setText("时间:"+user.getDates());

    }
    /**
	 * 创建菜单
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "返回");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * 菜单事件
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://返回
           finish();
           break;
         default:
             break;
        }
		return super.onOptionsItemSelected(item);
	}
}