package com.UserTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *SQLite ���ݿ��������
 */
public class MyDB extends SQLiteOpenHelper{

	
	 private static String DB_NAME = "My_DB.db";  //���ݿ�����
		
	 private static int DB_VERSION = 2;  //�汾��
	
	 private  SQLiteDatabase db; //���ݿ��������
	
	 public  MyDB(Context context)
	 {    
		 super(context, DB_NAME, null, DB_VERSION);
		 db=getWritableDatabase(); 
	 }
	 @Override
	 public void onCreate(SQLiteDatabase db) {
		// TODO �������ݿ�󣬶����ݿ�Ĳ���
	 }

	 @Override
	 public void onOpen(SQLiteDatabase db) {
		// TODO ÿ�γɹ������ݿ�����ȱ�ִ��
		super.onOpen(db);
	 }

	 @Override
	 public void onUpgrade(SQLiteDatabase db, 
			 int oldVersion, int newVersion) {
	 	// TODO �������ݿ�汾�Ĳ���
	 }
	 /***
	  * ִ��SQLite���ݿ�����
	  * @return  SQLiteDatabase
	  */
	 public SQLiteDatabase openConnection (){  
		 
		    if(!db.isOpen())
		    {
		    	 //��д��ʽ��ȡSQLiteDatabase
		    	db=getWritableDatabase(); 
		    }
	        return db;
	 }
	 /***
	  * �ر� SQLite���ݿ����Ӳ���
	  * @return
	  */
	 public void closeConnection (){
		 try{ 
			 if(db!=null&&db.isOpen())
	          db.close();
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
	 /**
	  * ������  
	  * @param createTableSql      
	  * @return
	  */
	 public  boolean creatTable(
			 String  createTableSql)
	 {   
         try{
        	 openConnection();
    	     db.execSQL(createTableSql); 

           }catch(Exception ex)
		   {
        	   ex.printStackTrace();
				return false;
		   }finally
		   {
			   closeConnection();
		   }
		   return true;
	 }
	 /**
	  * ��Ӳ���
	  * @param tableName   ����
	  * @param values      ���϶���
	  * @return
	  */
	 public  boolean  save(String tableName,
			          ContentValues values )
	 {
		 try{
			 openConnection();
			 db.insert(tableName, null, values);
		  
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
			  return  false;
		  }finally
		  {
			   closeConnection();
		  }
		  return true;
	 }
	 /**
	  * ���²���
	  * @param table
	  * @param values
	  * @param whereClause
	  * @param whereArgs
	  * @return
	  */
	 public  boolean  update(String table,
	  ContentValues values ,String whereClause,String []whereArgs)
	 {
		     try{
		    	 openConnection();
		    	 db.update(table, values, whereClause, whereArgs);
			  
		      }catch(Exception ex)
			  {  
		    	  ex.printStackTrace();
				  return  false;
			  }finally
			  {
				   closeConnection();
			  }
		return true;
	 }
	 /**
	  * ɾ��
	  * @param    deleteSql   ��Ӧ�����ֶ�   
	  *  �磺 "where personid=?"
	  * @param    obj[]       ��Ӧֵ                 
	  *   �磺   new Object[]{person.getPersonid()};
	  * @return                
	  */
	 public  boolean  delete(String table,
			 String deleteSql,String obj[])
	 {
		  try{
			  openConnection();
			  db.delete(table, deleteSql, obj);
		      
		   }catch(Exception ex)
		   {     
			   ex.printStackTrace();
			   return  false;
		   }finally
		   {
			   closeConnection();
		   }
		   return true;
	 }
	 /**
	 * ��ѯ����   
	 * @param findSql  ��Ӧ��ѯ�ֶ�    �磺 
	 * select * from person limit ?,?
	 * @param obj      ��Ӧֵ                   �磺 
	 *  new String[]{String.valueOf(fristResult)
	 *  ,String.valueOf(maxResult)}
	 * @return
	 */
	 public  Cursor  find(String findSql,String obj[]  )
	 {   
		
		 try{
			 openConnection();
			 Cursor cursor = db.rawQuery(findSql,obj);
			 return  cursor;
          
		   }catch(Exception ex)
		   {    
			    ex.printStackTrace();
				return null;
		   }
	 }	 
	 /**
	 * �жϱ��Ƿ����
	 * @param tablename
	 * @return
	 */
	 public boolean isTableExits( String tablename){
		 try{
			 openConnection();
			 String str="select count(*) xcount  from  "
				 +tablename;
	         db.rawQuery(str,null).close();
	       }catch(Exception ex)
		   {
				return false;
		   }finally
		   {
			   closeConnection();
		   }
	       return true;
	 }
	public Cursor query(String string, String[] col, String string2, Object object, Object object2, Object object3,
			Object object4) {
		// TODO Auto-generated method stub
		return null;
	}
	
}