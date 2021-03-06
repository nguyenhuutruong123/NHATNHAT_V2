package geso.dms.center.beans.chuyennpp.imp;

import geso.dms.center.beans.chuyennpp.IChuyenNpp;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChuyenNpp
  extends Stockintransit
  implements IChuyenNpp
{
  String id;
  String nppIdFrom;
  String nppIdTo;
  String msg;
  String ngayks;
  dbutils db;
  
  public ChuyenNpp()
  {
    this.id = "";
    this.nppIdFrom = "";
    this.nppIdTo = "";
    this.msg = "";
    this.ngayks = "";
    this.db = new dbutils();
  }
  
  public String getNppIdFrom()
  {
    return this.nppIdFrom;
  }
  
  public void setNppIdFrom(String nppIdFrom)
  {
    this.nppIdFrom = nppIdFrom;
  }
  
  public String getNppIdTo()
  {
    return this.nppIdTo;
  }
  
  public void setNppIdTo(String nppIdTo)
  {
    this.nppIdTo = nppIdTo;
  }
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public boolean TransferData()
  {
    try
    {
      this.db.getConnection().setAutoCommit(false);
      
      String query;
      boolean flag=false;
      
      if (this.ngayks.length() > 0)
      {
        query = "select count(*) from khoasongay where npp_fk ='" + this.nppIdTo + "'";
        System.out.println("Check ngayks " + query);
       ResultSet rs = this.db.get(query);
        if (rs != null)
        {
          rs.next();
          if (rs.getInt(1) > 0) {
            flag = true;
          }
          rs.close();
        }
        if (flag)
        {
          query = 
            "select max(ngayks) as ngayks, cast(DATEADD(day,1, max(ngayks)) as nvarchar(11)) as ngaytiep, DATEDIFF(day, max(ngayks), '" + this.ngayks + "') AS chenhks, " + "DATEDIFF(day, Replace(convert(char(10), GETDATE() , 102), '.', '-'), '" + this.ngayks + "') AS chenhht " + "from khoasongay where npp_fk ='" + this.nppIdTo + "'";
          System.out.println("Check ngayks " + query);
          rs = this.db.get(query);
          int chenhks = 0;
          int chenhht = 0;
          String ngayksgn = "";
          if (rs != null)
          {
            if (rs.next())
            {
              ngayksgn = rs.getString("ngayks");
              chenhks = rs.getInt("chenhks");
              chenhht = rs.getInt("chenhht");
            }
            rs.close();
          }
          if ((chenhks >= 2) || (chenhht > 0))
          {
            this.msg = "Ng??y kh??a s??? ph???i sau ng??y kh??a s??? g???n nh???t 1 ng??y v?? nh??? h??n ho???c b???ng ng??y hi???n t???i";
            this.db.getConnection().rollback();
            return false;
          }
        }
        else
        {
          query = 
            "insert into KhoaSoNgay(NgayKsGanNhat,NgayKs,NgayTao,NguoiTao,Npp_FK,Chon,DoanhSo) select '" + this.ngayks + "','" + this.ngayks + "','" + getDate() + "','" + getuserId() + "','" + this.nppIdTo + "',0,0 ";
          System.out.println("Chen Ngay Khoa So " + query);
          if (!this.db.update(query))
          {
            this.db.getConnection().rollback();
            this.msg = ("Chuyen khach hang bi loi " + query);
            return false;
          }
        }
      }
      
      if(!this.nppIdFrom.equals(nppIdTo))
      {
      
	      query = "insert into chuyennpp(npp_fk_from ,npp_fk_to,nguoitao,ngaytao) values('" + 
	        this.nppIdFrom + "','" + this.nppIdTo + "','" + getuserId() + "','" + getDate() + "')";
	      if (!this.db.update(query))
	      {
	        this.db.getConnection().rollback();
	        this.msg = ("Chuyen khach hang bi loi " + query);
	        return false;
	      }
	      query = "select scope_identity()";
	      ResultSet rs = this.db.get(query);
	      if (rs != null)
	      {
	        rs.next();
	        this.id = rs.getString(1);
	        rs.close();
	      }
	      
	      
	      
	      query = 
	      
	
	        "insert into chuyennpp_khachhang(chuyennpp_fk,npp_fk_from,khachhang_fk,smartId) select '" + this.id + "',npp_fk,pk_Seq as khid,smartId " + " from khachhang " + "where npp_fk='" + this.nppIdFrom + "' ";
	      if (!this.db.update(query))
	      {
	        this.db.getConnection().rollback();
	        this.msg = ("Chuyen khach hang bi loi " + query);
	        return false;
	      }
	      query = 
	      
	        "insert into chuyennpp_daidienkinhdoanh(chuyennpp_fk,npp_fk_from,ddkd_fk) select '" + this.id + "',npp_fk,pk_Seq as ddkd_fk " + " from daidienkinhdoanh " + "where npp_fk='" + this.nppIdFrom + "' ";
	      if (!this.db.update(query))
	      {
	        this.db.getConnection().rollback();
	        this.msg = ("Chuyen khach hang bi loi " + query);
	        return false;
	      }
	      query = 
	        "insert into chuyennpp_tuyenbanhang(chuyennpp_fk,npp_fk_from,tbh_fk) select '" + this.id + "',npp_fk,pk_Seq as tbh_fk " + " from tuyenbanhang " + "where npp_fk='" + this.nppIdFrom + "' ";
	      if (!this.db.update(query))
	      {
	        this.db.getConnection().rollback();
	        this.msg = ("Chuyen khach hang bi loi " + query);
	        return false;
	      }
      }
      
      query = "select count(*) from khachhang where npp_fk='" + this.nppIdTo + "'";
      ResultSet rsCheck = this.db.get(query);
      flag = false;
      if (rsCheck != null)
      {
        rsCheck.next();
        if (rsCheck.getInt(1) > 0) {
          flag = true;
        }
        rsCheck.close();
      }
      System.out.println("111.Check Kh" + query);
      if (flag)
      {
        int maxSmarId = 0;
        query = "select MAX(CAST (SMARTID AS NUMERIC(18,0))) from khachhang where npp_fk='" + this.nppIdTo + "'";
        rsCheck = this.db.get(query);
        if (rsCheck != null)
        {
          rsCheck.next();
          maxSmarId = rsCheck.getInt(1);
          rsCheck.close();
        }
        System.out.println("112." + query);
        if (maxSmarId == 0)
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
        query = "select pk_Seq from khachhang where npp_fk='" + this.nppIdFrom + "'";
        rsCheck = this.db.get(query);
        if (rsCheck != null)
        {
          while (rsCheck.next())
          {
            maxSmarId++;
            query = "update khachhang set smartId='" + maxSmarId + "',npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "' and pk_seq='" + rsCheck.getString("pk_seq") + "' ";
            if (!this.db.update(query))
            {
              this.db.getConnection().rollback();
              this.msg = ("Chuyen khach hang bi loi " + query);
              return false;
            }
            System.out.println("113." + query);
          }
          rsCheck.close();
        }
        System.out.println("113." + query);
        query = "update tuyenbanhang set npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "'";
        if (!this.db.update(query))
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
        System.out.println("114." + query);
        query = "update daidienkinhdoanh set npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "'";
        if (!this.db.update(query))
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
      }      
      else
      {
        query = "update khachhang set npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "'  ";
        if (!this.db.update(query))
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
        System.out.println("113." + query);
        query = "update tuyenbanhang set npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "'";
        if (!this.db.update(query))
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
        System.out.println("113." + query);
        query = "update daidienkinhdoanh set npp_fk='" + this.nppIdTo + "' where npp_fk='" + this.nppIdFrom + "'";
        if (!this.db.update(query))
        {
          this.db.getConnection().rollback();
          this.msg = ("Chuyen khach hang bi loi " + query);
          return false;
        }
        System.out.println("113." + query);
      }
      this.db.getConnection().commit();
      this.db.getConnection().setAutoCommit(true);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return true;
  }
  
  public boolean Revert()
  {
    return true;
  }
  
  public void closeDB() {}
  
  public String getNgayKs()
  {
    return this.ngayks;
  }
  
  public void setNgayKs(String ngayKs)
  {
    this.ngayks = ngayKs;
  }
}
