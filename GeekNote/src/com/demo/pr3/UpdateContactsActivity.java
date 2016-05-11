package com.demo.pr3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.UserTable.ContactsTable;
import com.UserTable.User;
/******************** (C) COPYRIGHT 2012********************
*修改号码记录操作界面													
************************************************************/
public class UpdateContactsActivity extends Activity {
    /** Called when the activity is first created. */
	//标题
	private  EditText nameEditText;  
	//内容
	private  EditText stuEditText;
	//时间
	private  EditText datesEditText;
	//修改记事
	private  User user; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("修改联系人");
        
        //从已设置的页面布局查找对应的控件
        nameEditText=(EditText)findViewById(R.id.name); 
        stuEditText=(EditText)findViewById(R.id.stu); 
        datesEditText=(EditText)findViewById(R.id.dates); 

        //将要修改的联系人数据赋值到用户界面显示
        Bundle localBundle = getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByID(id);
        nameEditText.setText(user.getName());
        stuEditText.setText(user.getStu());
        datesEditText.setText(user.getDates());
    }
    /**
	 * 创建菜单
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "保存");
	     menu.add(Menu.NONE, 2, Menu.NONE, "返回");
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
        		user.setName(nameEditText.getText().toString());
        		user.setStu(stuEditText.getText().toString());
        		user.setDates(datesEditText.getText().toString());
        		ContactsTable ct=
    					new ContactsTable(UpdateContactsActivity.this);
    				//修改数据库记事本内容
    			if(ct.updateUser(user))
    			{
    				Toast.makeText(UpdateContactsActivity.this, "修改成功！", 
    				Toast.LENGTH_SHORT).show();
    			}else
    			{
    				Toast.makeText(UpdateContactsActivity.this, "修改失败！",
    				Toast.LENGTH_SHORT).show();
    			}
    		}else
    		{
    			Toast.makeText(UpdateContactsActivity.this, "数据不能为空！",
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
}