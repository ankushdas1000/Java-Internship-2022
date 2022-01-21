import java.util.*; 
import java.sql.*;  
class Practice1{  
public static void main(String args[])
{  
try{  
	
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection(  
	"jdbc:mysql://localhost:3306/PRACTICE","root","root");  
	
	boolean check=true;
	
	
	while(check)
	{
	System.out.println("1.Insert student data into Student table");
	System.out.println("2. Update student data into Student table");
	System.out.println("3. Delete student data from Student table");
	System.out.println("4. Get a list of all students");
	System.out.println("5. Get one student information depending on the student id filter.");
	System.out.println("6. EXIT");
	Scanner sc= new Scanner(System.in);
	int ch=sc.nextInt();  
	
	switch(ch)
	{
	case 1:
		System.out.println("Enter Student Number or ID");
		int StudNo=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Student Name");
		String StudentName=sc.nextLine();
		System.out.println("Enter Date Of Birth(DD-MM-YYYY)");
		String StudentDOB=sc.nextLine();
		System.out.println("Enter Date Of Joining(DD-MM-YYYY)");
		String StudentDOJ=sc.nextLine();
		
		PreparedStatement pstmt=con.prepareStatement("insert into STUDENT values(?,?,?,?)"); 
		pstmt.setInt(1,StudNo);  
		pstmt.setString(2,StudentName);
		pstmt.setString(3,StudentDOB);
		pstmt.setString(4,StudentDOJ);
		
		int i=pstmt.executeUpdate();  
		System.out.println(i+" records inserted");  
		
		break;
	case 2:
		System.out.println("Enter Student Number or ID");
		int Student_No=sc.nextInt();
		System.out.println("Enter Student DOB to be updated");
		String Student_DOB=sc.nextLine();
		
		PreparedStatement upstmt = con.prepareStatement("Update STUDENT set STUDENT_DOB=? where STUDENT_NO=?");
		upstmt.setString(1,Student_DOB);
		upstmt.setInt(2, Student_No);
	    upstmt.executeUpdate();
	      
	      System.out.println("Database updated successfully ");
		break;
	case 3:
		System.out.println("Enter Student Number or ID");
		int StudentNo=sc.nextInt();
		
		PreparedStatement dpstmt=con.prepareStatement("delete from STUDENT where STUDENT_NO=?");
		dpstmt.setInt(1,StudentNo);
		dpstmt.executeUpdate();
		System.out.println("Record Deleted");
		break;
	case 4:
		Statement stmt=con.createStatement();  		
		ResultSet rs=stmt.executeQuery("select * from STUDENT");  
		while(rs.next())
		{
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)); 
		}
		break;
	case 5:
		System.out.println("Enter Student Number or ID");
		int Stud_No=sc.nextInt();
		 PreparedStatement spstmt = con.prepareStatement("select * from STUDENT where STUDENT_NO=?");
		 spstmt.setInt(1, Stud_No);
		 ResultSet res = spstmt.executeQuery();
		 while (res.next())
		 {
	          System.out.println("Stuend ID=" + res.getInt(1));
	          System.out.println("Student Name=" + res.getString(2));
	          System.out.println("Student DOB=" + res.getString(3));
	          System.out.println("Student DOJ=" + res.getString(4));
	          
	     }
		break;
	case 6:
		check=false;
		System.out.println("EXIT");
		break;
	default:
		System.out.println("Wrong Choice");
	}
	 
	con.close();
	}	
	}

		catch(Exception e)
		{
		System.out.println(e);
		}  
}  
}  
