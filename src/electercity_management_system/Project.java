package electercity_management_system;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Project extends JFrame implements ActionListener{
	String atype,meterno;
	public Project(String atype, String meterno){
		this.atype = atype;
		this.meterno=meterno;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(new ImageIcon(i2));
		add(image);
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu master = new JMenu("Master");
		master.setForeground(Color.BLUE);
		
		
		JMenuItem newcustomer = new JMenuItem("New Customer");
		newcustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
		newcustomer.setBackground(Color.white);
		master.add(newcustomer);
		ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
		Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newcustomer.setIcon(new ImageIcon(image1));
		newcustomer.setMnemonic('N');
		newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newcustomer.addActionListener(this);
		
		JMenuItem customerdetails = new JMenuItem("Customer Details");
		customerdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		customerdetails.setBackground(Color.white);
		master.add(customerdetails);
		ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
		Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		customerdetails.setIcon(new ImageIcon(image2));
		customerdetails.setMnemonic('M');
		customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		customerdetails.addActionListener(this);

		
		JMenuItem depositdetails = new JMenuItem("Deposit Details");
		depositdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		depositdetails.setBackground(Color.white);
		master.add(depositdetails);
		ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
		Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		depositdetails.setIcon(new ImageIcon(image3));
		depositdetails.setMnemonic('D');
		depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		depositdetails.addActionListener(this);

		
		JMenuItem calculatebill = new JMenuItem("Calculate Bill");
		calculatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
		calculatebill.setBackground(Color.white);
		master.add(calculatebill);
		ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
		Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		calculatebill.setIcon(new ImageIcon(image4));
		calculatebill.setMnemonic('B');
		calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		calculatebill.addActionListener(this);

		
		
		JMenu information = new JMenu("Information");
		information.setForeground(Color.RED); 
		
		
		JMenuItem upinfo = new JMenuItem("Update Information");
		upinfo.setFont(new Font("monospaced", Font.PLAIN, 12));
		upinfo.setBackground(Color.white);
		information.add(upinfo);
		ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
		Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		upinfo.setIcon(new ImageIcon(image5));
		upinfo.setMnemonic('I');
		upinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		upinfo.addActionListener(this);
		
		JMenuItem viewinfo = new JMenuItem("View Information");
		viewinfo.setFont(new Font("monospaced", Font.PLAIN, 12));
		viewinfo.setBackground(Color.white);
		information.add(viewinfo);
		ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
		Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		viewinfo.setIcon(new ImageIcon(image6));
		viewinfo.setMnemonic('V');
		viewinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		viewinfo.addActionListener(this);

		
		JMenu user = new JMenu("User");
		user.setForeground(Color.BLUE); 
		
		
		JMenuItem pbill = new JMenuItem("Pay Bill");
		pbill.setFont(new Font("monospaced", Font.PLAIN, 12));
		pbill.setBackground(Color.white);
		user.add(pbill);
		ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
		Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		pbill.setIcon(new ImageIcon(image7));
		pbill.setMnemonic('P');
		pbill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		pbill.addActionListener(this);
		
		JMenuItem billdetails = new JMenuItem("Bill Details");
		billdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
		billdetails.setBackground(Color.white);
		user.add(billdetails);
		ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
		Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		billdetails.setIcon(new ImageIcon(image8));
		billdetails.setMnemonic('B');
		billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		billdetails.addActionListener(this);
		
		
		JMenu report = new JMenu("Report");
		report.setForeground(Color.RED); 
		
		
		JMenuItem generatebill = new JMenuItem("Generate Bill");
		generatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
		generatebill.setBackground(Color.white);
		report.add(generatebill);
		ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
		Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		generatebill.setIcon(new ImageIcon(image9));
		generatebill.setMnemonic('G');
		generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		generatebill.addActionListener(this);
		JMenu utility = new JMenu("Utility");
		utility.setForeground(Color.BLUE); 
		
		
		JMenuItem notepad = new JMenuItem("Notepad");
		notepad.setFont(new Font("monospaced", Font.PLAIN, 12));
		notepad.setBackground(Color.white);
		utility.add(notepad);
		ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
		Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		notepad.setIcon(new ImageIcon(image10));
		notepad.setMnemonic('A');
		notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		notepad.addActionListener(this);
		
		JMenuItem calculator = new JMenuItem("Calculator");
		calculator.setFont(new Font("monospaced", Font.PLAIN, 12));
		calculator.setBackground(Color.white);
		utility.add(calculator);
		ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
		Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		calculator.setIcon(new ImageIcon(image11));
		calculator.setMnemonic('C');
		calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		calculator.addActionListener(this);
		
		
		JMenu exit = new JMenu("Exit");
		exit.setForeground(Color.RED); 
		

		JMenuItem exit1 = new JMenuItem("Exit");
		exit1.setFont(new Font("monospaced", Font.PLAIN, 12));
		exit1.setBackground(Color.white);
		exit.add(exit1);
		ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
		Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		exit1.setIcon(new ImageIcon(image12));
		exit1.setMnemonic('W');
		exit1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		exit1.addActionListener(this);
		
		if (atype.equals("Admin")) {
			mb.add(master);
		}else {
			mb.add(information);
			mb.add(user);
			mb.add(report);
		}
		
		
		
		
		mb.add(utility);
		mb.add(exit);

		setLayout(new FlowLayout());
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String msg = ae.getActionCommand();
		if (msg.equals("New Customer")) {
			new Newcustomer();
		}else if (msg.equals("Customer Details")) {
			new CustomerDetails();
		}else if (msg.equals("Deposit Details")) {
			new DepositDetails();
		}else if (msg.equals("Calculate Bill")) {
			new Calculatebill(); 
		}else if (msg.equals("View Information")) {
			new ViewInformation(meterno); 
		}else if (msg.equals("Update Information")) {
			new UpdateInformation(meterno);
		}else if (msg.equals("Pay Bill")) {
			new Paybill(meterno);
		}else if (msg.equals("Bill Details")) {
			new BillDetails(meterno);
		}else if (msg.equals("Notepad")) {
			try {
				Runtime.getRuntime().exec("notepad.exe");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (msg.equals("Calculator")) {
			try {
				Runtime.getRuntime().exec("calc.exe");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (msg.equals("Generate Bill")) {
			new Generatebill(meterno);
		}else if (msg.equals("Exit")) {
			setVisible(false);
			new LoginForm();
		}
		
	}

	public static void main(String[] args) {
		new Project("","");
	}
}
