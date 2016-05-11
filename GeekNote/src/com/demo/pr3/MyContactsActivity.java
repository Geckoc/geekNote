package com.demo.pr3;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.UserTable.ContactsTable;
import com.UserTable.User;
/******************** (C) COPYRIGHT 2012********************
*主界面	  
************************************************************/
public class MyContactsActivity extends Activity {
	//结果列表
	private  ListView listView;
	//ListView 列表适配器
	private BaseAdapter  listViewAdapter;
	//通讯录用户
	private  User users[];
	//当前选择
	private  int selecteItem=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("记事本");
        
        listView = (ListView) findViewById(R.id.listView);  
        loadContacts();
    }
    /**
     * 加载联系人列表
     */
    private void  loadContacts()
    {
    	 //获取所以通讯录联系人
    	ContactsTable ct=new ContactsTable(this);
    	users=ct.getAllUser();
    	//Log.w("test", String.valueOf(users.length).toString());
    	
    	
    	//listView列表现实适配器
        listViewAdapter=new BaseAdapter() {  
              @Override  
              public View getView(int position, 
            		  View convertView, ViewGroup parent) {  
              	if(convertView==null)
              	{
              	  TextView textView = 
              			new TextView(MyContactsActivity.this);  
  	            	textView.setTextSize(22);
  	            	convertView=textView;
              	}
              	String dates=users[position].getDates()==null?""
						:users[position].getDates();
              	((TextView)convertView).setText(users[position]
						.getName()+"---"+dates);
              	if(position==selecteItem)
              	{
              		 convertView.setBackgroundColor(Color.DKGRAY);
              	}else
              	{
              		 convertView.setBackgroundColor(0);
              	}
                return convertView;  
              }  
              @Override  
              public long getItemId(int position) {  
                  return position;  
              }  
              @Override  
              public Object getItem(int position) {  
                  return users[position];  
              }  
              @Override  
              public int getCount() {  
                  return users.length;  
              }  
          };
          //设置listView控件的适配器
          listView.setAdapter(listViewAdapter);
          listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
		   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//记录点击列
				selecteItem=arg2;
                 //刷新列表
				listViewAdapter.notifyDataSetChanged();
		  }
        	  
		});
    }
    
    /**
	 * 创建菜单
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "添加");
	     menu.add(Menu.NONE, 2, Menu.NONE, "编辑");
	     menu.add(Menu.NONE, 3, Menu.NONE, "查看信息");
	     menu.add(Menu.NONE, 4, Menu.NONE, "删除");
	     menu.add(Menu.NONE, 5, Menu.NONE, "查询");
	     menu.add(Menu.NONE, 7, Menu.NONE, "退出");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * 菜单事件
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://添加
            Intent intent = new Intent(MyContactsActivity.this,AddContactsActivity.class);
       	    startActivity(intent);
            break;
        case 2://编辑
        	if(users[selecteItem].getId_DB()>0)//根据数据库ID判断当前记录是否可以操作
        	{
        	  intent = new Intent(MyContactsActivity.this,UpdateContactsActivity.class);
        	  intent.putExtra("user_ID", users[selecteItem].getId_DB());
       		  startActivity(intent);
        	}else
        	{
        		Toast.makeText(this, "无结果记录，无法操作!",Toast.LENGTH_SHORT).show();
        	}
        	break;
        case 3://查看信息
        	if(users[selecteItem].getId_DB()>0)
        	{
        	 intent = new Intent(MyContactsActivity.this,ContactsMessageActivity.class);
      	     intent.putExtra("user_ID", users[selecteItem].getId_DB());
     		 startActivity(intent);
        	}else
        	{
        		Toast.makeText(this, "无结果记录，无法操作!",Toast.LENGTH_SHORT).show();
        	}
            break;
        case 4://删除
        	if(users[selecteItem].getId_DB()>0)
        	{
        	 delete();
        	}else
        	{
        		Toast.makeText(this, "无结果记录，无法操作!",Toast.LENGTH_SHORT).show();
        	}
        	break;
        case 5://查询
        	 new FindDialog(this).show();
        	 break;
        case 7://退出
        	quit();
            break;
         default:
             break;
        }
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//重新加载数据
		ContactsTable ct=new ContactsTable(this);
    	users=ct.getAllUser();
    	//刷新列表
    	listViewAdapter.notifyDataSetChanged();
	}
	/**
	* 查询
	*/
    public   class FindDialog extends Dialog{
	
		public FindDialog(Context context) {
			super(context);
			
		}
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.find);
			setTitle("记事本查询");
			Button find=(Button)findViewById(R.id.find);
			Button cancel=(Button)findViewById(R.id.cancel);
			find.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText value=(EditText)findViewById(R.id.value);
			ContactsTable ct=new ContactsTable(MyContactsActivity.this);
					users=ct.findUserByKey(value.getText().toString());
					for(int i=0;i<users.length;i++)
					{
					 System.out.println("标题是"+users[i].getName());
					}
					listViewAdapter.notifyDataSetChanged();
		        	selecteItem=0;
		        	dismiss();
				}
			});
			cancel.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dismiss();
				}
			 });
		}
    }
    /**
     * 删除
     */
    public  void delete()
    {
    	Builder alert = new AlertDialog.Builder(this); 
		alert.setTitle("系统信息");
		alert.setMessage("是否要删除？");
		alert.setPositiveButton("是", 
		new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			   ContactsTable ct=new ContactsTable(MyContactsActivity.this);
				 //删除
			   if(ct.deleteByUser(users[selecteItem]))
			   {
			    	//重新获取数据
		            users=ct.getAllUser();
		        	//刷新列表
		        	listViewAdapter.notifyDataSetChanged();
		        	selecteItem=0;
		        	Toast.makeText(MyContactsActivity.this, "删除成功！",
		        	Toast.LENGTH_SHORT).show();
			   }else
			   {
			    	Toast.makeText(MyContactsActivity.this, "删除失败！", 
			    	Toast.LENGTH_SHORT).show();
			   }
		   }
		});
		alert.setNegativeButton("否",
		new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int whichButton) {
	
		   }
		});
		alert.show();
    }
    public void quit(){
    	Builder alert = new AlertDialog.Builder(this); 
		alert.setTitle("温馨提示");
		alert.setMessage("您确定要退出？");
		alert.setPositiveButton("退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				
			}
		}); 
		alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			
				
			}
		});
    	alert.show();
	
    }
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			quit();
			return true;
		}
		return false;
	}
    
    
    
    
    
}