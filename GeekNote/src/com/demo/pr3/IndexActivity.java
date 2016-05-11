package com.demo.pr3;

import com.UserTable.User;
import com.UserTable.UserContactsTable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class IndexActivity extends Activity{
	private Button login,register;
	private EditText usernameEditText;
	private EditText pwd;
	private Button email=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frist);
        setTitle("记事本");
   login=(Button)findViewById(R.id.Buttonlogin);
   register=(Button)findViewById(R.id.Buttonregister);
   
   
   login.setOnClickListener(new OnClickListener() {
	public void onClick(View v) {
	   	LayoutInflater loginInflater=LayoutInflater.from(IndexActivity.this);
	   	final View loginView=loginInflater.inflate(R.layout.login, null);
	   	Dialog dialog=new AlertDialog.Builder(IndexActivity.this)
	   	   .setIcon(R.drawable.icon)
	   	   .setView(loginView)
	   	   .setIcon(R.drawable.logo3)
	   	   .setTitle("用户登陆")
	   	   .setPositiveButton("登陆", new DialogInterface.OnClickListener() {		
			public void onClick(DialogInterface dialog, int which) {
				usernameEditText=(EditText)loginView.findViewById(R.id.username);
				pwd=(EditText)loginView.findViewById(R.id.userpwd);
				User user=new User();
		        user.setUsername(usernameEditText.getText().toString());
		        user.setUserpwd(pwd.getText().toString());   
		        UserContactsTable cts=new UserContactsTable(IndexActivity.this);	       
		        boolean str=cts.Userlogin(user);
		        if(str){
		     /*   	AnimationSet set=new AnimationSet(true);
					AlphaAnimation alpha=new AlphaAnimation(0, 1);
					alpha.setDuration(3000);
					set.addAnimation(alpha);
					IndexActivity.this.test.startAnimation(set); */
		        	Toast.makeText(IndexActivity.this, "登录成功！",Toast.LENGTH_SHORT).show();
		        	Intent intent=new Intent(IndexActivity.this, MyContactsActivity.class);
					startActivity(intent);
		        }
		        else {
		        	Toast.makeText(IndexActivity.this, "登录失败！请重新输入账号密码！",Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				
			}
		})
		.create();
	   	dialog.show();
	}
});
   register.setOnClickListener(new OnClickListener() {		
		public void onClick(View v) {
	
			Intent intent=new Intent(IndexActivity.this, UserAddActivity.class);
			startActivity(intent);
		}
	});
   
   email=(Button)findViewById(R.id.Buttonhelp);
/*   email.setOnClickListener(new OnClickListener()
   {
	public void onClick(View v) {
		Intent emailIntent=new Intent(Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		String address[]=new String[]{"chanilix@foxmail.com"};
		String subject="用户反馈:GeekNote修改建议";
		String content="请输入您的建议/意见";
		emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, content);
		IndexActivity.this.startActivity(emailIntent);
	}
});  */
   
	}
	public void quit(){
    	Builder alert = new AlertDialog.Builder(this); 
		alert.setTitle("温馨提示");
		alert.setMessage("您确定要退出？");
		alert.setPositiveButton("退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				IndexActivity.this.finish();
				
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