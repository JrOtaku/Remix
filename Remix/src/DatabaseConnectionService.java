import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
	
	private Connection connection = null;
	//private final String URL = "jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=SodaBaseguajarea30;user=SodaBaseUserguajarea30;password={Password123}";
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";
	
	public DatabaseConnectionService()
	{
		
	}
	
	public boolean connect() {
		String URL = new String(SampleURL);
		URL = URL.replace("${dbServer}", "golem.csse.rose-hulman.edu");
		URL = URL.replace("${dbName}", "Remix-GV");
		URL = URL.replace("${user}", "SodaBaseUserguajarea30");
		URL = URL.replace("${pass}", "Password123");
		try {
			connection = DriverManager.getConnection(URL);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			//do nothing
		}
	}
	
	public boolean login()
	{
		return true;
	}

}
