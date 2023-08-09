package electercity_management_system;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame implements Runnable {
	Thread t;
	public Splash() {
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
		Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image);
		setVisible(true);
		int x = 1;
		for (int i = 2; i < 600; i+=4,x++) {
			setSize(i+x, i);
			setLocation(750-((i+x)/2), 400-(i/2));
			try {
				Thread.sleep(2);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		t = new Thread(this);
		t.start();
		setVisible(true);
		
	}
	public void run() {
		try {
			Thread.sleep(3000);
			setVisible(false);
			//login frame will open
			new LoginForm();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Splash();
	}
}
