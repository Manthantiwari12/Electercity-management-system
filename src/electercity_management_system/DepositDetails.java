package electercity_management_system;

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


public class DepositDetails extends JFrame implements ActionListener{
	
	Choice meternumber,cmonth;
	JTable table;
	JButton search,print,back;
	public DepositDetails() {
		super("Deposit Details");
		setSize(700, 700);
		setLocation(400, 70);
		
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		JLabel lblmeterno = new JLabel("Search By Meter Number");
		lblmeterno.setBounds(20, 20, 150, 25);
		add(lblmeterno);
		meternumber = new Choice();
		meternumber.setBounds( 180, 22, 150, 25);
		add(meternumber);
		
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer");
			while (rs.next()) {
				meternumber.add(rs.getString("meter_no"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JLabel lblmonth = new JLabel("Search By Month");
		lblmonth.setBounds(400, 20, 100, 25);
		add(lblmonth);
		cmonth = new Choice();
		cmonth.setBounds( 520, 22, 150, 25);
		cmonth.add("January");
		cmonth.add("February ");
		cmonth.add("March ");
		cmonth.add("April ");
		cmonth.add("May ");
		cmonth.add("June");
		cmonth.add("July ");
		cmonth.add("August ");
		cmonth.add("September ");
		cmonth.add("October ");
		cmonth.add("November");
		cmonth.add("December");
		add(cmonth);
		
		table = new JTable(); 
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from bill");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		add(table);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 100, 700, 600);
		add(sp);
		
		
		search = new JButton("Search");
		search.setBounds(20, 70, 80, 20);
		add(search);
		search.addActionListener(this);
		
		print = new JButton("Print");
		print.setBounds(120, 70, 80, 20);
		add(print);
		print.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(220, 70, 80, 20);
		add(back);
		back.addActionListener(this);
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource()== search ) {
			String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
			try {
				Connect con = new Connect();
				ResultSet rs = con.stmt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (ae.getSource()== print ) {
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
		new DepositDetails();
	}
}
