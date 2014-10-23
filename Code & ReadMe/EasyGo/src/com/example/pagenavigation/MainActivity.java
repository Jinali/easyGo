package com.example.pagenavigation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
 MyNewScreen obB=new MyNewScreen();
 private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String DB_END_POINT = "mydbinstance.ckhnhh5hb4bw.us-east-1.rds.amazonaws.com";
	private final String DB_USER_NAME = "easygo";
	private final String DB_PWD = "easygoeasygo";
	private final String DB_NAME = "EasyGoDB";
	private final int DB_PORT = 3306;
   public void onCreate(Bundle icicle)
   {
      super.onCreate(icicle);
      setContentView(R.layout.activity_main);
      
      Button b = (Button) findViewById(R.id.loginBtn);
      b.setOnClickListener(new View.OnClickListener() {
      
         public void onClick(View arg0) {
         // here i call new screen;
        	 
        	 
        	 try{
        		 // This will load the MySQL driver, each DB has its own driver
      						Class.forName("com.mysql.jdbc.Driver").newInstance();
      						// Setup the connection with the DB
      						connect = DriverManager
      								.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

      						// Statements allow to issue SQL queries to the database
      						statement = connect.createStatement();
      						System.out.println("connected");
      						EditText e1 = (EditText) findViewById(R.id.uname);
      						EditText e2 = (EditText) findViewById(R.id.password);
      						String username = e1.getText().toString();
      						String password = e2.getText().toString();
      						String createTableSql = "Select count(*) as count1 from Users where uname ='"+ username +"' and password = '"+password+"'";
      								
      								ResultSet rs = statement.executeQuery(createTableSql); 
      								while(rs.next())
      								{
      									if (rs.getInt("count1") > 0)
      									{
      										Intent i1 = new Intent(MainActivity.this, MyNewScreen.class);
      										i1.putExtra("Username", username);
      								        startActivity(i1);
      									}
      									else
      									{
      										Toast.makeText(getApplicationContext(), "wrong username/password",Toast.LENGTH_SHORT).show();
      									}
      								}
      								statement.close();
      								connect.close();
      						
      					} catch (Exception e) {
      						e.printStackTrace();				
      						
      					}
          		} 
       		});

      
      Button b1 = (Button) findViewById(R.id.regBtn);
      b1.setOnClickListener(new View.OnClickListener() {
      
         public void onClick(View arg0) 
         {
        	 Intent i2 = new Intent(MainActivity.this, UserRegistration.class);
             startActivity(i2);
         } 
        });
      try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      
   }
  
}