import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DbIO {
	
	private final String url = "jdbc:mysql://aws02:3306/?autoReconnect=true&useSSL=false";
	private final String user = "root";
	private final String password = ""; //TO-DO: should supply correct password here
	private final String databaseName = "calendar";
	private final String tableName = "schedule";
	private final String driverName = "com.mysql.cj.jdbc.Driver";
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String sqlText = null;
	
	public DbIO() {
		try {
			Class.forName(this.driverName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkIfDatabaseExists () {
		sqlText = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + databaseName + "'";
		boolean result = false;
		
		try {
			resultSet = statement.executeQuery(sqlText);
			result = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int createDatabase () {
		sqlText = "CREATE SCHEMA `" + databaseName + "`";
		int result = -1;
		
		try {
			result = statement.executeUpdate(sqlText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkIfTableExists () {
		sqlText = String.format("SELECT TABLE_SCHEMA, TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s'"
				, databaseName, tableName);
		boolean result = false;
		
		try {
			resultSet = statement.executeQuery(sqlText);
			result = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int createTable () {
		sqlText = "CREATE TABLE `" + databaseName +"`.`" + tableName +"` (\r\n" + 
				"  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `date` date NOT NULL,\r\n" + 
				"  `schedule` mediumtext COLLATE utf8_unicode_ci,\r\n" + 
				"  PRIMARY KEY (`id`)\r\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='스케쥴 관리 테이블'";
		int result = -1;
		
		try {
			result = statement.executeUpdate(sqlText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int addSchedule (String date, String text) {
		sqlText = String.format("INSERT INTO %s.%s (date, schedule) VALUES ('%s', '%s')", databaseName, tableName, date, text);
		int result = -1;
		try {
			result = statement.executeUpdate(sqlText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		return result;
	}
	
	public List<String> getScheduleForDay (String date) {
		sqlText = String.format("SELECT schedule FROM %s.%s WHERE date='%s'", databaseName, tableName, date);
		List<String> result = new ArrayList<String>();
		try {
			resultSet = statement.executeQuery(sqlText);
			while (resultSet.next()) {
				String colSchedule = resultSet.getString(1);
				result.add(colSchedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getScheduledDateForMonth (int year, int month) {
		sqlText = String.format("SELECT DISTINCT(date) FROM %s.%s WHERE year(date) = '%s' AND month(date) = '%s'"
				, databaseName, tableName, year, month);
		List<String> result = new ArrayList<String>();
		try {
			resultSet = statement.executeQuery(sqlText);
			while (resultSet.next()) {
				String colDate = resultSet.getString(1);
				result.add(colDate.substring(8, 10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void closeAll () {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
