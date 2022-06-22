package geso.dms.distributor.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test2() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession(false);
	    

	    if(session != null && !session.isNew()) {
	    	out.println(request.getRequestedSessionId());
	    } else {
	        out.println("expired");
	    }


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	public static void main(String[] arg) throws FileNotFoundException
	{
		String input="https://localhost:44384/";
		HubConnection hubConnection = HubConnectionBuilder.create(input)
		        .build();
		hubConnection.send("Send", input);
	}
	 public void parseFile(String fileName,String searchStr) throws FileNotFoundException{
	        Scanner scan = new Scanner(new File(fileName));
	        while(scan.hasNext()){
	            String line = scan.nextLine().toLowerCase().toString();
	            if(line.contains(searchStr)){
	                System.out.println(line);
	            }
	        }
	    }
	

}
