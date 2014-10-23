package com.example.pagenavigation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MyNewScreen extends TabActivity
{
 private static final String String = null;
 int finalid = 0;
 String card_num1=null;
 String card_num2 =null;
MyNewScreen ob;
 private EditText card_details;
 private TextView mtv1=null;
 private TextView mtv2=null;
 
 private RadioButton radio1;
 private Button card_add;
 StringBuilder s;
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
      setContentView(R.layout.activity_my_new_screen);          
    
 
      
      mtv1 = (EditText)findViewById(R.id.card1);
      mtv1.setEnabled(false);
      
     
      card_add=(Button)findViewById(R.id.card_add);
      
      String UName=getIntent().getExtras().getString("Username");
      try{
  		 // This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				// Setup the connection with the DB
				connect = DriverManager
						.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

				// Statements allow to issue SQL queries to the database
				statement = connect.createStatement();
				System.out.println("connected");
				String card_num = card_details.getText().toString();
			
				String createTableSql = "select Card_Num from Card_Details where uname ='"+UName+"')";
						
						ResultSet rs = statement.executeQuery(createTableSql); 
						String cnum = rs.getString("Card_Num");
						if(cnum.length()>0)
						{
							mtv1.setText(cnum);
						}
						statement.close();
						connect.close();
						
				
			} catch (Exception e) {
				e.printStackTrace();				
				
			}   	  
	
     
      
      card_add.setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View v) {
              // TODO Auto-generated method stub
        
        	card_details=(EditText)findViewById(R.id.card);
        	 
        	  
        	s= new StringBuilder(card_details.getText().toString());        	
      	    s.replace(0,12,"XXXX-XXXX-XXXX-"); 
      	  mtv1.setEnabled(true);
      	    
      	    mtv1.setText(s);
      	   
      	  mtv1.setEnabled(false);
	        	 
        	  String UName=getIntent().getExtras().getString("Username");
        	  try{
         		 // This will load the MySQL driver, each DB has its own driver
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// Setup the connection with the DB
					connect = DriverManager
							.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

					// Statements allow to issue SQL queries to the database
					statement = connect.createStatement();
					System.out.println("connected");
					String card_num = card_details.getText().toString();
					if(card_num.length()!=16)
					{
						Toast.makeText(getApplicationContext(), "Enter 16 Digit valid card number",Toast.LENGTH_SHORT).show();
					      
					}
					else
					{
					String createTableSql = "Insert into Card_Details(uname,Card_Num) values ('"+UName+"','"+card_num+"')";
							
							int rs = statement.executeUpdate(createTableSql); 
							Toast.makeText(getApplicationContext(), "Card Details Updated for "+UName,Toast.LENGTH_SHORT).show();
							Thread.sleep(10000); 
							 Intent i4 = new Intent(MyNewScreen.this, LocationServiceActivity.class);
				             startActivity(i4);						      
					}		
							statement.close();
							connect.close();
					
				} catch (Exception e) {
					e.printStackTrace();				
					
				}   	  
   
          	}
      });
    }
   
 
}