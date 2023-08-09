package electercity_management_system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Meterinfo extends JFrame implements ActionListener{
	
	
	JButton submit;
	JLabel lblmeterno;
	Choice meterlocation,tfmetertype,tfphasecode,billtype;
	String meterno;
	public Meterinfo(String meterno) {
		this.meterno = meterno;
		setSize(700, 500);
		setLocation(450, 175);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173,216,230));
		add(p);
		
		JLabel heading = new JLabel("Meter Information");
		heading.setBounds(180, 10,  200, 20);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 24 ));
		p.add(heading);
		
		JLabel lblmeter = new JLabel("Meter Number");
		lblmeter.setBounds(100, 80,  100, 25);
		p.add(lblmeter);
		
		lblmeterno = new JLabel(""+meterno);
		lblmeterno.setBounds(240, 80,  100, 25);
		p.add(lblmeterno);
		
		JLabel lblmeterlocation = new JLabel("Meter location");
		lblmeterlocation.setBounds(100, 120,  100, 25);
		p.add(lblmeterlocation);
		
		meterlocation = new Choice();
		meterlocation.add("Outside");
		meterlocation.add("Inside");
		meterlocation.setBounds(240, 120, 200, 25);
		p.add(meterlocation);
		
		
		
		
		JLabel lblmetertype = new JLabel("Meter Type");
		lblmetertype.setBounds(100, 160,  100, 25);
		p.add(lblmetertype);
		
		tfmetertype = new Choice();
		tfmetertype.add("Electric Meter");
		tfmetertype.add("Solar Meter");
		tfmetertype.add("Smart Meter");
		tfmetertype.setBounds(240, 160, 200, 25);
		p.add(tfmetertype);
		
		JLabel lblphasecode = new JLabel("Phase Code");
		lblphasecode.setBounds(100, 200,  100, 25);
		p.add(lblphasecode);
		
		tfphasecode = new Choice();
		tfphasecode.add("011");
		tfphasecode.add("022");
		tfphasecode.add("033");
		tfphasecode.add("044");
		tfphasecode.add("055");
		tfphasecode.add("066");
		tfphasecode.add("077");
		tfphasecode.add("088");
		tfphasecode.add("099");
		tfphasecode.setBounds(240, 200, 200, 25);
		p.add(tfphasecode);
		
		JLabel lblbilltype = new JLabel("Bill Type");
		lblbilltype.setBounds(100, 240,  100, 25);
		p.add(lblbilltype);
		
		billtype = new Choice();
		billtype.add("Normal");
		billtype.add("Industrial");
		billtype.setBounds(240, 240, 200, 25);
		p.add(billtype);
		
		JLabel lblday = new JLabel("Days");
		lblday.setBounds(100, 280,  100, 25);
		p.add(lblday);
		
		JLabel lbldays = new JLabel("30 Days");
		lbldays.setBounds(240, 280,  100, 25);
		p.add(lbldays);
		
		JLabel lblnote = new JLabel("Note ");
		lblnote.setBounds(100, 320,  100, 25);
		p.add(lblnote);
		
		JLabel lblnotes = new JLabel("By default bill is calculated for 30 days...");
		lblnotes.setBounds(240, 320,  250, 25);
		p.add(lblnotes);
		
		submit = new JButton("Submit");
		submit.setBounds(220, 390, 100, 25);
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		p.add(submit);
		
		
		
		
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
		if (ae.getSource() == submit) {
			
			String meternumber = meterno;
			String meterl = meterlocation.getSelectedItem();
			String metertype = tfmetertype.getSelectedItem();
			String phasecode = tfphasecode.getSelectedItem();
			String billt = billtype.getSelectedItem();
			String days = "30";
			
			String query = "insert into meter_info values('"+meternumber+"','"+meterl+"','"+metertype+"','"+phasecode+"','"+billt+"','"+days+"')";
			
			try {
				Connect con = new Connect();
				con.stmt.executeUpdate(query);
				
				
				JOptionPane.showMessageDialog(null, "Meter Information added Sucessfully");
				
				setVisible(false);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			setVisible(false);
		}
		
	}



	public static void main(String[] args) {
		new Meterinfo("");
	}
}
