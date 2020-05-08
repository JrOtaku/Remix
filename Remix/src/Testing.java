import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Testing {
	
	public static void main(String args[])
	{
		DatabaseConnectionService dbservice = new DatabaseConnectionService();
		dbservice.connect();
		UserService userservice = new UserService(dbservice);
		userservice.register("Test", "Test123");
		
		//String query = "Select Name from Artist";
		//try {
		//	Statement statement = dbservice.getConnection().createStatement();
		//	ResultSet resultset = statement.executeQuery(query);
		//	while(resultset.next())
		//	{
		//		System.out.println(resultset.getString(1));
		//	}
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		
		dbservice.closeConnection();
	}

}
