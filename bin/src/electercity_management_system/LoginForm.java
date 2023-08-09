package electercity_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
	JButton login,cancel,sign_up;
	JTextField username;
	JPasswordField password;
	Choice logininas;
	public LoginForm(){
		super("Login Form");
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(300, 20, 100, 20);
		add(lblusername);
		username = new JTextField();
		username.setBounds(400,20,150,20);
		add(username);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(300, 60, 100, 20);
		add(lblpassword);
		password = new JPasswordField();
		password.setBounds(400, 60, 150, 20);
		add(password);
		
		JLabel lbllogininas = new JLabel("Login in as");
		lbllogininas.setBounds(300, 100, 100, 20);
		add(lbllogininas);
		logininas = new Choice();
		logininas.add("Admin");
		logininas.add("Customer");
		logininas.setBounds(400, 100, 150, 20);
		add(logininas);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
		Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		 login = new JButton("Login",new ImageIcon(i2));
		login.setBounds(330, 160, 100, 20);
		login.addActionListener(this);
		add(login);
		
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
		Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		cancel = new JButton("Cancel",new ImageIcon(i4));
		cancel.setBounds(450, 160, 100, 20);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
		Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		sign_up = new JButton("Sign UP",new ImageIcon(i6));
		sign_up.setBounds(380, 200, 100, 20);
		sign_up.addActionListener(this);
		add(sign_up);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
		Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i8));
		image.setBounds(0, 0, 250, 250);
		add(image);
		
		setSize(640, 300);
		setLocation(400, 250);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource()==login) {
			String lusername = username.getText();
			String lpassword = password.getText();
			String lchoice = logininas.getSelectedItem();
			
			try {
				Connect c = new Connect();
				String query ="select * from login where username = '"+lusername+"' and password = '"+lpassword+"' and usertype = '"+lchoice+"'";
				ResultSet rs = c.stmt.executeQuery(query);
				if (rs.next()) {
					String meterno = rs.getString("meter_no");
					setVisible(false);
					new Project(lchoice,meterno);
					
				}else {
					JOptionPane.showMessageDialog(null, "Invalid Login...");
					username.setText("");
					password.setText("");
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else if (ae.getSource()==cancel) {
			setVisible(false);
		}else if (ae.getSource()==sign_up) {
			setVisible(false);
			new Signup();
		}
	}



	public static void main(String[] args) {
		new LoginForm();
	}
}
