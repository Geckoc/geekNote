package com.demo.pr3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.UserTable.User;
import com.UserTable.UserContactsTable;
import com.demo.pr3.R;

public class UserAddActivity  extends Activity {
	    //用户账号
		private  EditText usernameEditText;  
		//内密码
		private  EditText userpwdEditText;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        setTitle("注册账号");
	        //从已设置的页面布局查找对应的控件
	        usernameEditText=(EditText)findViewById(R.id.username); 
	        userpwdEditText=(EditText)findViewById(R.id.userpwd);
	        Button register=(Button)findViewById(R.id.register);
	        register.setOnClickListener(new OnClickListener() {		
	    		public void onClick(View v) {
	    			if(!usernameEditText.getText().toString().equals(""))
					{
						User user=new User();
						user.setUsername(usernameEditText.getText().toString());
						user.setUserpwd(userpwdEditText.getText().toString());
						UserContactsTable ct=new UserContactsTable(UserAddActivity.this);
						if(ct.addData(user))
						{
							Toast.makeText(UserAddActivity.this, "注册成功！",
							Toast.LENGTH_SHORT).show();
							finish();
						}else
						{
							Toast.makeText(UserAddActivity.this, "注册失败！",
							Toast.LENGTH_SHORT).show();
							
						}
					}else
					{
						Toast.makeText(UserAddActivity.this, "请先输入数据！",
						Toast.LENGTH_SHORT).show();
					}	
	    		} 
	    	});
	    }
	    /**
		 * 创建菜单
		 */
		public boolean onCreateOptionsMenu(Menu menu) {
		   
		     menu.add(Menu.NONE,1, Menu.NONE, "注册");
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
	        	if(!usernameEditText.getText().toString().equals(""))
				{
					User user=new User();
					user.setUsername(usernameEditText.getText().toString());
					user.setUserpwd(userpwdEditText.getText().toString());
					UserContactsTable ct=new UserContactsTable(UserAddActivity.this);
					if(ct.addData(user))
					{
						Toast.makeText(UserAddActivity.this, "注册成功！",
						Toast.LENGTH_SHORT).show();
						finish();
					}else
					{
						Toast.makeText(UserAddActivity.this, "注册失败！",
						Toast.LENGTH_SHORT).show();
						
					}
				}else
				{
					Toast.makeText(UserAddActivity.this, "请先输入数据！",
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
