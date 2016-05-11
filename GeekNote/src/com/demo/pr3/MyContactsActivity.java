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
*������	  
************************************************************/
public class MyContactsActivity extends Activity {
	//����б�
	private  ListView listView;
	//ListView �б�������
	private BaseAdapter  listViewAdapter;
	//ͨѶ¼�û�
	private  User users[];
	//��ǰѡ��
	private  int selecteItem=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("���±�");
        
        listView = (ListView) findViewById(R.id.listView);  
        loadContacts();
    }
    /**
     * ������ϵ���б�
     */
    private void  loadContacts()
    {
    	 //��ȡ����ͨѶ¼��ϵ��
    	ContactsTable ct=new ContactsTable(this);
    	users=ct.getAllUser();
    	//Log.w("test", String.valueOf(users.length).toString());
    	
    	
    	//listView�б���ʵ������
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
          //����listView�ؼ���������
          listView.setAdapter(listViewAdapter);
          listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
		   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//��¼�����
				selecteItem=arg2;
                 //ˢ���б�
				listViewAdapter.notifyDataSetChanged();
		  }
        	  
		});
    }
    
    /**
	 * �����˵�
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "���");
	     menu.add(Menu.NONE, 2, Menu.NONE, "�༭");
	     menu.add(Menu.NONE, 3, Menu.NONE, "�鿴��Ϣ");
	     menu.add(Menu.NONE, 4, Menu.NONE, "ɾ��");
	     menu.add(Menu.NONE, 5, Menu.NONE, "��ѯ");
	     menu.add(Menu.NONE, 7, Menu.NONE, "�˳�");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * �˵��¼�
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://���
            Intent intent = new Intent(MyContactsActivity.this,AddContactsActivity.class);
       	    startActivity(intent);
            break;
        case 2://�༭
        	if(users[selecteItem].getId_DB()>0)//�������ݿ�ID�жϵ�ǰ��¼�Ƿ���Բ���
        	{
        	  intent = new Intent(MyContactsActivity.this,UpdateContactsActivity.class);
        	  intent.putExtra("user_ID", users[selecteItem].getId_DB());
       		  startActivity(intent);
        	}else
        	{
        		Toast.makeText(this, "�޽����¼���޷�����!",Toast.LENGTH_SHORT).show();
        	}
        	break;
        case 3://�鿴��Ϣ
        	if(users[selecteItem].getId_DB()>0)
        	{
        	 intent = new Intent(MyContactsActivity.this,ContactsMessageActivity.class);
      	     intent.putExtra("user_ID", users[selecteItem].getId_DB());
     		 startActivity(intent);
        	}else
        	{
        		Toast.makeText(this, "�޽����¼���޷�����!",Toast.LENGTH_SHORT).show();
        	}
            break;
        case 4://ɾ��
        	if(users[selecteItem].getId_DB()>0)
        	{
        	 delete();
        	}else
        	{
        		Toast.makeText(this, "�޽����¼���޷�����!",Toast.LENGTH_SHORT).show();
        	}
        	break;
        case 5://��ѯ
        	 new FindDialog(this).show();
        	 break;
        case 7://�˳�
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
		//���¼�������
		ContactsTable ct=new ContactsTable(this);
    	users=ct.getAllUser();
    	//ˢ���б�
    	listViewAdapter.notifyDataSetChanged();
	}
	/**
	* ��ѯ
	*/
    public   class FindDialog extends Dialog{
	
		public FindDialog(Context context) {
			super(context);
			
		}
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.find);
			setTitle("���±���ѯ");
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
					 System.out.println("������"+users[i].getName());
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
     * ɾ��
     */
    public  void delete()
    {
    	Builder alert = new AlertDialog.Builder(this); 
		alert.setTitle("ϵͳ��Ϣ");
		alert.setMessage("�Ƿ�Ҫɾ����");
		alert.setPositiveButton("��", 
		new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			   ContactsTable ct=new ContactsTable(MyContactsActivity.this);
				 //ɾ��
			   if(ct.deleteByUser(users[selecteItem]))
			   {
			    	//���»�ȡ����
		            users=ct.getAllUser();
		        	//ˢ���б�
		        	listViewAdapter.notifyDataSetChanged();
		        	selecteItem=0;
		        	Toast.makeText(MyContactsActivity.this, "ɾ���ɹ���",
		        	Toast.LENGTH_SHORT).show();
			   }else
			   {
			    	Toast.makeText(MyContactsActivity.this, "ɾ��ʧ�ܣ�", 
			    	Toast.LENGTH_SHORT).show();
			   }
		   }
		});
		alert.setNegativeButton("��",
		new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int whichButton) {
	
		   }
		});
		alert.show();
    }
    public void quit(){
    	Builder alert = new AlertDialog.Builder(this); 
		alert.setTitle("��ܰ��ʾ");
		alert.setMessage("��ȷ��Ҫ�˳���");
		alert.setPositiveButton("�˳�", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				
			}
		}); 
		alert.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
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