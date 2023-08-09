package electercity_management_system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;


public class CustomerDetails extends JFrame implements ActionListener{
	
	Choice meternumber,cmonth;
	JTable table;
	JButton search,print,back;
	public CustomerDetails() {
		super("Customer Details");
		setSize(1200, 650);
		setLocation(170, 80);
		setLayout(new BorderLayout());
		 
		
		table = new JTable(); 
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		add(table);
		
		JScrollPane sp = new JScrollPane(table);
		add(sp);
		
		print = new JButton("Print");
		add(print,BorderLayout.PAGE_END);
		print.addActionListener(this);
		
		
		back = new JButton("Back");
		add(back,BorderLayout.PAGE_START);
		back.addActionListener(this);
		
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource()== print ) {
			try {
				table.print();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			setVisible(false);
		}
	}


	public static void main(String[] args) {
		new CustomerDetails();
	}
}
