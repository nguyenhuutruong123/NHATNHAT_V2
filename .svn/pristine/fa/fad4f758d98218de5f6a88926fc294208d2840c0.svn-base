package geso.dms.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.beans.routesumaryreport.IRouteSumaryReport;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTBList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.report.Ireport;
import geso.dms.distributor.beans.reports.imp.Reports;
import geso.dms.distributor.util.FixData;

import javax.servlet.http.HttpSession;
public class UtilitySyn implements Serializable
{ 
	private static final long serialVersionUID = 1L;

	//Sử dụng bên server ERP thì chỉ có link ngược sang server DMS để SYN data
	static String dmsLinked = "LINK_DMS.TraphacoDMS.dbo.";
	static String erpLinked = "LINK_ERP.TraphacoERP.dbo.";
	
	public static String SynData(geso.dms.center.db.sql.dbutils db, String fromTable, String toTable, String columnName, String value, 
			String Insert_Update_Delete, boolean isDelete )
	{
		//Insert_Update_Delete: 0 -- Insert, 1 -- Update, 2 -- Delete.
		//Trường hợp Delete, mà không phải xóa dòng khỏi db, thì sử dụng như update, biến isDelete = false 
		
		boolean sudungDBNGOAI = true;
		if( db == null )
		{
			db = new geso.dms.center.db.sql.dbutils();
			sudungDBNGOAI = false;
		}
		
		String query = "select synQuery from SynConfig where fromTable = '" + fromTable + "' and toTable = '" + toTable + "' ";
		ResultSet rs = db.get(query);
		String synQuery = "";
		if( rs != null )
		{
			try 
			{
				if( rs.next() )
				{
					synQuery = rs.getString("synQuery");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				if( !sudungDBNGOAI )
					db.shutDown();
				
				return "1. Lỗi khi xử lý dữ liệu: " + e.getMessage();
			}
		}
		
		if( synQuery.trim().length() <= 0 )
		{
			/*if( !sudungDBNGOAI )
				db.shutDown();
			return "2. Lỗi khi xử lý dữ liệu";*/
			
			//Nếu chưa khai báo thì tự hiểu là SYN tất cả các cột
			synQuery = "*";
		}
		
		//LINK SANG ERP
		toTable = erpLinked + toTable;
		
		if( Insert_Update_Delete.equals("0") )
		{
			if( synQuery.equals("*") ) //Đồng bộ nguyên bảng
				query = "insert " + toTable + " select " + synQuery + " from " + fromTable + " where " + columnName + " = '" + value + "' ";
			else
				query = "insert " + toTable + " ( " + synQuery + " ) select " + synQuery + " from " + fromTable + " where " + columnName + " = '" + value + "' ";
		}
		else if( Insert_Update_Delete.equals("1") || ( Insert_Update_Delete.equals("2") && !isDelete ) )
		{
			String synQueryNEW = "";
			if( synQuery.equals("*") ) //Đồng bộ nguyên bảng
			{
				query = "SELECT COLUMN_NAME " +
						"FROM INFORMATION_SCHEMA.COLUMNS  " +
						"WHERE TABLE_NAME = '" + fromTable + "' ";
				rs = db.get(query);
				try 
				{
					while( rs.next() )
					{
						synQueryNEW += rs.getString("COLUMN_NAME") + ", ";
					}
					rs.close();
					
					synQueryNEW = synQueryNEW.substring(0, synQueryNEW.length() - 2);
				} 
				catch (Exception e) { }
			}
			else
				synQueryNEW = synQuery;
			
			String[] arr = synQueryNEW.split(", ");
			String sqlUPDATE = "";
			for( int i = 0; i < arr.length; i++ )
			{
				sqlUPDATE += "b." + arr[i] + " = " + "a." + arr[i] + ", ";
			}
			
			sqlUPDATE = sqlUPDATE.substring(0, sqlUPDATE.length() - 2);
			query = " update b set " + sqlUPDATE + 
					" from " + fromTable + " a inner join " + toTable + " b on a.pk_seq = b.pk_seq " + 
					" where a." + columnName + " = '" + value + "' ";
			
		}
		else if( isDelete )
		{
			query = "delete " + toTable + " where " + columnName + " = '" + value + "' ";
		}
		
		System.out.println("::: THUC HIEN SYN: " + query);
		if( !db.update(query) )
		{
			if( !sudungDBNGOAI )
				db.shutDown();
			return "3. Lỗi khi xử lý dữ liệu";
		}
		
		if( !sudungDBNGOAI )
			db.shutDown();
		
		return "";
	}
	
	public static String SynData(geso.dms.distributor.db.sql.dbutils db, String fromTable, String toTable, String columnName, String value, 
			String Insert_Update_Delete, boolean isDelete )
	{
		//Insert_Update_Delete: 0 -- Insert, 1 -- Update, 2 -- Delete.
		//Trường hợp Delete, mà không phải xóa dòng khỏi db, thì sử dụng như update, biến isDelete = false 
		
		boolean sudungDBNGOAI = true;
		if( db == null )
		{
			db = new geso.dms.distributor.db.sql.dbutils();
			sudungDBNGOAI = false;
		}
		
		String query = "select synQuery from SynConfig where fromTable = '" + fromTable + "' and toTable = '" + toTable + "' ";
		ResultSet rs = db.get(query);
		String synQuery = "";
		if( rs != null )
		{
			try 
			{
				if( rs.next() )
				{
					synQuery = rs.getString("synQuery");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				if( !sudungDBNGOAI )
					db.shutDown();
				
				return "1. Lỗi khi xử lý dữ liệu: " + e.getMessage();
			}
		}
		
		if( synQuery.trim().length() <= 0 )
		{
			/*if( !sudungDBNGOAI )
				db.shutDown();
			return "2. Lỗi khi xử lý dữ liệu";*/
			
			//Nếu chưa khai báo thì tự hiểu là SYN tất cả các cột
			synQuery = "*";
		}
		
		//LINK SANG DMS
		toTable = erpLinked + toTable;
		
		if( Insert_Update_Delete.equals("0") )
		{
			if( synQuery.equals("*") ) //Đồng bộ nguyên bảng
				query = "insert " + toTable + " select " + synQuery + " from " + fromTable + " where " + columnName + " = '" + value + "' ";
			else
				query = "insert " + toTable + " ( " + synQuery + " ) select " + synQuery + " from " + fromTable + " where " + columnName + " = '" + value + "' ";
		}
		else if( Insert_Update_Delete.equals("1") || ( Insert_Update_Delete.equals("2") && !isDelete ) )
		{
			String synQueryNEW = "";
			if( synQuery.equals("*") ) //Đồng bộ nguyên bảng
			{
				query = "SELECT COLUMN_NAME " +
						"FROM INFORMATION_SCHEMA.COLUMNS  " +
						"WHERE TABLE_NAME = '" + fromTable + "' ";
				rs = db.get(query);
				try 
				{
					while( rs.next() )
					{
						synQueryNEW += rs.getString("COLUMN_NAME") + ", ";
					}
					rs.close();
					
					synQueryNEW = synQueryNEW.substring(0, synQueryNEW.length() - 2);
				} 
				catch (Exception e) { }
			}
			else
				synQueryNEW = synQuery;
			
			String[] arr = synQueryNEW.split(", ");
			String sqlUPDATE = "";
			for( int i = 0; i < arr.length; i++ )
			{
				sqlUPDATE += "b." + arr[i] + " = " + "a." + arr[i] + ", ";
			}
			
			sqlUPDATE = sqlUPDATE.substring(0, sqlUPDATE.length() - 2);
			query = " update b set " + sqlUPDATE + 
					" from " + fromTable + " a inner join " + toTable + " b on a.pk_seq = b.pk_seq " + 
					" where a." + columnName + " = '" + value + "' ";
			
		}
		else if( isDelete )
		{
			query = "delete " + toTable + " where " + columnName + " = '" + value + "' ";
		}
		
		System.out.println("::: THUC HIEN SYN: " + query);
		if( !db.update(query) )
		{
			if( !sudungDBNGOAI )
				db.shutDown();
			return "3. Lỗi khi xử lý dữ liệu";
		}
		
		if( !sudungDBNGOAI )
			db.shutDown();
		
		return "";
	}
	
	public static void main(String[] arg)
	{
		UtilitySyn util = new UtilitySyn();
		geso.dms.center.db.sql.dbutils db = new dbutils();
		String msg = util.SynData(db, "TEST01", "TEST01", "PK_SEQ", "1", "1", false);
		
		System.out.println("::: MSG SYN: " + msg);
	}
	
}
