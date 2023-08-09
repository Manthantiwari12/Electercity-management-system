package electercity_management_system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculatebill extends JFrame implements ActionListener {

	JTextField tfunits;
	JButton next, cancel;
	JLabel lblmnmae, lblladdress;
	Choice meterno,cmonth;

	public Calculatebill() {
		setSize(700, 430);
		setLocation(450, 175);

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173, 216, 230));
		add(p);

		JLabel heading = new JLabel("Calculate Electercity Bill");
		heading.setBounds(180, 10, 400, 20);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
		p.add(heading);

		JLabel lblmeterno = new JLabel("Meter Number");
		lblmeterno.setBounds(100, 80, 100, 25);
		p.add(lblmeterno);

		meterno = new Choice();
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer");
			while (rs.next()) {
				meterno.add(rs.getString("meter_no"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		meterno.setBounds(240, 80, 200, 25);
		p.add(meterno);

		JLabel lblname = new JLabel("Name");
		lblname.setBounds(100, 120, 100, 25);
		p.add(lblname);

		lblmnmae = new JLabel();
		lblmnmae.setBounds(240, 120, 200, 25);
		p.add(lblmnmae);

		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(100, 160, 100, 25);
		p.add(lbladdress);

		lblladdress = new JLabel();
		lblladdress.setBounds(240, 160, 200, 25);
		p.add(lblladdress);
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt
					.executeQuery("select * from customer where meter_no='" + meterno.getSelectedItem() + "'");
			while (rs.next()) {
				lblmnmae.setText(rs.getString("name"));
				lblladdress.setText(rs.getString("address"));
			}

		} catch (Exception ae) {
			// TODO: handle exception
			ae.printStackTrace();
		}
		meterno.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				try {
					Connect con = new Connect();
					ResultSet rs = con.stmt
							.executeQuery("select * from customer where meter_no='" + meterno.getSelectedItem() + "'");
					while (rs.next()) {
						lblmnmae.setText(rs.getString("name"));
						lblladdress.setText(rs.getString("address"));
					}

				} catch (Exception ae) {
					// TODO: handle exception
					ae.printStackTrace();
				}
			}
		});

		JLabel lblunits = new JLabel("Units Consumed");
		lblunits.setBounds(100, 200, 100, 25);
		p.add(lblunits);

		tfunits = new JTextField();
		tfunits.setBounds(240, 200, 200, 25);
		p.add(tfunits);

		JLabel lblmonth = new JLabel("Month");
		lblmonth.setBounds(100, 240, 100, 25);
		p.add(lblmonth);

		cmonth = new Choice();
		cmonth.setBounds(240, 240, 200, 25);
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
		
		p.add(cmonth);

		

		next = new JButton("Submit");
		next.setBounds(120, 320, 100, 25);
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.addActionListener(this);
		p.add(next);

		cancel = new JButton("Cancel");
		cancel.setBounds(250, 320, 100, 25);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		p.add(cancel);

		setLayout(new BorderLayout());
		add(p, "Center");

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
		Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		add(image, "West");
		getContentPane().setBackground(Color.WHITE);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == next) {
			String meternumber = meterno.getSelectedItem();
			String units = tfunits.getText();
			String month = cmonth.getSelectedItem();
			int totalbill = 0;
			int units_consumed =Integer.parseInt(units);

			
			String query = "select * from tax";

			try {
				Connect con = new Connect();
				
				ResultSet rs = con.stmt.executeQuery(query);
				while (rs.next()) {
					totalbill += units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
					totalbill += Integer.parseInt(rs.getString("meter_rent"));
					totalbill += Integer.parseInt(rs.getString("service_charge"));
					totalbill += Integer.parseInt(rs.getString("service_tax"));
					totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
					totalbill += Integer.parseInt(rs.getString("fixed_tax"));
				}

				

				

				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			String query2 = "insert into bill values('"+meternumber+"','"+month+"','"+units+"','"+totalbill+"','Not Paid')";
			try {
				Connect con = new Connect();
				con.stmt.executeUpdate(query2);
				JOptionPane.showMessageDialog(null, "Customer BIll Updated sucessfully");
				setVisible(false);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			setVisible(false);
		}

	}

	public static void main(String[] args) {
		new Calculatebill();
	}
}
