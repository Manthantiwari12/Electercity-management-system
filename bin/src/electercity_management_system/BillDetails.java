package electercity_management_system;

import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame{
	
	public BillDetails(String meter) {
		setSize(700,500);
		setLocation(400,200);
		getContentPane().setBackground(Color.WHITE);
		
		JTable table = new JTable();
		try {
			Connect con = new Connect();
			String query= "select * from bill where meter_no = '"+meter+"'";
			ResultSet rs = con.stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 700, 500);
		add(sp);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new BillDetails("");
	}
}
