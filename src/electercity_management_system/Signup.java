package electercity_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Signup extends JFrame implements ActionListener {
	JButton create, back;
	JTextField meterno, username, name;
	JPasswordField password;
	Choice accounttype;

	public Signup() {

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 630, 300);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setForeground(new Color(34, 139, 34));
		add(panel);

		JLabel heading = new JLabel("Create Account As");
		heading.setBounds(100, 50, 140, 20);
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(heading);
		accounttype = new Choice();
		accounttype.add("Admin");
		accounttype.add("Customer");
		accounttype.setBounds(260, 50, 150, 20);
		panel.add(accounttype);

		JLabel lblmeterno = new JLabel("Meter Number");
		lblmeterno.setBounds(100, 90, 140, 20);
		lblmeterno.setForeground(Color.GRAY);
		lblmeterno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmeterno.setVisible(false);
		panel.add(lblmeterno);
		meterno = new JTextField();
		meterno.setBounds(260, 90, 150, 20);
		meterno.setVisible(false);
		panel.add(meterno);

		

		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(100, 130, 140, 20);
		lblusername.setForeground(Color.GRAY);
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblusername);
		username = new JTextField();
		username.setBounds(260, 130, 150, 20);
		panel.add(username);

		JLabel lblname = new JLabel("Name");
		lblname.setBounds(100, 170, 140, 20);
		lblname.setForeground(Color.GRAY);
		lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblname);
		name = new JTextField();
		name.setBounds(260, 170, 150, 20);
		panel.add(name);

		meterno.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					Connect con = new Connect();
					ResultSet rs = con.stmt
							.executeQuery("select * from login where meter_no = '" + meterno.getText() + "'");
					while (rs.next()) {
						name.setText(rs.getString("name"));
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(100, 210, 140, 20);
		lblpassword.setForeground(Color.GRAY);
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblpassword);
		password = new JPasswordField();
		password.setBounds(260, 210, 150, 20);
		panel.add(password);
		
		accounttype.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String user = accounttype.getSelectedItem();
				if (user.equals("Admin")) {
					lblmeterno.setVisible(false);
					meterno.setVisible(false);
					name.setEditable(true);
					
				} else {
					lblmeterno.setVisible(true);
					meterno.setVisible(true);
					name.setEditable(false);
				}
			}
		});

		create = new JButton("Create");
		create.setBackground(Color.BLACK);
		create.setForeground(Color.WHITE);
		create.setBounds(140, 260, 100, 25);
		create.addActionListener(this);
		panel.add(create);

		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(300, 260, 100, 25);
		back.addActionListener(this);
		panel.add(back);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
		Image i2 = i1.getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		image.setBounds(412, 30, 240, 240);
		panel.add(image);

		setSize(700, 400);
		setLocation(400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == create) {
			String sacctype = accounttype.getSelectedItem();
			String susername = username.getText();
			String sname = name.getText();
			String spassword = password.getText();
			String smeter;
			if (sacctype.equals("Admin")) {
				smeter = "null";

			} else {
				smeter = meterno.getText();
			}

			try {
				Connect c = new Connect();
				String query = null;
				if (sacctype.equals("Admin")) {
					query = "insert into login values('" + smeter + "','" + susername + "','" + sname + "','" + spassword + "','" + sacctype + "')";
					
				}else {
					query = "update login set username = '"+susername+"',password = '"+spassword+"',usertype = '"+sacctype+"' where meter_no = '"+smeter+"'";
				}
						

				int success = c.stmt.executeUpdate(query);
				if (success != 0) {
					JOptionPane.showMessageDialog(null, "Account created sucessfully...");
					setVisible(false);
					new LoginForm();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (ae.getSource() == back) {
			setVisible(false);
			new LoginForm();
		}
	}

	public static void main(String[] args) {
		new Signup();
	}
}
