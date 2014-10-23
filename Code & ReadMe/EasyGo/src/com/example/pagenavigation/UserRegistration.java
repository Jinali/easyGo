package com.example.pagenavigation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistration extends Activity
{
  MyNewScreen ob;
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
      setContentView(R.layout.activity_user_registration);
      Button b = (Button) findViewById(R.id.register1);
      
      b.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
        	 try{
        		// This will load the MySQL driver, each DB has its own driver
       						Class.forName("com.mysql.jdbc.Driver").newInstance();
       						// Setup the connection with the DB
       						connect = DriverManager
       								.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

       						// Statements allow to issue SQL queries to the database
       						statement = connect.createStatement();
       						System.out.println("connected");
       						EditText e1 = (EditText) findViewById(R.id.reguname);
       						EditText e2 = (EditText) findViewById(R.id.regpassword);
       						EditText e3 = (EditText) findViewById(R.id.retype);
       						EditText e4 = (EditText) findViewById(R.id.regemail);
       						EditText e5 = (EditText) findViewById(R.id.phone);
       						String regusername = e1.getText().toString();
       						String regpassword = e2.getText().toString();
       						String regretype = e3.getText().toString();
       						String regemail = e4.getText().toString();
       						String regphone = e5.getText().toString();
       						//Toast.makeText(getApplicationContext(), regpassword,Toast.LENGTH_SHORT).show();
       						//Toast.makeText(getApplicationContext(), regretype,Toast.LENGTH_SHORT).show();
       					if(!android.util.Patterns.EMAIL_ADDRESS.matcher(regemail).matches())
       					{
       						Toast.makeText(getApplicationContext(), "Enter Valid Email",Toast.LENGTH_SHORT).show();
       					}
       					else if(!regpassword.equals(regretype))
       					{
       						Toast.makeText(getApplicationContext(), "Passwords dont match\n Please Re-nter Passwords",Toast.LENGTH_SHORT).show();
       				      
       					}
       					
       					else if(regphone.length()!=10)
       					{
       						Toast.makeText(getApplicationContext(), "Enter 10 digit valid phone number",Toast.LENGTH_SHORT).show();
       					}
       					else
       					{
       						String createTableSql = "Insert into Users(uname,password,email,phone) values ('"+regusername+"','"+regpassword+"','"+regemail+"','"+regphone+"')";
       								
       								int rs = statement.executeUpdate(createTableSql);				
       									
       								statement.close();
       								connect.close();
       								Toast.makeText(getApplicationContext(), "Registration Succesful" ,Toast.LENGTH_SHORT).show();
       								Intent i3 = new Intent(UserRegistration.this, MainActivity.class);
								        startActivity(i3);
       					}	
       					} catch (Exception e) {
       						e.printStackTrace();;					
       						
       		}
         } 
      });
        }
   
   public void setOb( MyNewScreen obA){
    this.ob=obA;
   }
}