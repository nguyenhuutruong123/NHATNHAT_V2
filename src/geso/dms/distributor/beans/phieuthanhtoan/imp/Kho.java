package geso.dms.distributor.beans.phieuthanhtoan.imp;

import geso.dms.distributor.beans.phieuthanhtoan.IKho;

public class Kho implements IKho 
{
	String id;
	String makho;
	
	public Kho()
	{
		this.id = "";
		this.makho = "";
	}
	
	public Kho(String id, String makho)
	{
		this.id = id;
		this.makho = makho;
	}
	
	public String getId() 
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMakho() 
	{
		return this.makho;
	}

	public void setMakho(String makho) 
	{
		this.makho = makho;
	}

}
