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
*�޸ĺ����¼��������													
************************************************************/
public class UpdateContactsActivity extends Activity {
    /** Called when the activity is first created. */
	//����
	private  EditText nameEditText;  
	//����
	private  EditText stuEditText;
	//ʱ��
	private  EditText datesEditText;
	//�޸ļ���
	private  User user; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("�޸���ϵ��");
        
        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
        nameEditText=(EditText)findViewById(R.id.name); 
        stuEditText=(EditText)findViewById(R.id.stu); 
        datesEditText=(EditText)findViewById(R.id.dates); 

        //��Ҫ�޸ĵ���ϵ�����ݸ�ֵ���û�������ʾ
        Bundle localBundle = getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByID(id);
        nameEditText.setText(user.getName());
        stuEditText.setText(user.getStu());
        datesEditText.setText(user.getDates());
    }
    /**
	 * �����˵�
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "����");
	     menu.add(Menu.NONE, 2, Menu.NONE, "����");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * �˵��¼�
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://����
        	if(!nameEditText.getText().toString().equals(""))
    	    {
        		user.setName(nameEditText.getText().toString());
        		user.setStu(stuEditText.getText().toString());
        		user.setDates(datesEditText.getText().toString());
        		ContactsTable ct=
    					new ContactsTable(UpdateContactsActivity.this);
    				//�޸����ݿ���±�����
    			if(ct.updateUser(user))
    			{
    				Toast.makeText(UpdateContactsActivity.this, "�޸ĳɹ���", 
    				Toast.LENGTH_SHORT).show();
    			}else
    			{
    				Toast.makeText(UpdateContactsActivity.this, "�޸�ʧ�ܣ�",
    				Toast.LENGTH_SHORT).show();
    			}
    		}else
    		{
    			Toast.makeText(UpdateContactsActivity.this, "���ݲ���Ϊ�գ�",
    			Toast.LENGTH_SHORT).show();
    		}
        	break;
        case 2://����
            finish();
            break;
         default:
             break;
        }
		return super.onOptionsItemSelected(item);
	}
}