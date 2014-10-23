package com.example.cardswipe;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.cardswipe.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{

 private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String DB_END_POINT = "mydbinstance.ckhnhh5hb4bw.us-east-1.rds.amazonaws.com";
	private final String DB_USER_NAME = "easygo";
	private final String DB_PWD = "easygoeasygo";
	private final String DB_NAME = "EasyGoDB";
	private final int DB_PORT = 3306;
	Private string number=null;
	private  String lat = null;
	private  String longi = null;
	private  String phonelat = null;
	private String phonelong = null;
	private String latitude= null;
	private String longitude=null;
   public void onCreate(Bundle icicle)
   {
      super.onCreate(icicle);
      setContentView(R.layout.activity_main);
      
      Button b = (Button) findViewById(R.id.swipecard);
      
      b.setOnClickListener(new View.OnClickListener() {
      
         public void onClick(View arg0) {
         // here i call new screen;
        	  
        	 try{
        		 EditText e1 = (EditText) findViewById(R.id.cardnum);
     			EditText e2 = (EditText) findViewById(R.id.latitude);
     			EditText e3 = (EditText) findViewById(R.id.longitude);
        		 // This will load the MySQL driver, each DB has its own driver
      						Class.forName("com.mysql.jdbc.Driver").newInstance();
      						// Setup the connection with the DB
      						connect = DriverManager
      								.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

      						// Statements allow to issue SQL queries to the database
      						statement = connect.createStatement();
      						System.out.println("connected");
      						
      						String createTableSql = "INSERT INTO locswipe(Cardnumber, Lat, Lng) Select Cardnumber, Lat, Lng from geo ORDER BY RAND() limit 1";
      								
      								int rs = statement.executeUpdate(createTableSql); 
      								
      								String createTableSql2 ="Select * from locswipe where number=number";
									int rs2 = statement.SelectQuery(createTableSql2); 
      								      								
      								ResultSet rs1 = statement.executeQuery(createTableSql1);	
      								ResultSet rs2 = statement.executeQuery(createTableSql2)
      								ResultSetMetaData rsmd = rs1.getMetaData();
      								int columnCount = rsmd.getColumnCount();
									String createTableSql = "select * from Loc where number=";
      								
      								while(rs1.next())
      								{
      									e1.setText(rs1.getString("Cardnumber"));
      									
      									e2.setText(rs1.getString("Lat"));
      									e3.setText(rs1.getString("Lng"));
      									String cardnum = rs1.getString("Cardnumber");
      									latitude = rs1.getString("Lat")	;
      									longitude = rs1.getString("Lng")	;
      									phonelat = rs2.getString("lat");
      									phonelong = rs2.getString("lng");
      									 lat = latitude.substring(0, 2);      									      									 
      									longi = longitude.substring(0, 3);
      									
      								}
      								statement.close();
      								connect.close();
      								
      								if(lat.equals(phonelat) && longi.equals(phonelong))
  									{
      									Toast.makeText(getApplicationContext(), "Valid card Swipe",Toast.LENGTH_SHORT).show();;
  										  										   										
  										
  									}
  									else
  									{
  										Toast.makeText(getApplicationContext(), "Invalid card swipe",Toast.LENGTH_SHORT).show();									
  									
  									}	
  								
      								
      								
      						
      					} catch (Exception e) {
      						e.printStackTrace();				
      						
      					}
        	 
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