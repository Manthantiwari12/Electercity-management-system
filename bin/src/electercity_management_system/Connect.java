package electercity_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {
	Connection con;
	Statement stmt;
	public Connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "172005manu");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
