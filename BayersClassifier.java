import java.util.Random;
import java.util.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class BayersClassifier {
	//private static Random generator = new Random();
	public static void getStreamOfRandomIntsWithRange(int num, int min, int max) {
	    Random random = new Random();
	    random.ints(num,min, max).sorted().forEach(System.out::println);
	}

	public static void main(String[] args) {
		new Random();
	      try {
	         Class.forName("org.postgresql.Driver");
	         DriverManager
	            .getConnection("jdbc:postgresql://localhost:32768/postgres",
	            "postgres", "3");
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
	{

	    
	      // check next Gaussian value  
	     // System.out.println("Next Gaussian value: " + randomnum.nextGaussian());
	      
	      	      
	      System.out.println("Random number of Students Age: ");
	      BayersClassifier.getStreamOfRandomIntsWithRange(100,19,30);
	      
	      System.out.println(" Random number of Teachers Age: ");
	      BayersClassifier.getStreamOfRandomIntsWithRange(100,31,60);
	      {     
	try
	{
		/*
		c1 and c2 denote class 1 & class 2. 
		class1 :- He is a Student
		class2 :- He is a Teacher
		*/
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:ecompany");
		Statement s = (Statement) con.createStatement();
		ResultSet rs = null;
		int c1=0 ,c2=0 ,n=0;

		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				//Count of cases when predicted person is student in training set
				c1=Integer.parseInt(rs.getString(1));

		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				//Count of cases when predicted person is teacher in training set
				c2=Integer.parseInt(rs.getString(1)); 

		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				//Count of total cases in training set
				n = Integer.parseInt(rs.getString(1)); 

		float pc1 = (float)c1/n; //General probability for class c1
		float pc2 = (float)c2/n; //General probability for class c2

		System.out.println("c1= " +c1 +"\nc2="+c2+"\ntotal="+n);
		System.out.println("p(c1)="+pc1);
		System.out.println("p(c2)="+pc2);

		Scanner sc = new Scanner(System.in);
		int StudentAge, TeacherAge;
		String teacher, student,class1 = null, class2 = null;
		
		// Accept the parameter values for which Age is to be predicted
		
		System.out.println("Enter  Student age:");
		StudentAge = sc.nextInt();

		System.out.println("Enter  Teacher age:");
		TeacherAge = sc.nextInt();
		
		System.out.println("Enter student:(yes/no)");
		student = sc.next();
		
		System.out.println("Enter teacher:(yes/no)");
		teacher = sc.next();

		
		float pinc1=0,pinc2=0;
		//pinc1 = probability of prediction to be Student (if Age 19 <= 30)
		//pinc2 = probability of prediction to be Teacher (if Age 31 <= 60)
			
		pinc1 = pfind(StudentAge,student,"yes", class1);
		pinc2 = pfind(TeacherAge,teacher,"no", class2);

		pinc1 = pinc1 * pc1;
		pinc2 = pinc2 * pc2;

		// compare pinc1 & pinc2 and predict the class that person will or won't student
		if(pinc1 > pinc2)
			System.out.println("He is a Student");
		else
			System.out.println("He is a Teacher");

		((Connection) s).close();
		con.close();
	}
	catch(Exception e)
	{
		System.out.println("Exception:"+ e); 
	}
	finally {
		System.out.println("He is a Student");
	
	      }	
	      }
	}   
	      
@SuppressWarnings("resource")
public static float pfind(int StudentAge,String StudentName,String TeacherName,String TeacherAge)
{
	float ans = 0;
	try{
		new Scanner(System.in);
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:ecompany");
		Statement s = (Statement) con.createStatement();
		ResultSet rs = null;
		int a=0 , b=0 , c=0 , d=0 , total=0;
		
		/* 	Queries below are constructed using parameter values of age , student , class
			passed to function. Function finds probability for every individual parameter of provided class value 
			and using naive baye's theorem it calculates total probability */
		

		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				a=Integer.parseInt(rs.getString(1));
		// a = count of values in training set having Student age , class same as passed in argument

		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				b=Integer.parseInt(rs.getString(1));
		// b = count of values in training set having Teacher age , class same as passed in argument


		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				c=Integer.parseInt(rs.getString(1));
		// c = count of values in training set having probability if its a student or teacher , class same as passed in argument


		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				d=Integer.parseInt(rs.getString(1)); 
		s.execute();
		rs= ((java.sql.Statement) s).getResultSet();
		if(rs.next())
				total=Integer.parseInt(rs.getString(1)); //total no results

		ans = (float)a / (float)total  * (float)b /(float)total * (float)c /(float)total * (float)d /(float)total ;
		//calculating total probability by naive byes
		
		((Connection) s).close();
		con.close();
	}
	catch(Exception e)
	{
		System.out.println("Exception:"+ e);
	}
	return ans;
}

}
	
/*
 * public static double getRandomIntegerBetweenRange(double min, double max){
 * double x = (int)(Math.random()*((max-min)+1))+min; return x;
 */
	



