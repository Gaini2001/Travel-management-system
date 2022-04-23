import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

//import com.sun.media.sound.Toolkit;

public class TravelManagement {
	static Connection connection=null;
	public static void Bus_info() {
		try {
	    	Scanner sc=new Scanner(System.in);
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","anurodh");
	     System.out.println("Enter the source:-");
	      String source=sc.nextLine();
	      System.out.println("Enter the destination:-");
	      String destination=sc.nextLine();
	      PreparedStatement statement = connection.prepareStatement("Select bus_number,source,destination,departure_time,arrival_time,total_seats,ticket_price from bus_info where source=? and destination=?");
	      statement.setString(1, source);
	      statement.setString(2, destination);
	      ResultSet rset = statement.executeQuery();
	      int i=0;
	      System.out.println("BusNo.    "+"    Source       "+"Dest.        "+"DepartTime    "+"ArrivalTime   "+"SeatAvail."+"   ticket_price ");
	       while(rset.next())
	       {
	    	   System.out.println(rset.getString(1)+"     "+rset.getString(2)+"      "+rset.getString(3)+"      "+rset.getString(4)+"       "+rset.getString(5)+"       "+rset.getInt(6)+"              "+rset.getInt(7));
	    	   i=1;
	       }
	       if(i==0)
	       {
	    	   System.out.println("Sorry!! No data available.");
	       }
	      
		}
		  catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	public static void Login()
	{
		try {
		  Scanner sc=new Scanner(System.in);
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","anurodh");
	      System.out.println("******WELCOME TO LOGIN PAGE*****");
	      System.out.println("\n");
	      System.out.println("Enter your email:-");
	      String email=sc.nextLine();
	      System.out.println("Enter your Password:-");
	      String password=sc.nextLine();
	      PreparedStatement prestmt=connection.prepareStatement("Select * from passenger where email=? and Binary password=?");
	      prestmt.setString(1, email);
	      prestmt.setString(2, password);
	      ResultSet rset=prestmt.executeQuery();
	      if(rset.next()==true)
	      {
	    	  System.out.println("You have successfully Logged in our bus service.\n");
	    	  boolean c=true;
	    	  while(c) {
	    	  System.out.println("1.Bus_Info");
	    	  System.out.println("2.Ticket_Booking");
	    	  System.out.println("Enter your choice.");
	    	  int ch=sc.nextInt();
	    	  switch(ch)
	    	  {
	    	  case 1:
	    		  TravelManagement.Bus_info();
	    		  System.out.println("Do you want to Book ticket(true/false).");
		    	  c=sc.nextBoolean();
	    		  break;
	    	  case 2:
	    		  TravelManagement.ticket_booking(email);
	    		  c=false;
	    		  break;
	    	  }
	    }
	      }
	      else {
	    	  {
	    		  System.out.println("Sorry !!! Either your email or password is incorrect.");
	    	  }
	      }
	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void ticket_booking(String email)
	{
		try {
			Scanner sc=new Scanner(System.in);
		     Class.forName("com.mysql.cj.jdbc.Driver");
		      connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","anurodh");
		      email="'"+email+"'";
		System.out.println("WELCOME TO TICKET BOOKING WINDOW");
		System.out.println("\n");
		System.out.println("Please enter the Bus number");
		String bus_number=sc.nextLine();
		String bus=bus_number;
		System.out.println("Please Make Payment.");
		System.out.println("1.SBI online.");
		System.out.println("2.Card Services.");
		System.out.println("3.UPI ");
		System.out.println("4.Others \n");
		String type=null;
		System.out.println("Enter your choice");
		int s=sc.nextInt();
		switch(s)
		{
		case 1: 
			type="SBI online";
			break;
		case 2:
			type="Card Services";
			break;
		case 3:
			type="UPI";
			break;
		case 4:
			System.out.println("Please specify the Trasaction type.");
			type=sc.nextLine();
			break;
		}
		System.out.println("Enter the Amount.");
		float amount=sc.nextFloat();
		int n=10;
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvwxyz";
		StringBuilder sBuilder=new StringBuilder(n);
			for(int i=0;i<n;i++)
			{
				int index=(int) ((int)str.length()*Math.random());
				sBuilder.append(str.charAt(index));
			}
			String paymentID=sBuilder.toString();	
			String query="Select passengerID,name from passenger where email="+email;
		Statement stmt=connection.createStatement();
		ResultSet rset=stmt.executeQuery(query);
		int ID=0;
		String name=null;
		while(rset.next())
		{
			ID=rset.getInt(1);
			name=rset.getString("name");
		}
		PreparedStatement statement=connection.prepareStatement("Insert into  payment Values(?,?,?,?)");
		statement.setFloat(1, amount);
		statement.setString(2, paymentID);
		statement.setString(3, type);
		statement.setInt(4, ID);
		statement.executeUpdate();
		String discription="Booked";
		String Que="Insert Into ticket(ticket_price,discription,paymentID,passengerID,bus_number) Values(?,?,?,?,?)";
		PreparedStatement pstatement=connection.prepareStatement(Que);
		pstatement.setFloat(1, amount);
		pstatement.setString(2, discription);
		pstatement.setString(3, paymentID);
		pstatement.setInt(4, ID);
		pstatement.setString(5, bus_number);
		pstatement.executeUpdate();
		System.out.println("Here Is your Ticket.");
		Statement statement2=connection.createStatement();
		bus_number="'"+bus_number+"'";
		int available=0;
		String source=null,destination=null,depart_time=null,arrival_time=null;
		String q="Select source,destination,departure_time,arrival_time,seat_availability from bus_info where bus_number="+bus_number;
		ResultSet rs=statement2.executeQuery(q);
		while(rs.next())
		{
			 source=rs.getString("source");
			 destination=rs.getString("destination");
			 depart_time= rs.getString("departure_time");
			 arrival_time=rs.getString("arrival_time");
			available=rs.getInt("seat_availability");
		}
		System.out.println("Your Ticket:-");
		System.out.println("Passenger: "+name);
		System.out.println("Source: "+source+" Destination: "+destination);
		System.out.println("Departure_Time: "+depart_time+" Arrial_Time: "+arrival_time);
		System.out.println("Amount: "+amount);
		if(available==0)
		{
			System.out.println("Sorry!!! No seat Available.");
			System.exit(0);
		}
		available=available-1;
		String avail=Integer.toString(available);
		PreparedStatement pres=connection.prepareStatement("Update bus_info set seat_availability=? where bus_number=?");
		pres.setInt(1, available);
		pres.setString(2,bus);
		int x=pres.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("THANK YOU");
		}
	}
	public static void register()
	{
		try {
			Scanner sc=new Scanner(System.in);
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","anurodh");
		      System.out.println("*****WELCOME TO REGISTRATION PAGE*****");	      
		      System.out.println("\n");
		      String email,name,gender=null,address=null;
		      System.out.println("Please Enter your email*  (* mandatory)");
		      email=sc.nextLine();
		      if(email.contains("@gmail.com")!=true)
		      {
		    	  java.awt.Toolkit.getDefaultToolkit().beep();
		    	  System.out.println("Please Enter a valid Email ID");
		    	  email=sc.nextLine();
		      }
		      System.out.println("Please Enter your Name*");
		      name=sc.nextLine();
		      System.out.println("Please Enter your age*");
		      int age=sc.nextInt();
		      sc.nextLine();
		      System.out.println("Please Enter your gender");
		      gender=sc.nextLine();
		      System.out.println("Please Enter your contactNo*");
		      String contactNo=sc.nextLine();
		      int len=contactNo.length();
		      while(len!=10)
		      {
		    	  java.awt.Toolkit.getDefaultToolkit().beep();
		    	  System.out.println("Contact number should not less than 10 digit.");
		    	  contactNo=sc.nextLine();
		    	  len=contactNo.length();	  
		      }
		      System.out.println("Please Enter your address");
		      address=sc.nextLine();
		      System.out.println("Please Enter your password*");
		      String password=sc.nextLine();
		      PreparedStatement pstmt=connection.prepareStatement("Insert into passenger(name,age,gender,contactno,email,address,password) Values(?,?,?,?,?,?,?)");
		      pstmt.setString(1, name);
		      pstmt.setInt(2, age);
		      pstmt.setString(3, gender);
		      pstmt.setString(4, contactNo);
		      pstmt.setString(5, email);
		      pstmt.setString(6, address);
		      pstmt.setString(7, password);
		      int i=pstmt.executeUpdate();
		      if(i!=0)
		      {
		    	  System.out.println("You have SuccessFully created Account.THANK YOU.");
		      }
		      else 
		    	  {
		    		  System.out.println("Sorry!!!some Information is missing.");
		    	  }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
  public static void main(String args[]) throws Exception {
	  Scanner SC=new Scanner(System.in);
	  boolean choice=true;
	  while(choice==true) {
	  System.out.println("Welcome to Our Bus Service!!");
	  System.out.println("\n");
	  System.out.println("1.Bus-Info");
	  System.out.println("2.Login");
	  System.out.println("3.Register\n");
	  System.out.println("Note: In Order to Book a ticket customer need to register and login.\n");
	  System.out.println("Enter your choice:-");
	  int i=SC.nextInt();
	  switch(i)
	  {
	  case 1:
		  TravelManagement.Bus_info();
		  System.out.println("\nNote:- In order to book a ticket, Customer need To register and Login in Application.\n");
	       System.out.println("THANK YOU.");
		  break;
	  case 2:
		  TravelManagement.Login();
		  break;
	  case 3:
		 TravelManagement.register();
		  break;
		 default:
			 System.out.println("Please enter a valid choice.");
	  }
	  System.out.println("Do you want to continue.?(yes/no)");
	  String s=SC.next();
	  if(s.equalsIgnoreCase("yes"))
	  {
		  choice=true;
	  }
	  else {
		  {
			  choice=false;
		  }
	}
	  
  }
	  System.out.println("THANK YOU FOR USING OUR BUS SERVICE!!!!");
	  
  }  
}
