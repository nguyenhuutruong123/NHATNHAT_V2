package geso.dms.center.util;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
public class ChuyenNgu implements Serializable
{
	//public  static final String host = "localhost";
	public  static final String host = "118.69.168.124";
	public static final int port = 16379;
	public static final int timeout = 1000;
	public static String pass = "R3D1S_11869168124";
	public static String default_langue = "vi";

	//public static final Jedis jedis = new Jedis( host,port,2000); 

	//vi
	//en
	//zh
	public static boolean chuyennguMode = false; // true là sử dụng false là ko dùng
	public static boolean getChuyenNguMode()
	{
		return chuyennguMode;
	}

	public static String getDefault_langue() {
		return default_langue;
	}

	public static Jedis getJedis() {
		if(getChuyenNguMode())
		{
			Jedis jedis = new Jedis( host,port,timeout * 10 );
	
			if (pass.length() > 0) {
				jedis.auth(pass);
			}
	
			return jedis;
		}
		return null;
	}
	public static Jedis getJedis(int timeout) {
		if(getChuyenNguMode())
		{
			Jedis jedis = new Jedis( host,port,timeout);
	
			if (pass.length() > 0) {
				jedis.auth(pass);
			}
	
			return jedis;
		}
		return null;
	}

	public static long set(String key, String languageId, String value)
	{

		key = key.toUpperCase();
		if (value != null)
			value = value.trim();

		return  getJedis().hset(key, languageId, value);

		/*		//if(jedis.hget(key,languageId) == null)
			return  jedis.hset(key, languageId, value);

		//return -1;
		 */			
		//return -1;
	}

	public static long set(String key, String languageId, String value, Jedis j)
	{
		
		key = key.toUpperCase();
		if (value != null)
			value = value.trim();
		long a=  j.hset(key, languageId, value);
		System.out.println("key="+key +"--"+value + ", row = " + a);
		return a;

		/*		//if(jedis.hget(key,languageId) == null)
			return  jedis.hset(key, languageId, value);

		//return -1;
		 */			
		//return -1;
	}

	public static boolean checkExits(String key, String languageId, Jedis jedis)
	{
		return jedis.hget(key,languageId)!= null;
	}


	public static String get(String key, String languageId)
	{	
		if(getChuyenNguMode())
		{
			String kq = key;
			try
			{
				key = key.toUpperCase();
				Jedis j = getJedis();
				if(j != null && j.hget(key,languageId) != null)
					kq = j.hget(key,languageId);
				j.quit();
				j.close();
				j= null;
				return kq;
			}
			catch (Exception e) {
				return key;
			}
		}else
		{
			return key;
		}


	}

	public static String get(String key, HttpSession session)
	{	if(getChuyenNguMode())
		{
			String nnId = (String)session.getAttribute("nnId"); 
			if(nnId == null) 
				nnId =getDefault_langue();			
			return get(key, nnId);
		}else
		{
			return key;
		}
	}
	public static String get(String key,  HttpSession session, Jedis j)
	{	if(getChuyenNguMode())
		{
			String nnId = (String)session.getAttribute("nnId"); 
			if(nnId == null) 
				nnId = getDefault_langue();
			return get( key,  nnId,  j);
		}else
		{
			return key;
		}
	}
	public static String get(String key, String languageId, Jedis j)
	{	if(getChuyenNguMode())
		{
			String kq = key;
	
			try
			{
				key = key.toUpperCase();
				//System.out.println("val = " +j.hget(key,languageId));
				if(j != null && j.hget(key,languageId) != null)
					kq = j.hget(key,languageId);
				//	System.out.println("key =" + key + ", languageId =  " +languageId +" , val =" + kq);
	
			}
			catch (Exception e) {
				kq = key;
				e.printStackTrace();			
			}
			return kq;
		}else
		{
			return key;
		}
	}

	public static long set(String key, HttpSession session, String value)
	{
		String nnId = (String)session.getAttribute("nnId"); 
		if(nnId == null) 
			nnId = getDefault_langue();	
		return set(key,nnId,value);
	}

	

	public static void Save()
	{
		Jedis j = null;
		try
		{
			j = new Jedis( host,port,timeout); 	
			j.auth(pass);
			j.save();
			j.close();
			j = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Save(Jedis j)
	{
		try
		{	
			j.save();
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		/*
		 * System.out.println( set("sản phẩm",getDefault_langue(),"Product") );
		 * System.out.println( get("CƠ BẢN",getDefault_langue()) ); Save();
		 */
		//Jedis j = getJedis();
		
	}
	
	
}
