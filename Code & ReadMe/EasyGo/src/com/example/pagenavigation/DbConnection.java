package com.example.pagenavigation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class DbConnection 
{
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	String[] credentials;
	private String DB_END_POINT = "mydbinstance.ckhnhh5hb4bw.us-east-1.rds.amazonaws.com";
	private final String DB_USER_NAME = "easygo";
	private final String DB_PWD = "easygoeasygo";
	private final String DB_NAME = "EasyGoDB";
	private final int DB_PORT = 3306;
	
	/* public static void main(String[] args) throws SQLException {
		DbConnection obj = new DbConnection();
		obj.createConnectionAndStatement();
		
	} */
	
	public String[] createConnectionAndStatement() throws SQLException
	{
		try{
			// This will load the MySQL driver, each DB has its own driver
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						// Setup the connection with the DB
						connect = DriverManager
								.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);

						// Statements allow to issue SQL queries to the database
						statement = connect.createStatement();
						System.out.println("connected");
						String createTableSql = "Select Card_Num from Card_Details where uname ='meghana')";
						
						ResultSet rs = statement.executeQuery(createTableSql); 
						ResultSetMetaData rsmd = rs.getMetaData();
						int colCount = rsmd.getColumnCount();
						while(rs.next())
						{
							for(int i=1;i<=colCount+1;i++)
							{
								String card_num1 = rs.getString(1);
								String card_num2= rs.getString(2);
								System.out.println(card_num1);
								System.out.println(card_num2);
							}
						}
						
								statement.close();
								connect.close();
						
					} catch (Exception e) {
						e.printStackTrace();				
						
		}
		
		return credentials;
	}
	
}
