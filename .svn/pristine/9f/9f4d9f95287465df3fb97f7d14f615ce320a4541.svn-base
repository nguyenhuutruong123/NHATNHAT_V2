package geso.dms.center.util;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;




public class Csrf {
	private static String _tokenName = "_token";
	private int _timeLive = 3600;
	private String _tokenValue;
	private boolean _IS_POST;
	private boolean _IS_GET;
	private boolean _IS_USE;
	private HttpSession session;
	private Cookie[] cookie = null;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public Csrf(HttpServletRequest request,HttpServletResponse response, boolean _IS_POST, boolean _IS_GET, boolean _IS_USE) {
		super();
		this._IS_POST = _IS_POST;
		this._IS_GET = _IS_GET;
		this._IS_USE = _IS_USE;
		this.cookie = request.getCookies();
		this.session = request.getSession();
		this.request = request;
		this.response = response;
		
		try {
			this.request.setCharacterEncoding("UTF-8");
			this.response.setCharacterEncoding("UTF-8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!_IS_USE && _IS_POST ) {
			return;
		}
		if(!_IS_USE && !_IS_GET) {
			return;
		}
		
		try {
			
			this.initToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		
	}
	
	public String getIpAddress(){
		String ipAddress = request.getHeader("Remote_Addr");
		
		if(ipAddress != null) return ipAddress; 
		
		ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");

		if (ipAddress == null) {
		    ipAddress = request.getRemoteAddr();
		}
		
		return ipAddress;
	}
	
	public String getUserAgent(String hash){
		
		if(hash.split(".").length < 3) return "";
		return hash.split(".")[2];
	}
	
	public void initToken() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{

		String _token_in_cookie = getCookie(this.cookie,this._tokenName);
		long millis = System.currentTimeMillis();
		String ipAddress = getIpAddress();
		String User_Agent = request.getHeader("User-Agent");
		//System.out.println("User-Agent:"+User_Agent);
		String Referer = request.getHeader("Referer");
		String hash = String.valueOf(millis).concat(".").concat(ipAddress).concat(".").concat(User_Agent);
		
		//System.out.println( " :: hash " + hash);
		if(isNullOrEmpty(_token_in_cookie)){
			// Khởi tạo mới
			if(is_IS_USE())
			{
				this._tokenValue = Utility.dongMa(hash);
				Cookie c = new Cookie(this._tokenName, this._tokenValue);
				c.setMaxAge(_timeLive);
				this.response.addCookie(c);
			}
			
		}else{
			this._tokenValue = _token_in_cookie;		
		}		
	}	
	MultipartRequest multi  = null;
	public boolean __validate_post(){
	    
    	if (GlobalValue.getDevmode())
    		return true;
    	
    	if(request.getContentType().contains("multipart/form-data") && this.multi == null )
    		return true;
    	
    	/*if(!_IS_USE)
    		return true;
    	
    	if(!_IS_POST)
    		return true;
    	
    	if(request.getRequestURI().contains(".jsp")){
			return true;
		}*/
    	
    //	System.out.println(" :: request.getMethod() " + request.getMethod());
    	
    	if(request.getMethod().equals("POST"))
    	{
    		// Kiểm tra có tồn tại token không
    		String _token_in_request = "";
    		if(this.multi != null)
    			_token_in_request = multi.getParameter(this._tokenName);
    		else
    			_token_in_request = request.getParameter(this._tokenName);
    		
    		String _token_in_cookie = getCookie(this.cookie,this._tokenName);
    	//	System.out.println(" :: _token_in_request " + _token_in_request);
    	//	System.out.println(" :: _token_in_cookie " + _token_in_cookie);
            if (isNullOrEmpty(_token_in_request) || isNullOrEmpty(_token_in_cookie)){
                return false;
            }
            // Nếu tokeon không phù hợp
            if (!_token_in_request.equals(_token_in_cookie)){
                return false;
            }
            
            String User_Agent = request.getHeader("User-Agent");
            
            /*try {
				String dehash = Utility.giaiMa(_token_in_cookie);
				
				System.out.println(":: dehas " + dehash);
				
				String _user = getUserAgent(dehash);
				
				if(!_user.equals(User_Agent)){
					return false;
				}
				
				
			} catch (InvalidKeyException | NoSuchAlgorithmException
					| NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException e) {
				e.printStackTrace();
				return false;
			}*/
            
            
    	}
    	return true;
    }

    
    public boolean __validate_post_mul(MultipartRequest multi) {

    	if (GlobalValue.getDevmode())
    		return true;

    	if(request.getMethod().equals("POST")){
    		String	_token_in_request = multi.getParameter(this._tokenName);



    		String _token_in_cookie = getCookie(this.cookie,this._tokenName);
    		if (isNullOrEmpty(_token_in_request) || isNullOrEmpty(_token_in_cookie)){
    			return false;
    		}
    		// Nếu tokeon không phù hợp
    		if (!_token_in_request.equals(_token_in_cookie)){
    			return false;
    		}

    		String User_Agent = request.getHeader("User-Agent");




    	}
    	return true;
    }
    
    
    public  boolean __validate_get(){
    	
    	/*if (GlobalValue.getDevmode())
    		return true;
    	
    	if(!_IS_USE)
    		return true;
    	
    	if(!_IS_GET)
    		return true;*/
    	
    	if(request.getMethod().equals("GET")){
    	//	System.out.println("request.getRequestURI() " + request.getRequestURI());
    	//	System.out.println("request.getPathInfo()" +  request.getPathInfo());
    		if( request.getRequestURI().equals("/KHUONGDUY/")){
    			return true;
    		}
    		
    		if( request.getRequestURI().equals("/KHUONGDUY/RouterSvl")){
    			return true;
    		}
    		
    		if(request.getRequestURI().contains(".jsp")){
    			return true;
    		}
    		
    	//	System.out.println(" :: request.getQueryString() " + request.getQueryString());
    		
    		if(isNullOrEmpty( request.getQueryString())){
    			return true;
    		}
    		
    		
    		// Kiểm tra có tồn tại token không
    		String _token_in_request = Utility.getParameterFromSearch(request.getQueryString(),this._tokenName);
    		String _token_in_cookie = getCookie(this.cookie,this._tokenName);
    	//	System.out.println(" :: _token_in_request " + _token_in_request);
    	//	System.out.println(" :: _token_in_cookie " + _token_in_cookie);
            if (isNullOrEmpty(_token_in_request) || isNullOrEmpty(_token_in_cookie)){
                return false;
            }
         // Nếu tokeon không phù hợp
            if (!_token_in_request.equals(_token_in_cookie)){
                return false;
            }
            
            String User_Agent = request.getHeader("User-Agent");
            
           /* try {
				String dehash = Utility.giaiMa(_token_in_cookie);
				
				System.out.println(":: dehas " + dehash);
				
				String _user = getUserAgent(dehash);
				
				if(!_user.equals(User_Agent)){
					return false;
				}
				
				
			} catch (InvalidKeyException | NoSuchAlgorithmException
					| NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException e) {
				e.printStackTrace();
				return false;
			}*/
            
            
    	}
    	return true;
    }
    
    public String _create_link(String url){
    	
    	
    	String u = "";
    	if(url.contains("?")) u= url + '&' + this._tokenName + '=' + this._tokenValue;
    	
    	else
    		u = url + '?' + this._tokenName + '=' + this._tokenValue;
    //	System.out.println("url " + u);
    	return u;
    }
    
    public boolean isNullOrEmpty(String value){
    	if(value == null)
    		return true;
    	if(value.trim().length() == 0)
    		return true;
    	return false;
    }
	
	public static String getCookie(Cookie[] cookie, String key){
		if(cookie == null) return null;
		for(Cookie c : cookie){
			if(c.getName().equals(key)){
				return c.getValue();
			}
		}
		return null;
	}
	
	public String get_tokenName() {
		return _tokenName;
	}
	public static String get_tokenName_Static() {
		return _tokenName;
	}
	public void set_tokenName(String _tokenName) {
		this._tokenName = _tokenName;
	}

	public String get_tokenValue() {
		return _tokenValue;
	}
	public void set_tokenValue(String _tokenValue) {
		this._tokenValue = _tokenValue;
	}
	public boolean is_IS_POST() {
		return _IS_POST;
	}
	public void set_IS_POST(boolean _IS_POST) {
		this._IS_POST = _IS_POST;
	}
	public boolean is_IS_GET() {
		return _IS_GET;
	}
	public void set_IS_GET(boolean _IS_GET) {
		this._IS_GET = _IS_GET;
	}
	public boolean is_IS_USE() {
		return _IS_USE;
	}
	public void set_IS_USE(boolean _IS_USE) {
		this._IS_USE = _IS_USE;
	}
	
	public static boolean validate_static_post(String _token_in_request, HttpServletRequest request )
	{
		 String _token_in_cookie =  Csrf.getCookie(request.getCookies(),Csrf.get_tokenName_Static());
	     return _token_in_request.equals(_token_in_cookie);
	}
}
