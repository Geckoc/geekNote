package com.demo.pr3;

import com.UserTable.User;
import com.UserTable.UserContactsTable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
public class Loginrest extends Activity{

public void onCreate(Bundle savedInstanceState){
	
	super.onCreate(savedInstanceState);
	super.setContentView(R.layout.login);

/*	Button mybut=(Button)findViewById(R.id.mybut);
	mybut.setOnClickListener(new OnClickListener() {		
		public void onClick(View v) {
			User user=new User();
	        user.setUsername(usernameEditText.getText().toString());
	        user.setUserpwd(userpwdEditText.getText().toString());
	        
	        UserContactsTable cts=new UserContactsTable(Loginrest.this);
	       
	        boolean str=cts.Userlogin(user);
	        if(str){
	        	Toast.makeText(Loginrest.this, "µ«¬º≥…π¶£°",Toast.LENGTH_SHORT).show();
	        	Intent intent=new Intent(Loginrest.this, MyContactsActivity.class);
				startActivity(intent);
	        }
	        else {
	        	Toast.makeText(Loginrest.this, "µ«¬º ß∞‹£°«Î÷ÿ–¬ ‰»Î’À∫≈√‹¬Î£°",Toast.LENGTH_SHORT).show();
			}
			
			
			
		}
	});
	Button register=(Button)findViewById(R.id.register);
	register.setOnClickListener(new OnClickListener() {		
		public void onClick(View v) {
	
			Intent intent=new Intent(Loginrest.this, UserAddActivity.class);
			startActivity(intent);
		}
	});  */
}
}
