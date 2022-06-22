package geso.dms.center.util;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import geso.dms.center.db.sql.*;

public class SendMail
{
	  protected String SMTP_HOST_NAME ;
	  protected String SMTP_AUTH_USER ;
	  protected String SMTP_AUTH_PWD  ;
	  private  String emailFromAddress; 
  
	  public SendMail()
	  {
	  }
  
	  public void postMail( String[] recipients, String subject, String message ) throws MessagingException
	  {
		  boolean debug = false;
	      SMTP_HOST_NAME = "mail.geso.us";
	      SMTP_AUTH_USER = "luonghv@geso.us";
	      SMTP_AUTH_PWD = "cuibap@123";
	      emailFromAddress = "luonghv@geso.us";
	
	     //Set the host smtp address
	        Properties props = new Properties();
	        props.put("mail.smtp.host", SMTP_HOST_NAME);
	        props.put("mail.smtp.auth", "true");
	
	        Authenticator auth = new SMTPAuthenticator();
	        Session session = Session.getDefaultInstance(props, auth);
	
	        session.setDebug(debug);
	
	    // create a message
	        Message msg = new MimeMessage(session);
	
	    // set the from and to address
	        InternetAddress addressFrom = new InternetAddress(emailFromAddress);
	    
	        InternetAddress[] addressTo = new InternetAddress[1];
		    for (int i = 1; i <= new Integer(recipients[0]).intValue(); i++)
		    {
		        System.out.println(recipients[i]);
		        addressTo[0] = new InternetAddress(recipients[i]);
		        msg.setFrom(addressFrom);
		        msg.setRecipients(Message.RecipientType.TO, addressTo);
		        msg.setSubject(subject);
		        msg.setContent(message, "text/html; charset=UTF-8");//msg.setContent(message, "text/plain");
		        try{
		          Transport.send(msg);
		        }catch(SendFailedException err){err.toString();}
		    }
	 }

	
	  public String postMailHTML( String To, String Cc, String subject, String mesg ) throws MessagingException
	    {
		  SMTP_HOST_NAME = "mail.geso.us";
	      SMTP_AUTH_USER = "luonghv@geso.us";
	      SMTP_AUTH_PWD = "cuibap@123";
	      emailFromAddress = "luonghv@geso.us";
	      
    	Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.ssl.trust", "*");		
		props.put("mail.smtp.port", "25");
	 
			/*Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
				}
			  });*/
			
			Session session = Session.getDefaultInstance(props, new SMTPAuthenticator());
			
			try 
			{
				MimeMessage message = new MimeMessage(session);
				message.setHeader("Content-Type", "text/html; charset=UTF-8");
				
				message.setFrom(new InternetAddress(emailFromAddress));
				message.setContent(message, "text/html; charset=UTF-8");
				
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
				if(Cc.trim().length() > 0)
					message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(Cc));
				
				message.setSubject(subject, "UTF-8");
				message.setContent(mesg, "text/html; charset=UTF-8");
				
				Transport.send(message);
	 
				System.out.println("Gui MAIL thanh cong toi: " + To);
				return "";
	 
			} 
			catch (Exception e) 
			{
				//throw new RuntimeException(e);
				System.out.println("Lỗi gửi mail to " + e.toString());
				return "Lỗi gửi mail to " + e.toString();
			}
	    }
  
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
	
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}
	
	public static void main(String[] arg)
	{
		SendMail sendMAIL = new SendMail();
		
		try 
		{
			String msg = sendMAIL.postMailHTML("xuantvt@geso.us,oanhnt@geso.us", "luonghv@geso.us", "TEST", "ABC 123");
			//sendMAIL.postMail(new String[]{"xuantvt@geso.us"}, "TEST", "ABC 123");
			//System.out.println("MSG::: " + msg);
			
			System.out.println("MSG::: ");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


