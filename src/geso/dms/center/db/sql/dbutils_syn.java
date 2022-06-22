package geso.dms.center.db.sql;

import java.sql.*;  
import java.lang.String;
import java.io.Serializable;

public class dbutils_syn implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	
	private String username = "salesup";
	private String password = "sales@up@1235";
	public static String dbName = "IMEX_Sync";	
	private String url = "jdbc:jtds:sqlserver://115.79.59.115:1433/"+dbName;
	
	private Connection connection;
	private Statement statement;
	
    public dbutils_syn()
    {  
    	connect();
    }  
      
    
    public boolean connect()  
    {  
    	try 
    	{  
    			
    		Class.forName("net.sourceforge.jtds.jdbc.Driver");
    		connection = DriverManager.getConnection(url, username, password);
    		
    		return true;  
    	}catch ( Exception ex ) {
    		System.out.print(ex.toString());    		
    	}  
        	return false;  
    }  
    public int updateReturnInt(String query)  
    {  
    	try {  
    		statement = connection.createStatement();  
    		return	statement.executeUpdate( query );  
    		
    		 
    	}  
    	catch ( SQLException sqle ) {  
    		return -1;  
    	}  
    }  		

    public ResultSet get(String query)  
    {  
    	try {
    		
    		statement = connection.createStatement();
    		ResultSet rs = statement.executeQuery( query );  
    		return rs;      	 
    	}catch ( SQLException sqle ) {	
    		System.out.print(sqle.toString());
    		return null;
        }                
    }  

    public ResultSet getScrol(String query)  
    {  
    	try {
    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		ResultSet rs = statement.executeQuery( query );  
    		return rs;      	 
    	}catch ( SQLException sqle ) {	
    		System.out.print(sqle.toString());
    		return null;
        }                
    }  
    
    public boolean update(String query)  
    {  
    	try {  
    		statement = connection.createStatement();  
    		statement.executeUpdate( query );  
    		
    		return true;  
    	}  
    	catch ( SQLException sqle ) {  
    		return false;  
    	}  
    }  
    
    public boolean shutDown()  
    {  
    	try {  
    		if(statement != null)
    			statement.close();
    		
    		if(connection != null)
    			connection.close();  
    		
    		return true;  
    	}  
    	catch ( SQLException sqlex ) {  
    		return false;  
    	}  
    }  
    
 
  
    public Connection getConnection(){
    	return this.connection;
    }
}  
