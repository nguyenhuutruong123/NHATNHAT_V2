package geso.dms.center.servlets.phanbokhuyenmai;

import geso.dms.center.beans.ctkhuyenmai.ICtkhuyenmaiList;
import geso.dms.center.beans.phanbokhuyenmai.IPhanbokhuyenmai;
import geso.dms.center.beans.phanbokhuyenmai.imp.Phanbokhuyenmai;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;

import geso.dms.center.db.sql.*;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import jxl.Cell;
import jxl.Sheet;

public class PhanbokhuyenmaiSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "c:\\upload\\excel\\";

	public PhanbokhuyenmaiSvl()
	{
		super();
	}

	private Utility util = new Utility();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		IPhanbokhuyenmai pbkmBean = new Phanbokhuyenmai();
		String querystring = request.getQueryString();
		System.out.println("querystring : "+ querystring+" - view : "+ util.getView(querystring));
		
		String view = util.getView(querystring);
		if(view == null) view = "";
		pbkmBean.setView(view);
		
		String userId = util.getUserId(querystring);
		pbkmBean.setUserId(userId);
		pbkmBean.init();
		session.setAttribute("pbkm", pbkmBean);
		session.setAttribute("schemeId", "0");
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKhuyenMai.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
	
		String contentType = request.getContentType();
		Utility util = new Utility();

		String userId = (String) session.getAttribute("userId");
		IPhanbokhuyenmai pbkmBean = new Phanbokhuyenmai();

		String action = util.antiSQLInspection(request.getParameter("action"));

		System.out.println("action"+action);
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
			String ctkmId = "";
			String[] ctkmIds = multi.getParameterValues("ctkmId");
			if (ctkmIds != null)
			{
				for (int i = 0; i < ctkmIds.length; i++)
					ctkmId += ctkmIds[i] + ",";
				if (ctkmId.length() > 0)
					ctkmId = ctkmId.substring(0, ctkmId.length() - 1);
			}
			pbkmBean.setSchemeId(ctkmId);

			 userId = (String) session.getAttribute("userId");
			 pbkmBean.setUserId(userId);
			
			String vungId = util.antiSQLInspection(multi.getParameter("vungId"));
			pbkmBean.setVungId(vungId);
			
			String view = util.antiSQLInspection(multi.getParameter("view"));
			if(view == null) view = "";
			pbkmBean.setView(view);

			String kvId = util.antiSQLInspection(multi.getParameter("khuvucId"));
			pbkmBean.setKvId(kvId);

			String tungay = util.antiSQLInspection(multi.getParameter("tungay"));
			pbkmBean.setTungay(tungay);

			String denngay = util.antiSQLInspection(multi.getParameter("denngay"));
			pbkmBean.setDenngay(denngay);


			String loaingansach = util.antiSQLInspection(multi.getParameter("loaingansach"));
			pbkmBean.setLoaingansach(loaingansach);

			String nppIdSearch = util.antiSQLInspection(multi.getParameter("nppIdSearch"));
			if (nppIdSearch == null)
				nppIdSearch = "";
			pbkmBean.setNppIdSearch(nppIdSearch);
			
			String phanbotdv = util.antiSQLInspection(multi.getParameter("phanbotdv"));
			if (phanbotdv == null)
				phanbotdv = "";
			pbkmBean.setPhanboTDV(phanbotdv);

			userId = util.antiSQLInspection(multi.getParameter("userId"));
			session.setAttribute("userId", userId);

			Enumeration files = multi.getFileNames();
			String filename = "";
			System.out.println("__userId" + userId);
			System.out.println("____+___"+action);
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				System.out.println("File  " + UPLOAD_DIRECTORY + filename);
			}
			if (filename != null && filename.length() > 0)
			{	
				System.out.println("phanbotdv : "+ phanbotdv);
				if(phanbotdv.trim().equals("1"))
				{
					pbkmBean.setMessage(this.readExcel_TDV(UPLOAD_DIRECTORY + filename, pbkmBean));
				}
				else
				{
					pbkmBean.setMessage(this.readExcel(UPLOAD_DIRECTORY + filename, pbkmBean));
				}
			}
			pbkmBean.setUserId(userId);
			pbkmBean.init();
			session.setAttribute("pbkm", pbkmBean);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKhuyenMai.jsp";
			response.sendRedirect(nextJSP);
			return;

		} 
		
		//--- check user
	    String view = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("view")));
		if (view == null) {
			view = "";
		}
		pbkmBean.setView(view);
		
		if (action.equals("phanbo"))
		{
			String vungId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("vungId")));
			pbkmBean.setVungId(vungId);

			String kvId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("khuvucId")));
			pbkmBean.setKvId(kvId);

			String tungay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("tungay")));
			pbkmBean.setTungay(tungay);

			String denngay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("denngay")));
			pbkmBean.setDenngay(denngay);

			String phanbo = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("phanbo")));
			pbkmBean.setPhanbo(phanbo);
			
			String phanbotdv = util.antiSQLInspection(request.getParameter("phanbotdv"));
			if (phanbotdv == null)
				phanbotdv = "";
			pbkmBean.setPhanboTDV(phanbotdv);


			String loaingansach = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("loaingansach")));
			pbkmBean.setLoaingansach(loaingansach);
			
			String nppIdSearch = util.antiSQLInspection(request.getParameter("nppIdSearch"));
			if (nppIdSearch == null)
				nppIdSearch = "";
			pbkmBean.setNppIdSearch(nppIdSearch);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			pbkmBean.setNppId(nppId);
			
			String activeTab = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("activeTab")));
			if (activeTab == null)
				activeTab = "";
			pbkmBean.setActiveTab(activeTab);
			

			String ctkmId = "";
			String[] ctkmIds = request.getParameterValues("ctkmId");
			if (ctkmIds != null)
			{
				for (int i = 0; i < ctkmIds.length; i++)
					ctkmId += ctkmIds[i] + ",";
				if (ctkmId.length() > 0)
					ctkmId = ctkmId.substring(0, ctkmId.length() - 1);
			}
			pbkmBean.setSchemeId(ctkmId);

			String[] nppIs = request.getParameterValues("nppId");
			String[] ctkmId_pb = request.getParameterValues("ctkmId.pb");
			String[] ngansach = request.getParameterValues("ngansach");
			String[] loaingansachpb = request.getParameterValues("loaingansach.pb");

			dbutils db = new dbutils();
			System.out.println("ctkmId_pd _ :"+ctkmId_pb);
			pbkmBean.setUserId(userId);
			if(ctkmId_pb!=null)
			{
				for(int i=0;i<nppIs.length;i++)
				{
					try 
					{
						db.getConnection().setAutoCommit(false);
						String nganSACH="";
						if(loaingansachpb[i].equals("0"))
							nganSACH="99999999999";
						else 
							nganSACH=ngansach[i].replaceAll(",", "");
						System.out.println("ewrwerewr nha pdfs " + nppIs[i]);
						String sql = "select count(*) as sodong from PHANBOKHUYENMAI PB where ctkm_fk = '" + ctkmId_pb[i] + "' AND PB.NPP_FK = " + nppIs[i] + "  ";
						System.out.println("check ck km "+sql);
						ResultSet rsCHECK = db.get(sql);
						int soDONG = 0;
						if(rsCHECK != null)
						{
							if(rsCHECK.next())
								soDONG = rsCHECK.getInt("sodong");
						}
						rsCHECK.close();
						
						
						String  query = "";
						
						if(soDONG <= 0)
						{
							query = "INSERT INTO PHANBOKHUYENMAI(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
									"values('" + ctkmId_pb[i] + "','" + nppIs[i]+ "','"+nganSACH+"', 0, 0, '"+userId+"', dbo.GetLocalDate(DEFAULT))";
						}
						else
						{
							query = 
											"\n  update a set ngansach = '"+nganSACH+"', nguoisua = '"+userId+"', ngaysua =  dbo.GetLocalDate(DEFAULT) " +
											"\n  from phanbokhuyenmai a inner join ctkhuyenmai c on c.pk_seq=a.ctkm_fk "+
											
											"\n outer apply " +
											"\n (	" +
											"\n		select sum(soxuat)soxuat, sum(tonggiatri)tonggiatri " +
											"\n		from  " +
											"\n 	(" +
											"\n			select dhkm.donhangId,max(soxuat) soxuat, sum(dhkm.tonggiatri) tonggiatri " +
											"\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId " +
											"\n			where dh.trangthai !=2 and dh.npp_fk =  a.npp_fk and dhkm.ctkmid = a.ctkm_fk and not exists ( select 1 from donhangtrave where trangthai = 3 and donhang_fk = dh.pk_seq) " +
											"\n			group by dhkm.donhangId " +
											"\n 	)ss " +
											"\n )ss	" +
											
											"where a.ctkm_fk = '" + ctkmId_pb[i] + "' and a.npp_fk='" + nppIs[i] +"' " +
											"  and  "+nganSACH+" >= 	case when c.phanbotheodonhang =0 then isnull(ss.tonggiatri,0) 	else  isnull(ss.soxuat,0) end 	";
						}
						if (db.updateReturnInt(query)<=0) 
						{
							System.out.println("query loi = " + query);
							db.getConnection().rollback();
							pbkmBean.setMessage(" Ng??n s??ch kh??ng ????? vui l??ng ??i???u ch???nh l???i ");
						}
						
						
						if(soDONG <= 0)
						{
							query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaytao) " +
									"values('" + ctkmId_pb[i] + "','" + nppIs[i]+ "',0, 0, 0, '"+userId+"', dbo.GetLocalDate(DEFAULT))";
						}
						else
						{
							query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
									"values('" + ctkmId_pb[i] + "','" + nppIs[i]+ "',0, 0, 0, '"+userId+"', dbo.GetLocalDate(DEFAULT))";
						}
						if (!db.update(query))
						{
							db.getConnection().rollback();
							pbkmBean.setMessage(" Ng??n s??ch kh??ng ????? vui l??ng ??i???u ch???nh l???i ");
						}
						db.getConnection().commit();
						db.getConnection().setAutoCommit(true);

					} catch (SQLException e) 
					{
						e.printStackTrace();
						try {
							db.getConnection().rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			
				//Th??ng b??o khi ph??n b??? th??nh c??ng
				if(pbkmBean.getMessage().trim().length() <= 0){
					pbkmBean.setMessage("???? ph??n b??? th??nh c??ng! ");
				}
			}
			
			
			pbkmBean.init();
			pbkmBean.createSchemeRS();
			pbkmBean.createPhanBoRs();
			session.setAttribute("pbkm", pbkmBean);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKhuyenMai.jsp";
			response.sendRedirect(nextJSP);
		}
		else if(action.equals("xuatexcel"))
		{
			String tungay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("tungay")));
			pbkmBean.setTungay(tungay);

			String denngay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("denngay")));
			pbkmBean.setDenngay(denngay);
			
			String ctkmId = "";
			String[] ctkmIds = request.getParameterValues("ctkmId");
			if (ctkmIds != null)
			{
				for (int i = 0; i < ctkmIds.length; i++)
					ctkmId += ctkmIds[i] + ",";
				if (ctkmId.length() > 0)
					ctkmId = ctkmId.substring(0, ctkmId.length() - 1);
			}
			pbkmBean.setSchemeId(ctkmId);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=PhanBoKM.xls");
		
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			try {
				TaoBaoCao_excel(request,workbook, pbkmBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream out = response.getOutputStream();
			workbook.save(out);
		}
		else
		{
			String vungId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("vungId")));
			pbkmBean.setVungId(vungId);

			String kvId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("khuvucId")));
			pbkmBean.setKvId(kvId);

			String tungay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("tungay")));
			pbkmBean.setTungay(tungay);

			String denngay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("denngay")));
			pbkmBean.setDenngay(denngay);

			String nppIdSearch = util.antiSQLInspection(request.getParameter("nppIdSearch"));
			if (nppIdSearch == null)
				nppIdSearch = "";
			pbkmBean.setNppIdSearch(nppIdSearch);
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			pbkmBean.setNppId(nppId);

			
			String phanbo = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("phanbo")));
			pbkmBean.setPhanbo(phanbo);

			String loaingansach = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("loaingansach")));
			pbkmBean.setLoaingansach(loaingansach);
			String ctkmId = "";
			String[] ctkmIds = request.getParameterValues("ctkmId");
			if (ctkmIds != null)
			{
				for (int i = 0; i < ctkmIds.length; i++)
					ctkmId += ctkmIds[i] + ",";
				if (ctkmId.length() > 0)
					ctkmId = ctkmId.substring(0, ctkmId.length() - 1);
			}
			pbkmBean.setSchemeId(ctkmId);
			pbkmBean.setUserId(userId);
			pbkmBean.init();
			pbkmBean.createSchemeRS();
			pbkmBean.createPhanBoRs();
			session.setAttribute("pbkm", pbkmBean);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKhuyenMai.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	private String readExcel_TDV(String fileName, IPhanbokhuyenmai pbkmBean)
	{
		System.out.println("-- readExcel_TDV");
		dbutils db = new dbutils();
		try
		{
			String msg="";
			File inputWorkbook = new File(fileName);
			jxl.Workbook w = null;
			w = jxl.Workbook.getWorkbook(inputWorkbook);
			jxl.Sheet sheet = w.getSheet(0);
			int sodong = sheet.getRows();
			db.getConnection().setAutoCommit(false);
			for (int i = 4; i < sodong; i++)
			{
				Cell[] cells = sheet.getRow(i);
				if (cells != null)
				{
					String scheme = getValue(sheet, 0, i).trim();
					String nppid = getValue(sheet, 1, i).trim();
					String tdvid = getValue(sheet, 3, i).trim();
					String ngansach = getValue(sheet, 5, i).trim().replaceAll(",", "");

					String query="select  count(*) as sl from ctkhuyenmai where scheme='"+scheme+"' ";
					System.out.println("quer ---- "+query);
					ResultSet rs=db.get(query);
					rs.next();
					int check=rs.getInt("sl");
					rs.close();
					if(check==0)
					{
						db.getConnection().rollback();
						msg=" Scheme "+scheme+" kh??ng t???n t???i vui l??ng ki???m tra l???i !!!  ";
						pbkmBean.setMessage(msg);
						db.shutDown();
						return msg;
					
					}
					else
					{
						System.out.println("nppid la "+nppid);
						 query="select  pk_Seq from ctkhuyenmai where scheme='"+scheme+"' ";
						  rs=db.get(query);
						 rs.next();
						String ctkmId=rs.getString("pk_Seq");
							rs.close();
						//String nppId = htbNppId.get(nppMa);
						//Hashtable<String, String> htbNpp_Ctkm = get_nhapp_duockhaibao(ctkmId);
		
						if (nppid.trim().length() > 0 && tdvid.trim().length() > 0 && ctkmId.trim().length() > 0 && ngansach.trim().length() > 0 && !scheme.contains("Total"))
						{

							String sql = "select count(*) as sodong from PHANBOKHUYENMAI PB where ctkm_fk = '" + ctkmId + "' AND PB.NPP_FK = " + nppid + " AND PB.DDKD_FK = '"+ tdvid +"'  ";
							System.out.println("check ck km "+sql);
							ResultSet rsCHECK = db.get(sql);
							int soDONG = 0;
							if(rsCHECK != null)
							{
								if(rsCHECK.next())
									soDONG = rsCHECK.getInt("sodong");
							}
							rsCHECK.close();
							
							
							  query = "";
							
							if(soDONG <= 0)
							{
								query = "\n INSERT INTO PHANBOKHUYENMAI(CTKM_FK,NPP_FK, DDKD_FK, NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
										"\n select a.ctkm_fk ,a.npp_fk, a.ddkd_fk, '"+ngansach+"', 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT) " +
										"\n from ctkm_npp a where  a.ctkm_fk = '" + ctkmId + "' and a.npp_fk='" + nppid +"' and a.chon = 1 "
									  + " and exists ( select 1 from ddkd_npp ddnpp where ddnpp.ddkd_fk = '"+ tdvid +"' and npp_fk = '"+ nppid +"' ) ";
							}
							else
							{
								query = 
												"\n update a set ngansach = '"+ngansach+"', nguoisua = '"+pbkmBean.getUserId()+"', ngaysua =  dbo.GetLocalDate(DEFAULT) " +
												"\n   from phanbokhuyenmai a inner join ctkhuyenmai c on c.pk_seq=a.ctkm_fk " +
												"\n 	inner join ctkm_npp x on x.ctkm_fk = a.ctkm_fk and x.npp_fk = a.npp_fk and x.chon = 1  "+
												
												"\n outer apply " +
												"\n (	" +
												"\n		select sum(soxuat)soxuat, sum(tonggiatri)tonggiatri " +
												"\n		from  " +
												"\n 	(" +
												"\n			select dhkm.donhangId,max(soxuat) soxuat, sum(dhkm.tonggiatri) tonggiatri " +
												"\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId " +
												"\n			where dh.trangthai !=2 and dh.npp_fk =  a.npp_fk and dhkm.ctkmid = a.ctkm_fk and not exists ( select 1 from donhangtrave where trangthai = 3 and donhang_fk = dh.pk_seq) " +
												"\n			group by dhkm.donhangId " +
												"\n 	)ss " +
												"\n )ss	" +
												
												"\n where a.ctkm_fk = '" + ctkmId + "' and a.npp_fk='" + nppid +"' " +
											    "\n and exists ( select 1 from ddkd_npp ddnpp where ddnpp.ddkd_fk = '"+ tdvid +"' and npp_fk = '"+ nppid +"' ) "+
												"\n   and  "+ngansach+" >= case when c.phanbotheodonhang =0 then isnull(ss.tonggiatri,0)  else isnull(ss.soxuat,0) end 	";
							}
							if (db.updateReturnInt(query)<=0) 
							{
								System.out.println("query error = "+ query);
								db.getConnection().rollback();
								msg=" Ng??n s??ch kh??ng ????? ho???c NPP kh??ng thu???c ctkm vui l??ng ??i???u ch???nh l???i ";
								pbkmBean.setMessage(msg);
								db.shutDown();
								return msg;
							}
							
							
							if(soDONG <= 0)
							{
								query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK, DDKD_FK, NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaytao) " +
										"values('" + ctkmId + "','" + nppid+ "','" + tdvid+ "',0, 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT))";
							}
							else
							{
								query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK, DDKD_FK, NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
										"values('" + ctkmId + "','" + nppid+ "','" + tdvid+ "', 0, 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT))";
							}
							if (!db.update(query))
							{
								db.getConnection().rollback();
								msg="L???i ch??n log vui l??ng upload l???i";
								db.shutDown();
								pbkmBean.setMessage(msg);
							}
						}
					}
					
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} catch (Exception e)
		{
			e.printStackTrace();
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.shutDown();
			return "Vui l??ng coi l???i file " + e.getMessage();
		}
		return "Ph??n b??? khuy???n m??i th??nh c??ng";
	
	}

	private String readExcel(String fileName, IPhanbokhuyenmai pbkmBean)
	{
		System.out.println("-- readExcel");
		dbutils db = new dbutils();
		try
		{
			String msg="";
			File inputWorkbook = new File(fileName);
			jxl.Workbook w = null;
			w = jxl.Workbook.getWorkbook(inputWorkbook);
			jxl.Sheet sheet = w.getSheet(0);
			int sodong = sheet.getRows();
			db.getConnection().setAutoCommit(false);
			for (int i = 4; i < sodong; i++)
			{
				Cell[] cells = sheet.getRow(i);
				if (cells != null)
				{
					String scheme = getValue(sheet, 0, i).trim();
					String nppid = getValue(sheet, 1, i).trim();
					String ngansach = getValue(sheet, 3, i).trim().replaceAll(",", "");

					String query="select  count(*) as sl from ctkhuyenmai where scheme='"+scheme+"' ";
					System.out.println("quer ---- "+query);
					ResultSet rs=db.get(query);
					rs.next();
					int check=rs.getInt("sl");
					rs.close();
					if(check==0)
					{
						db.getConnection().rollback();
						msg=" Scheme "+scheme+" kh??ng t???n t???i vui l??ng ki???m tra l???i !!!  ";
						pbkmBean.setMessage(msg);
						db.shutDown();
						return msg;
					
					}
					else
					{
						System.out.println("nppid la "+nppid);
						 query="select  pk_Seq from ctkhuyenmai where scheme='"+scheme+"' ";
						  rs=db.get(query);
						 rs.next();
						String ctkmId=rs.getString("pk_Seq");
							rs.close();
						//String nppId = htbNppId.get(nppMa);
						//Hashtable<String, String> htbNpp_Ctkm = get_nhapp_duockhaibao(ctkmId);
		
						if (nppid.trim().length() > 0 && ctkmId.trim().length() > 0 && ngansach.trim().length() > 0 && !scheme.contains("Total"))
						{

							String sql = "select count(*) as sodong from PHANBOKHUYENMAI PB where ctkm_fk = '" + ctkmId + "' AND PB.NPP_FK = " + nppid + "  ";
							System.out.println("check ck km "+sql);
							ResultSet rsCHECK = db.get(sql);
							int soDONG = 0;
							if(rsCHECK != null)
							{
								if(rsCHECK.next())
									soDONG = rsCHECK.getInt("sodong");
							}
							rsCHECK.close();
							
							
							  query = "";
							
							if(soDONG <= 0)
							{
								query = "\n INSERT INTO PHANBOKHUYENMAI(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
										"\n select a.ctkm_fk ,a.npp_fk ,'"+ngansach+"', 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT) " +
										"\n from ctkm_npp a where  a.ctkm_fk = '" + ctkmId + "' and a.npp_fk='" + nppid +"' and a.chon = 1  ";
							}
							else
							{
								query = 
												"\n update a set ngansach = '"+ngansach+"', nguoisua = '"+pbkmBean.getUserId()+"', ngaysua =  dbo.GetLocalDate(DEFAULT) " +
												"\n   from phanbokhuyenmai a inner join ctkhuyenmai c on c.pk_seq=a.ctkm_fk " +
												"\n 	inner join ctkm_npp x on x.ctkm_fk = a.ctkm_fk and x.npp_fk = a.npp_fk and x.chon = 1  "+
												
												"\n outer apply " +
												"\n (	" +
												"\n		select sum(soxuat)soxuat, sum(tonggiatri)tonggiatri " +
												"\n		from  " +
												"\n 	(" +
												"\n			select dhkm.donhangId,max(soxuat) soxuat, sum(dhkm.tonggiatri) tonggiatri " +
												"\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId " +
												"\n			where dh.trangthai !=2 and dh.npp_fk =  a.npp_fk and dhkm.ctkmid = a.ctkm_fk and not exists ( select 1 from donhangtrave where trangthai = 3 and donhang_fk = dh.pk_seq) " +
												"\n			group by dhkm.donhangId " +
												"\n 	)ss " +
												"\n )ss	" +
												
												"\n where a.ctkm_fk = '" + ctkmId + "' and a.npp_fk='" + nppid +"' " +
												"\n   and  "+ngansach+" >= case when c.phanbotheodonhang =0 then isnull(ss.tonggiatri,0)  else isnull(ss.soxuat,0) end 	";
							}
							if (db.updateReturnInt(query)<=0) 
							{
								System.out.println("query error = "+ query);
								db.getConnection().rollback();
								msg=" Ng??n s??ch kh??ng ????? ho???c NPP kh??ng thu???c ctkm vui l??ng ??i???u ch???nh l???i ";
								pbkmBean.setMessage(msg);
								db.shutDown();
								return msg;
							}
							
							
							if(soDONG <= 0)
							{
								query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaytao) " +
										"values('" + ctkmId + "','" + nppid+ "',0, 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT))";
							}
							else
							{
								query = "INSERT INTO PHANBOKHUYENMAI_LOG(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG, nguoisua, ngaysua) " +
										"values('" + ctkmId + "','" + nppid+ "',0, 0, 0, '"+pbkmBean.getUserId()+"', dbo.GetLocalDate(DEFAULT))";
							}
							if (!db.update(query))
							{
								db.getConnection().rollback();
								msg="L???i ch??n log vui l??ng upload l???i";
								db.shutDown();
								pbkmBean.setMessage(msg);
							}
						}
					}
					
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} catch (Exception e)
		{
			e.printStackTrace();
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.shutDown();
			return "Vui l??ng coi l???i file " + e.getMessage();
		}
		return "Ph??n b??? khuy???n m??i th??nh c??ng";
	}

	// lay cac nha phan phoi duoc khai bao
	private Hashtable<String, String> get_nhapp_duockhaibao(String ctkmId)
	{
		Hashtable<String, String> ht_nppdckhaibao = new Hashtable<String, String>();
		dbutils db = new dbutils();
		String sql_getnpp_dckhaibao = "SELECT CTKM_FK,NPP_FK FROM CTKM_NPP WHERE CTKM_FK='" + ctkmId + "' AND CHON=1 ";

		ResultSet rs = db.get(sql_getnpp_dckhaibao);
		try
		{
			if (rs != null)
			{
				while (rs.next())
				{
					ht_nppdckhaibao.put(rs.getString("npp_fk").trim(), rs.getString("ctkm_fk").trim());
				}
				rs.close();
			}
			db.shutDown();
		} catch (Exception er)
		{
			er.printStackTrace();
			System.out.print("Exception"+sql_getnpp_dckhaibao);
		}
		return ht_nppdckhaibao;

	}

	private Hashtable<String, String> getHtbCtkmId()
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		dbutils db = new dbutils();
		if (db.getConnection() != null)
		{
			// chi duoc lay nhung nhapp trong ctkm_npp tuong ung -- khong lay het
			ResultSet rs = db.get("SELECT SCHEME, PK_SEQ, LEVEL_PHANBO FROM CTKHUYENMAI WHERE LOAINGANSACH = 1  ");
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						if (rs.getString("scheme") != null)
						{
							ht.put(rs.getString("scheme"), rs.getString("pk_seq") + "_" + rs.getString("LEVEL_PHANBO") );
						}
					}
					rs.close();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			} 
			else
			{
				System.out.print("Error here!");
			}
			db.shutDown();
		} 
		else
		{
			System.out.print("Error here!");
		}
		return ht;
	}

	private Hashtable<String, String> getNPP()
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		dbutils db = new dbutils();
		if (db.getConnection() != null)
		{
			ResultSet rs = db.get("SELECT MA,TEN ,PK_SEQ FROM NHAPHANPHOI WHERE TRANGTHAI=1 AND PRIANDSECOND=0");
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						if (rs.getString("ma") != null)
						{
							ht.put(rs.getString("ma"), rs.getString("pk_seq"));
						}
					}
					rs.close();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			} else
			{
				System.out.print("Error here!");
			}
			db.shutDown();
		} else
		{
			System.out.print("Error here!");
		}
		return ht;
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getValue(Sheet sheet, int column, int row)
	{
		Cell cell;
		cell = sheet.getCell(column, row);
		try
		{
			return cell.getContents();
		} catch (Exception er)
		{
			return "0";
		}
	}
	
	private void TaoBaoCao_excel(HttpServletRequest request,com.aspose.cells.Workbook workbook,IPhanbokhuyenmai pbkm)throws Exception
	{
		try{
			
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("ThongTinCTKM");
			
			com.aspose.cells.Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 50.0f);
			com.aspose.cells.Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,"Ph??n b??? khuy???n m??i");
			Utility util = new Utility();
			
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ng??y t???o : "+ getDateTime());
			
			cell = cells.getCell("A2");
			cells.setColumnWidth(0, 20.0f);

			cells.setColumnWidth(4, 20.0f);
			cells.setColumnWidth(9, 15.0f);
			cells.setColumnWidth(8, 15.0f);
			cells.setColumnWidth(7, 15.0f);
			cells.setColumnWidth(6, 15.0f);
			ResultSet rs = null;
			dbutils db = new dbutils();
			String query=" select distinct a.SCHEME,d.PK_SEQ as MANPP_HT,d.TEN as TENNPP, ISNULL(CAST(ddkd.pk_seq AS VARCHAR(50)), '') as MA_DDKD, isnull(ddkd.ten,'') as TEN_DDKD, case when a.loaingansach =0  then 999999999 else  isnull(c.NGANSACH,0) end NGANSACH \n"+ 
						 "	from CTKHUYENMAI a \n " +
						 "	inner join CTKM_NPP b  on a.PK_SEQ=b.CTKM_FK and b.chon = 1 \n " +
						 "	inner join NHAPHANPHOI d on d.pk_seq=b.npp_fk \n"+
						 "	left join PHANBOKHUYENMAI c on c.CTKM_FK=a.PK_SEQ and c.NPP_FK=b.NPP_FK \n"+
						 "  left join daidienkinhdoanh ddkd on ddkd.pk_seq = c.ddkd_fk "+
						 "	where 1=1  \n";
				if(pbkm.getTungay().trim().length()>0)
				{
					query+=" and a.TUNGAY >= '"+pbkm.getTungay()+"' ";
				}
				if(pbkm.getDenngay().trim().length()>0)
				{
					query+=" and a.denngay <= '"+pbkm.getDenngay()+"' ";
				}
				if( pbkm.getSchemeId().trim().length()>0)
				{
					query+=" and a.pk_seq in  ( "+pbkm.getSchemeId()+" ) ";
				}
				query+=	 "	order by d.PK_SEQ"; 
			System.out.println("query dowload  "+query);
			
			 rs = db.get(query);
				
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			
			int countRow = 3;
	
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow,i-1 );cell.setValue(rsmd.getColumnName(i));
				cells.setColumnWidth(i, 20.0f);
				ReportAPI.setCellBackground(cell, new Color(70,130,180), BorderLineType.THIN, true, 0);	
			 
			}
			countRow ++;
				
			
			int stt = 0;
			while(rs.next())
			{
				boolean kt = false;
				boolean ck = true;
				stt++;
				String value = "";
				for(int i =1;i <=socottrongSql ; i ++)
				{
					cell = cells.getCell(countRow,i-1 );
					if(rsmd.getColumnName(i).equals("STT"))
					{					
						cell.setValue(stt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						//System.out.println("STT: "+stt);

					}else
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL)
					{
						cell.setValue(rs.getDouble(i));
						
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}

			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}

	
		}catch(Exception ex){
			
			System.out.println("Errrorr : "+ex.toString());
			ex.printStackTrace();
			throw new Exception("L???i kh??ng t???o ???????c b??o c??o !");
		}
	}
	

}
