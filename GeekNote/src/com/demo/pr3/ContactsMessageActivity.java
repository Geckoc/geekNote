package com.demo.pr3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.UserTable.ContactsTable;
import com.UserTable.User;
/******************** (C) COPYRIGHT 2012********************
*��ʾ��ϵ�˽��� 
************************************************************/
public class ContactsMessageActivity extends Activity {
    /** Called when the activity is first created. */
	//����
	private  TextView nameTextView;  
	//����
	private  TextView stuTextView;
	//ʱ��
	private  TextView datesTextView;

	//�޸ĵ��¼�
	private  User user; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        setTitle("���±���Ϣ");
        
        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
        nameTextView=(TextView)findViewById(R.id.name); 
        stuTextView=(TextView)findViewById(R.id.stu); 
        datesTextView=(TextView)findViewById(R.id.dates); 
        
        //��Ҫ�༭���±����ݸ�ֵ���û�������ʾ
        Bundle localBundle = getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user =ct.getUserByID(id);
        nameTextView.setText("����:"+user.getName());
        stuTextView.setText("����:"+user.getStu());
        datesTextView.setText("ʱ��:"+user.getDates());

    }
    /**
	 * �����˵�
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	     menu.add(Menu.NONE, 1, Menu.NONE, "����");
		 return super.onCreateOptionsMenu(menu);
    }
   /**
    * �˵��¼�
    */
	public boolean onOptionsItemSelected(MenuItem item){
		// TODO Auto-generated method stub
		switch (item.getItemId()) { 
        case 1://����
           finish();
           break;
         default:
             break;
        }
		return super.onOptionsItemSelected(item);
	}
}