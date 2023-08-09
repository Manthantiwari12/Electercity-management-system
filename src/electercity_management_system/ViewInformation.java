package electercity_management_system;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import java.awt.print.PrinterJob;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewInformation extends JFrame implements ActionListener{
	
	
	JButton cancel,print;
	
	
	
	public ViewInformation(String meternumber) {
		setSize(850,650);
		setLocation(350, 80);
		
		getContentPane().setBackground(Color.WHITE);
		
		setLayout(null);
		
		JLabel heading = new JLabel("View Customer Information");
		heading.setBounds(300, 0, 500, 40);
		add(heading);
		heading.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(70, 80, 100, 20);
		add(lblname);
		
		JLabel name = new JLabel("");
		name.setBounds(250, 80, 100, 20);
		add(name); 
		
		
		
		JLabel lblmeterno = new JLabel("Meter Number");
		lblmeterno.setBounds(70, 140, 100, 20);
		add(lblmeterno);
		
		JLabel meterno = new JLabel("");
		meterno.setBounds(250, 140, 100, 20);
		add(meterno); 
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(70, 200, 100, 20);
		add(lbladdress);
		
		JLabel address = new JLabel("");
		address.setBounds(250, 200, 100, 20);
		add(address); 
		
		JLabel lblcity = new JLabel("City");
		lblcity.setBounds(70, 260, 100, 20);
		add(lblcity);
		
		JLabel city = new JLabel("");
		city.setBounds(250, 260, 100, 20);
		add(city);
		
		JLabel lblstate = new JLabel("State");
		lblstate.setBounds(500, 80, 100, 20);
		add(lblstate);
		
		JLabel state = new JLabel("");
		state.setBounds(650, 80, 100, 20);
		add(state);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(500, 140, 100, 20);
		add(lblemail);
		
		JLabel email = new JLabel("");
		email.setBounds(650, 140, 100, 20);
		add(email);
		
		JLabel lblphnno = new JLabel("Phone ");
		lblphnno.setBounds(500, 200, 100, 20);
		add(lblphnno);
		
		JLabel phnno = new JLabel("");
		phnno.setBounds(650, 200, 100, 20);
		add(phnno);
		
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer where meter_no = '"+meternumber+"'");
			while (rs.next()) {
				name.setText(rs.getString("name"));
				meterno.setText(rs.getString("meter_no"));
				address.setText(rs.getString("address"));
				city.setText(rs.getString("city"));
				state.setText(rs.getString("state"));
				email.setText(rs.getString("email"));
				phnno.setText(rs.getString("phone"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		cancel = new JButton("Cancel");
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(450, 340, 200, 25);
		add(cancel);
		cancel.addActionListener(this);
		
		print = new JButton("Print Information");
		print.setBackground(Color.BLACK);
		print.setForeground(Color.WHITE);
		print.setBounds(230, 340, 200, 25);
		add(print);
		print.addActionListener(this);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		image.setBounds(20,350,600,300);
		add(image);
		
		
		
		
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==print) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setJobName("Print Data");
			job.setPrintable(new Printable() {
				
				@Override
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex){
					// TODO Auto-generated method stub
					
					if (pageIndex>0) {
						return Printable.NO_SUCH_PAGE;
					}
					Graphics2D g2=(Graphics2D)graphics;
					g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
					g2.scale(0.65, 0.85);
					paint(g2);
					
					return Printable.PAGE_EXISTS;
				}
			});
			boolean ok = job.printDialog();
			if (ok) {
				try {
					job.print();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}else {
			setVisible(false);
		}
		
	}



	public static void main(String[] args) {
		new  ViewInformation("");
	}
}
