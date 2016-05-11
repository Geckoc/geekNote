package com.UserTable;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


public class ContactsTable {

	private   final static String TABLENAME="contactsTable";//表名
	private   MyDB   db;//数据库管理
	
	public    ContactsTable(Context context)
	{
	    db=new  MyDB(context);
	    if(!db.isTableExits(TABLENAME))
		{
		     String   createTableSql="CREATE TABLE IF NOT EXISTS " +
		     TABLENAME + " (  id_DB  integer   " +
		     "primary key  AUTOINCREMENT , " +
		     User.NAME     + "  VARCHAR," +
		     User.DATES   + "  VARCHAR,"+
		     User.STU    + "  VARCHAR)";
		     //创建表
             db.creatTable(createTableSql);
		}
	   
	}

	/**
	 * 添加数据到记事本人表
	 * @param name

	 * @return
	 */
	public  boolean  addData(User user)
	{
	   	
		ContentValues values = new ContentValues();
		values.put(User.NAME, user.getName());
		values.put(User.DATES, user.getDates());
		values.put(User.STU, user.getStu());
	    return	db.save(TABLENAME, values);
	}
	
	/**
	* 获取记事本表数据
	* @return
	*/
	public  User[] getAllUser()
	{
	    Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find("select * from " + TABLENAME , null);
          while (cursor.moveToNext()) {
        	User temp = new User();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setDates(cursor.getString(cursor.getColumnIndex(User.DATES)));
            temp.setStu(cursor.getString(cursor.getColumnIndex(User.STU)));

            
            v.add(temp);
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
        	User[]  users=new User[1];
        	User  user=new User();
        	user.setName("开始编写日记吧");
        	users[0]=user;
        	return users;
        }
	}
	/**
	 * 根据数据库变ID主键来获取
	 * @param id
	 * @return
	 */
	public User getUserByID(int id)
	{
		 Cursor cursor = null;
	        try {
	            cursor = db.find(
	            		"select * from " + TABLENAME +"   where  "
	            	+"id_DB=?" , new String[]{id+""});
	        	User temp = new User();
	        	cursor.moveToNext();
	        	 temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
	                 temp.setName(cursor.getString(
	                 	cursor.getColumnIndex(User.NAME)));
	                 temp.setDates(cursor.getString(
	                 	cursor.getColumnIndex(User.DATES)));
	                 temp.setStu(cursor.getString(
	                 	cursor.getColumnIndex(User.STU)));

	                 
	            
	           return temp;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (cursor != null) {
	                cursor.close();
	            }
	            db.closeConnection();
	        }
	        return null;
	}
	/**
	* 获取记事本表数据
	* @return
	*/
	public  User[] findUserByKey(String key)
	{
	    Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find(
            	"select * from " + TABLENAME +"   where  "
            	+User.NAME+" like '%"+key+"%' " +
            	" or "+User.DATES+" like '%"+key+"%' " +
            	" or "+User.STU+" like  '%"+key+"%' "
            	, null);
          while (cursor.moveToNext()) {
        	User temp = new User();
        	 temp.setId_DB(cursor.getInt(
                 	cursor.getColumnIndex("id_DB")));
                 temp.setName(cursor.getString(
                 	cursor.getColumnIndex(User.NAME)));
                 temp.setDates(cursor.getString(
                 	cursor.getColumnIndex(User.DATES)));
                 temp.setStu(cursor.getString(
                 	cursor.getColumnIndex(User.STU)));

                 
            v.add(temp);
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[] {});
        }else
        {
        	User[]  users=new User[1];
        	User  user=new User();
        	user.setName("抱歉，没寻找到相应的日志");
        	users[0]=user;
        	return users;
        }
	}
	/**
	 * 修改
	 */
	public  boolean updateUser(User user)
	{
	  ContentValues values = new ContentValues();
	  values.put(User.NAME, user.getName());
	  values.put(User.DATES, user.getDates());
	  values.put(User.STU, user.getStu());

	  return db.update(TABLENAME, 
		     values, "  id_DB=? ", new String[]{user.getId_DB()+""});
	}
	/***
	 * test
	 */
	 public void FirstStart(){
	    	try{
		    	String col[] = {"type", "name" };
		    	Cursor c =db.query("sqlite_master", col, "name='colaconfig'", null, null, null, null);
		    	int n=c.getCount();
		    	if (c.getCount()==0){
		    		  		
		    	}	    	
		    	//test();	    	
		    	Log.v("cola","c.getCount="+n+"");	
	    	}catch(Exception e){
	    		Log.v("cola","e="+e.getMessage());
	    	}
	    	
	    	
	    }
	 public void close(){
	    	db.close();
	    }
	/**
	 * 删除
	 * @param user
	 * @return
	 */
	public  boolean  deleteByUser(User user)
	{
		return db.delete(TABLENAME,
			"  id_DB=?", new String[]{user.getId_DB()+""});
	}
	
}
