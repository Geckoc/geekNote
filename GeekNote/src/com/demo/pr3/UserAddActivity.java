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
	    //�û��˺�
		private  EditText usernameEditText;  
		//������
		private  EditText userpwdEditText;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        setTitle("ע���˺�");
	        //�������õ�ҳ�沼�ֲ��Ҷ�Ӧ�Ŀؼ�
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
							Toast.makeText(UserAddActivity.this, "ע��ɹ���",
							Toast.LENGTH_SHORT).show();
							finish();
						}else
						{
							Toast.makeText(UserAddActivity.this, "ע��ʧ�ܣ�",
							Toast.LENGTH_SHORT).show();
							
						}
					}else
					{
						Toast.makeText(UserAddActivity.this, "�����������ݣ�",
						Toast.LENGTH_SHORT).show();
					}	
	    		} 
	    	});
	    }
	    /**
		 * �����˵�
		 */
		public boolean onCreateOptionsMenu(Menu menu) {
		   
		     menu.add(Menu.NONE,1, Menu.NONE, "ע��");
		     menu.add(Menu.NONE,2, Menu.NONE, "����");
			 return super.onCreateOptionsMenu(menu);
	    }
	   /**
	    * �˵��¼�
	    */
		public boolean onOptionsItemSelected(MenuItem item){
			// TODO Auto-generated method stub
			switch (item.getItemId()) { 
	        case 1://����
	        	if(!usernameEditText.getText().toString().equals(""))
				{
					User user=new User();
					user.setUsername(usernameEditText.getText().toString());
					user.setUserpwd(userpwdEditText.getText().toString());
					UserContactsTable ct=new UserContactsTable(UserAddActivity.this);
					if(ct.addData(user))
					{
						Toast.makeText(UserAddActivity.this, "ע��ɹ���",
						Toast.LENGTH_SHORT).show();
						finish();
					}else
					{
						Toast.makeText(UserAddActivity.this, "ע��ʧ�ܣ�",
						Toast.LENGTH_SHORT).show();
						
					}
				}else
				{
					Toast.makeText(UserAddActivity.this, "�����������ݣ�",
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
