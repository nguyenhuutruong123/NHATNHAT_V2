package geso.dms.center.db.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Idbutils {
		
	    public boolean connect()  ;
	    public int updateReturnInt(String query);

	    public ResultSet get(String query)  ;

	    public ResultSet getScrol(String query) ;
	    public boolean update(String query) ;
	    
	    public int execProceduce(String procName, String[] param);
	    public ResultSet getRsByPro(String ProcName,String[] param) ;
	   
	    public String execProceduce2(String procName, String[] param);
	     
	    public boolean shutDown() ;
	    
	    public List<List<String>> RStoList(ResultSet rs, int num);
	    
	  
	    public Connection getConnection();
	    public int update_v2(String query ,Object[] data);
	    public void ClearParam();
		public void AddParam(String key,String value);
		public int update_with_param(String query);
		public ResultSet get_with_param(String query);
}
