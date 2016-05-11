package com.UserTable;

public    class User {

		public   final static String  NAME="name";  
		public   final static String  DATES="dates";
		public   final static String  STU="stu";
		public   final static String  USERNAME="username";
		public   final static String  USERPWD="userpwd";
		private  String username;
		private  String userpwd; 
		private  String name; //标题
		private  String dates;  //时间
		private  String stu;//内容
		private  int id_DB=-1;//数据库主键id
	
		public int getId_DB() {
			return id_DB;
		}
		public void setId_DB(int idDB) {
			id_DB = idDB;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getDates() {
			return dates;
		}
		public void setDates(String dates) {
			this.dates = dates;
		}
		public String getStu() {
			return stu;
		}
		public void setStu(String stu) {
			this.stu = stu;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUserpwd() {
			return userpwd;
		}
		public void setUserpwd(String userpwd) {
			this.userpwd = userpwd;
		}
		
}
