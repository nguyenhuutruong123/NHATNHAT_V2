package geso.dms.center.db.sql;

import java.sql.*;  
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class dbutils implements Idbutils,Serializable
{ 
	private static final long serialVersionUID = 1L;
	

	/*private String username = "geso";
	private String password = "geso@baongoc";
	private String url = "jdbc:jtds:sqlserver://1.53.252.165:14333/BAONGOC";*/
	
	
	/*
	 * private String username = "dms"; private String password = "geso!@#"; private
	 * String url = "jdbc:jtds:sqlserver://118.69.168.124:1433/";
	 */
	 
	/*
	 * 
	 * private String username = "sa"; private String password = "Dms07Erp21";
	 * private String url = "jdbc:jtds:sqlserver://localhost:1433/VNP";
	 */
	
	  private String username = "sa"; private String password = "Dms142022#@!!@#";
	private String url = "jdbc:jtds:sqlserver://118.70.67.47/DMS_NHATNHAT";
	
	
	
	private Connection connection;
	private Statement statement;
	public  PreparedStatement prep;
	public static final String NULLVAL = "NULL";
	
    public dbutils()
    {  
    	connect();
    }  
      
    public boolean connect()  
    {  
    	try 
    	{  
    		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		//connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=OneOne;user=" + username + "; password=" + password);
    		
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
    		sqle.printStackTrace();
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
    		System.out.print("error :"+query);
    		sqle.printStackTrace();
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
    	catch ( Exception sqle ) {  
    		sqle.printStackTrace();
    		return false;  
    	}  
    }  
    
    public int execProceduce(String procName, String[] param)
    {
		try 
		{
			String query = "";
	    	for(int i = 0; i < param.length; i++)
	    		query += "?,";
	    	if(query.length() > 0)
	    		query += "?"; //tham so dau ra, luu ket qua sau khi thuc thi thu tuc
	    	
	    	//query = "{call exOutParams(?,?)}";
	    	query = "{call " + procName + "(" + query + ")}";
	    	System.out.println("Query la: " + query);
	    	
			CallableStatement cstmt = connection.prepareCall(query);
			for(int i = 0; i < param.length; i++)
			{
				cstmt.setString(i + 1, param[i]);
			}

	    	//đăng ký tham số đầu ra
			cstmt.registerOutParameter(param.length + 1, java.sql.Types.INTEGER);
	    	cstmt.execute();

	    	//Lấy giá trị trả về
	    	int resual = cstmt.getInt(param.length + 1);
	    
	    	return resual;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Loi: " + e.toString());
			return -1;
		}
    }
    public ResultSet getRsByPro(String ProcName,String[] param) {
        try{
      
        	String para="";
       
        String SPsql = "EXEC "+ ProcName +" ";   // for stored proc taking 2 parameters
         // java.sql.Connection 
        String param_num="";
        if(param != null)
		{
			for(int i = 0; i < param.length; i++){
				param_num=param_num+"?,";
			}
			param_num = param_num.substring(0, param_num.length() - 1); //cat dau , cuoi
		}
        
        PreparedStatement ps = connection.prepareStatement(SPsql+param_num);
        ps.setEscapeProcessing(true);
       // ps.setQueryTimeout(<timeout value>);
      
        if(param != null)
		{
			for(int i = 0; i < param.length; i++){
				para+=param[i]+",";
				ps.setString(i+1, param[i]);
			}
			para = para.substring(0, para.length() - 1); //cat dau , cuoi
			
		}
        System.out.println("___Param: " + SPsql+para);
        ResultSet rs = ps.executeQuery();
        return rs;
        }  
    	catch ( SQLException sqle ) 
    	{ 
    		sqle.printStackTrace();
    		return null;
    	}  
   }
   
    public String execProceduce2(String procName, String[] param)
    {
    	try {  
    		statement = connection.createStatement();  
    		  
    		String query = procName;
    		
    		if(param != null)
    		{
    			String paramList = "";
    			for(int i = 0; i < param.length; i++)
    				paramList += "'" + param[i] + "',";
    			paramList = paramList.substring(0, paramList.length() - 1); //cat dau , cuoi
    			
    			query += " " + paramList;
    		}
    		
    		statement.executeUpdate( query );
    			
    		return "";  
    	}  
    	catch ( SQLException sqle ) {  
    		return sqle.toString();  
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
    
    public List<List<String>> RStoList(ResultSet rs, int num){
    	List<List<String>> list = new ArrayList<List<String>>();

    	try{
    		while (rs.next()){
    			List<String> tmp = new ArrayList<String>();
    			for (int i = 1; i<= num; i++){
    				tmp.add(rs.getString(i));
    			}
    			list.add(tmp);
    		}
    	}catch( SQLException sqlex ) {
    		
    	}
    	
    	return list;
    }
  
    public Connection getConnection(){
    	return this.connection;
    }
    
    public static Object[] setObject(Object... ObjectArrays) {
		Object[] temp = null;
		if (ObjectArrays.length >0) {
			temp = new Object[ObjectArrays.length];
			for (int i = 0; i < ObjectArrays.length; i++) {
				temp[i] = ObjectArrays[i];
			}
		}
		
		return temp;
	}
	
	public int update_v2(String query ,Object[] data)
    {
   	
    	try 
    	{      		
	    	// "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?"
	    	if(data == null || data.length <=0) return -1;
	    	
	    	
	    	prep = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    
		    for(int i= 0; i < data.length ; i++ )
		    {
		    	System.out.println("i= :"+i+"="+ data[i]);
		    	if(data[i] == null || ((String)data[i].toString().toUpperCase()).equals(NULLVAL))
		    		prep.setNull(i+1,java.sql.Types.VARCHAR);
		    	else
		    		prep.setObject(i+1, data[i]);
		    }
		 
		    return prep.executeUpdate();
	    	
	    	
	    	//return true;
    	}  
    	catch ( Exception sqle ) { 
    		//dbutils.viewQuery(query,data);
    		sqle.printStackTrace();
    		return -1;
    		//return false;  
    	}  
    }
	public ResultSet get_v2(String query,Object[] data)  
	{
		Object[] arr = new String[data.length];
		arr = data;
		return getByPreparedStatement(query,arr);
	}
	
	public ResultSet getByPreparedStatement(String query,Object[] data)  
    {  
		   	
    	try 
    	{  
    	
	    	// "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?"
	    	if(data == null ) return null;
	    	
	    	
		    PreparedStatement preparedStatement = connection.prepareStatement(query);
		    prep = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
		    for(int i= 0; i < data.length ; i++ )
		    {
		    	
		    	if(data[i] == null || data[i].equals(NULLVAL))
		    		preparedStatement.setNull(i+1,java.sql.Types.VARCHAR);
		    	else
		    		preparedStatement.setObject(i+1, data[i]);
		    	
		    }
		    ResultSet rs = preparedStatement.executeQuery();  
    		return rs; 
	    	
	    	
	    	//return true;
    	}  
    	catch ( Exception sqle ) { 
    		sqle.printStackTrace();
    		return null;
    		//return false;  
    	}  
             
    }
	public String getPk_seq()
	{
		try
		{
			ResultSet rs = this.prep.getGeneratedKeys();
			rs.next();
			return rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void viewQuery(String query ,Object[] data)
	{
		String queryLog = query;
		for(int i= 0; i < data.length ; i++ ) {
			queryLog = queryLog.replaceFirst("\\?", (data[i]==null?"null":"'"+data[i].toString().replaceAll("\\?","--")+ "'"));	
		}
		System.out.println("db center: query = "+ queryLog);
	}
	
public List<String[]> hashParam = new ArrayList<String[]>();
	
	
	public void ClearParam()
	{
		this.hashParam.clear();
	}
	public void AddParam(String key,String value)
	{
		this.hashParam.add( new String[]{key, value});
	}
	
	public int update_with_param(String query)
	{

		try 
		{      		
			// "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?"
			if(hashParam == null || hashParam.size() <=0) return -1;


			for (int i =0; i < hashParam.size(); i ++) 
			{
				String[]arr =hashParam.get(i);
				String key = arr[0];
				query = query.replaceFirst(key, "?");

			}
			System.out.println("query = " + query);

			prep = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			for (int i =0; i < hashParam.size(); i ++) 
			{
				String[]arr =hashParam.get(i);
				String key = arr[0];
				String val = arr[1];
				if( val == null || val.toUpperCase().equals(NULLVAL))
					prep.setNull(i+1,java.sql.Types.VARCHAR);
				else
					prep.setObject(i+1,(Object)val);

			}



			//		    for(int i= 0; i < data.length ; i++ )
				//		    {
				//		    	System.out.println("i= :"+i+"="+ data[i]);
			//		    	if(data[i] == null || data[i].equals(NULLVAL))
			//		    		prep.setNull(i+1,java.sql.Types.VARCHAR);
			//		    	else
			//		    		prep.setObject(i+1, data[i]);
			//		    }

			int sd = prep.executeUpdate();
			this.ClearParam();
			return sd;

			//return true;
		}  
		catch ( Exception sqle ) { 
			//dbutils.viewQuery(query,data);
			sqle.printStackTrace();
			return -1;
			//return false;  
		}  
	}
	public ResultSet get_with_param(String query)  
	{  

		try 
		{  

			// "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?"
			if(hashParam == null || hashParam.size() <=0) return this.get(query);




			for (int i =0; i < hashParam.size(); i ++) 
			{
				String[]arr =hashParam.get(i);
				String key = arr[0];
				query = query.replaceFirst(key, "?");

			}
			System.out.println("query = " + query);

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			for (int i =0; i < hashParam.size(); i ++) 
			{
				String[]arr =hashParam.get(i);
				String key = arr[0];
				String val = arr[1];
				if( val == null || val.toUpperCase().equals(NULLVAL))
					preparedStatement.setNull(i+1,java.sql.Types.VARCHAR);
				else
					preparedStatement.setObject(i+1,(Object)val);

			}


			ResultSet rs = preparedStatement.executeQuery();  
			this.ClearParam();
			return rs; 


			//return true;
		}  
		catch ( Exception sqle ) { 
			sqle.printStackTrace();
			return null;
			//return false;  
		}  

	}
}  
