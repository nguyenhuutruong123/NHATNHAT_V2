package geso.dms.center.beans.dontrahang.imp;

import geso.dms.center.beans.dontrahang.ICtkm;

public class Ctkm implements ICtkm{
	String id;
	String scheme;
	
	public Ctkm(){
		this.id="";
		this.scheme ="";
	}
	
	public Ctkm(String[] param){
		this.id="param[0]";
		this.scheme ="param[1]";
	}
	
	public Ctkm(String id, String scheme)
	{
		this.id = id;
		this.scheme =scheme;
	}

	
	
	public String getId() 
	{
		return this.id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getScheme() 
	{
		return this.scheme;
	}

	public void setScheme(String scheme) 
	{
		this.scheme = scheme;
	}
}