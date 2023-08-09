package electercity_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Paybill extends JFrame implements ActionListener {
	
	Choice cmonth;
	JButton pay,back;
	String meternumber;
	public Paybill(String meternumber) {
		this.meternumber=meternumber;
		setLayout(null);
		setSize(900, 600);
		setLocation(300, 150);
		
		JLabel heading = new JLabel("Electercity Bill");
		heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		heading.setBounds(120, 5, 400, 30);
		add(heading);
		
		JLabel lblmeterno = new JLabel("Meter Number");
		lblmeterno.setBounds(35, 80, 200, 20);
		add(lblmeterno);
		
		JLabel meterno = new JLabel("");
		meterno.setBounds(300, 80, 200, 20);
		add(meterno);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(35, 140, 200, 20);
		add(lblname);
		
		JLabel name = new JLabel("");
		name.setBounds(300, 140, 200, 20);
		add(name);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(35, 200, 200, 20);
		add(lblMonth);
		
		cmonth = new Choice();
		cmonth.setBounds(300, 200, 200, 25);
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
		
		
		JLabel lblUnits = new JLabel("Units");
		lblUnits.setBounds(35, 260, 200, 20);
		add(lblUnits);
		
		JLabel units = new JLabel("");
		units.setBounds(300, 260, 200, 20);
		add(units);
		
		JLabel lblTotalbill = new JLabel("Total Bill");
		lblTotalbill.setBounds(35, 320, 200, 20);
		add(lblTotalbill);
		
		JLabel totalbill = new JLabel("");
		totalbill.setBounds(300, 320, 200, 20);
		add(totalbill);
		
		JLabel lblstatus = new JLabel("Bill Status");
		lblstatus.setBounds(35, 380, 200, 20);
		add(lblstatus);
		
		JLabel status = new JLabel("");
		status.setBounds(300, 380, 200, 20);
		status.setForeground(Color.RED);
		add(status);
		
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer where meter_no='"+meternumber+"'");
			while (rs.next()) {
				meterno.setText(meternumber);
				name.setText(rs.getNString("name"));
			}
			rs = con.stmt.executeQuery("select * from bill where meter_no='"+meternumber+"' and month = '"+cmonth.getSelectedItem()+"'");
			while (rs.next()) {
				units.setText(rs.getNString("units"));
				totalbill.setText(rs.getNString("totalbill"));
				status.setText(rs.getString("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		cmonth.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ae) {
				// TODO Auto-generated method stub
				try {
					Connect con = new Connect();
					
					ResultSet rs = con.stmt.executeQuery("select * from bill where meter_no='"+meternumber+"' and month = '"+cmonth.getSelectedItem()+"'");
					while (rs.next()) {
						units.setText(rs.getNString("units"));
						totalbill.setText(rs.getNString("totalbill"));
						status.setText(rs.getString("status"));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		
		pay = new JButton("Pay");
		pay.setBackground(Color.BLACK);
		pay.setForeground(Color.WHITE);
		pay.setBounds(100, 460, 100, 25);
		add(pay);
		pay.addActionListener(this);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(230, 460, 100, 25);
		add(back);
		back.addActionListener(this);
		
		getContentPane().setBackground(Color.WHITE);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		image.setBounds(400, 120, 600, 300);
		add(image);
		
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==pay) {
			try {
				Connect con = new Connect();
				con.stmt.executeUpdate("update bill set status = 'paid' where meter_no = '"+meternumber+"' and month ='"+cmonth.getSelectedItem()+"'");
				
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			setVisible(false);
			new Paytm(meternumber);
			
			
		}else {
			setVisible(false);
		}
	}



	public static void main(String[] args) {
		new Paybill("");
	}
}
