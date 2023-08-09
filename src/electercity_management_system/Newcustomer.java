package electercity_management_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Newcustomer extends JFrame implements ActionListener{
	
	JTextField tfname,tfaddress,tfcity,tfstate,tfemail,tfphnno ;
	JButton next,cancel;
	JLabel lblmeterno;
	public Newcustomer() {
		setSize(700, 500);
		setLocation(450, 175);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		JLabel heading = new JLabel("New Customer");
		heading.setBounds(180, 10,  200, 20);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 24 ));
		p.add(heading);
		
		JLabel lblname = new JLabel("customer Name");
		lblname.setBounds(100, 80,  100, 25);
		p.add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(240, 80, 200, 25);
		p.add(tfname);
		
		JLabel lblmeter = new JLabel("Meter Number");
		lblmeter.setBounds(100, 120,  100, 25);
		p.add(lblmeter);
		
		lblmeterno = new JLabel("");
		lblmeterno.setBounds(240, 120,  100, 25);
		p.add(lblmeterno);
		
		Random ran = new Random();
		long number = ran.nextLong()%1000000;
		lblmeterno.setText(""+ Math.abs(number));
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(100, 160,  100, 25);
		p.add(lbladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(240, 160, 200, 25);
		p.add(tfaddress);
		
		JLabel lblcity = new JLabel("City");
		lblcity.setBounds(100, 200,  100, 25);
		p.add(lblcity);
		
		tfcity = new JTextField();
		tfcity.setBounds(240, 200, 200, 25);
		p.add(tfcity);
		
		JLabel lblstate = new JLabel("State");
		lblstate.setBounds(100, 240,  100, 25);
		p.add(lblstate);
		
		tfstate = new JTextField();
		tfstate.setBounds(240, 240, 200, 25);
		p.add(tfstate);
		
		JLabel lblemail = new JLabel("Email ID ");
		lblemail.setBounds(100, 280,  100, 25);
		p.add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(240, 280, 200, 25);
		p.add(tfemail);
		
		JLabel lblphnno = new JLabel("Phone Number ");
		lblphnno.setBounds(100, 320,  100, 25);
		p.add(lblphnno);
		
		tfphnno = new JTextField();
		tfphnno.setBounds(240, 320, 200, 25);
		p.add(tfphnno);
		
		next = new JButton("Next");
		next.setBounds(120, 390, 100, 25);
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.addActionListener(this);
		p.add(next);
		
		cancel = new JButton("cancel");
		cancel.setBounds(250, 390, 100, 25);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		p.add(cancel);
		
		
		setLayout(new BorderLayout());
		add(p,"Center");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
		Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		add(image,"West");
		getContentPane().setBackground(Color.WHITE);
		
		
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == next) {
			String name = tfname.getText();
			String meterno = lblmeterno.getText();
			String address = tfaddress.getText();
			String city = tfcity.getText();
			String state = tfstate.getText();
			String emailid = tfemail.getText();
			String phnno = tfphnno.getText();
			
			String query1 = "insert into customer values('"+name+"','"+meterno+"','"+address+"','"+city+"','"+state+"','"+emailid+"','"+phnno+"')";
			String query2 = "insert into login (meter_no,name)values('"+meterno+"','"+name+"')";
			
			try {
				Connect con = new Connect();
				con.stmt.executeUpdate(query1);
				con.stmt.executeUpdate(query2);
				
				
				JOptionPane.showMessageDialog(null, "Customer Details added Sucessfully");
				
				setVisible(false);
				
				new Meterinfo(meterno);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			setVisible(false);
		}
		
	}



	public static void main(String[] args) {
		new Newcustomer();
	}
}
