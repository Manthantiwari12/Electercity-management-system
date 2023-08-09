package electercity_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class UpdateInformation extends JFrame implements ActionListener{
	
	JTextField tfaddress,tfcity,tfstate,tfemail,tfphnno;
	JButton cancel,update; 
	String meternumber;
	public UpdateInformation(String meternumber) {
		this.meternumber=meternumber;
		setSize(1050,450);
		setLocation(300, 150);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("Update Customer Information");
		heading.setBounds(110, 0, 500, 30);
		add(heading);
		heading.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(30, 70, 100, 20);
		add(lblname);
		
		JLabel name = new JLabel("");
		name.setBounds(230, 70, 200, 20);
		add(name); 
		
		
		
		JLabel lblmeterno = new JLabel("Meter Number");
		lblmeterno.setBounds(30, 110, 100, 20);
		add(lblmeterno);
		
		JLabel meterno = new JLabel("");
		meterno.setBounds(230, 110, 200, 20);
		add(meterno); 
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(30, 150, 100, 20);
		add(lbladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(230, 150, 200, 20);
		add(tfaddress); 
		
		JLabel lblcity = new JLabel("City");
		lblcity.setBounds(30, 190, 100, 20);
		add(lblcity);
		
		tfcity = new JTextField();
		tfcity.setBounds(230, 190, 200, 20);
		add(tfcity);
		
		JLabel lblstate = new JLabel("State");
		lblstate.setBounds(30, 230, 100, 20);
		add(lblstate);
		
		tfstate = new JTextField();
		tfstate.setBounds(230, 230, 200, 20);
		add(tfstate);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(30, 270, 100, 20);
		add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(230, 270, 200, 20);
		add(tfemail);
		
		JLabel lblphnno = new JLabel("Phone ");
		lblphnno.setBounds(30, 310, 100, 20);
		add(lblphnno);
		
		tfphnno = new JTextField();
		tfphnno.setBounds(230, 310, 200, 20);
		add(tfphnno);
		
		try {
			Connect con = new Connect();
			ResultSet rs = con.stmt.executeQuery("select * from customer where meter_no = '"+meternumber+"'");
			while (rs.next()) {
				name.setText(rs.getString("name"));
				meterno.setText(rs.getString("meter_no"));
				tfaddress.setText(rs.getString("address"));
				tfcity.setText(rs.getString("city"));
				tfstate.setText(rs.getString("state"));
				tfemail.setText(rs.getString("email"));
				tfphnno.setText(rs.getString("phone"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		cancel = new JButton("Cancel");
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(230, 360, 100, 25);
		add(cancel);
		cancel.addActionListener(this);
		
		update = new JButton("Update");
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		update.setBounds(70, 360, 100, 25);
		add(update);
		update.addActionListener(this);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
		Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		image.setBounds(550,50,400,300);
		add(image);
		
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==update) {
			String address= tfaddress.getText();
			String city = tfcity.getText();
			String state = tfstate.getText();
			String email = tfemail.getText();
			String phnno = tfphnno.getText();
			
			
			try {
				Connect con = new Connect();
				con.stmt.execute("update customer set address='"+address+"',city='"+city+"',state='"+state+"',email='"+email+"',phone='"+phnno+"' where meter_no='"+meternumber+"'");
				
				JOptionPane.showMessageDialog(null, "User Information Updated Sucessfully");
				setVisible(false);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}else {
			setVisible(false);
		}
	}


	public static void main(String[] args) {
		new UpdateInformation("");
	}
}
