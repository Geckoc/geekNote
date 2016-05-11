package com.UserTable;

import android.content.Context;


import android.content.ContentValues;
import android.database.Cursor;

public class UserContactsTable {
	private   final static String USERTABLENAME="users";//����
	private   MyDB   db;//���ݿ����
	public    UserContactsTable(Context context)
	{
	    db=new  MyDB(context);
	 //users �û���Ϣ��
    if(!db.isTableExits(USERTABLENAME)){
    	String   createTableSql="CREATE TABLE IF NOT EXISTS " +
   		     USERTABLENAME + " (  id_DB  integer   " +
   		     "primary key  AUTOINCREMENT , " +
   		     User.USERNAME     + "  VARCHAR," +
   		     User.USERPWD   + "  VARCHAR)";
   		     //������
                db.creatTable(createTableSql);
    	}
	}

	/**
	 * ������ݵ��û���
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
		
		/* ��� �α���һ�� �Ƿ����   
		if(cursor.moveToNext()){
			cursor.close();
			return true;             
		}else {
			return  false;
		}*/
	}
	
}
