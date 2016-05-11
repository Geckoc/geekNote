package com.UserTable;

import android.content.Context;


import android.content.ContentValues;
import android.database.Cursor;

public class UserContactsTable {
	private   final static String USERTABLENAME="users";//表名
	private   MyDB   db;//数据库管理
	public    UserContactsTable(Context context)
	{
	    db=new  MyDB(context);
	 //users 用户信息表
    if(!db.isTableExits(USERTABLENAME)){
    	String   createTableSql="CREATE TABLE IF NOT EXISTS " +
   		     USERTABLENAME + " (  id_DB  integer   " +
   		     "primary key  AUTOINCREMENT , " +
   		     User.USERNAME     + "  VARCHAR," +
   		     User.USERPWD   + "  VARCHAR)";
   		     //创建表
                db.creatTable(createTableSql);
    	}
	}

	/**
	 * 添加数据到用户表
	 * @param name

	 * @return
	 */
	public  boolean  addData(User user)
	{
	   	
		ContentValues values = new ContentValues();
		values.put(User.USERNAME, user.getUsername());
		values.put(User.USERPWD, user.getUserpwd());
	    return	db.save(USERTABLENAME, values);
	}
	public boolean Userlogin(User user)
	{ 
		Cursor cursor = null;
		cursor = db.find("select * from "+USERTABLENAME+" where username='"+user.getUsername()+"' and userpwd='"+user.getUserpwd()+"'", null);

		if(cursor.getCount()!=0){
			cursor.close();
			return true;             
		}else {
			return  false;
		}
		
		/* 检查 游标下一个 是否存在   
		if(cursor.moveToNext()){
			cursor.close();
			return true;             
		}else {
			return  false;
		}*/
	}
	
}
