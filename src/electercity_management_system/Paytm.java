package electercity_management_system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Paytm extends JFrame implements ActionListener {
	
	JButton back;
	String meternumber;
	public Paytm(String meternumber) {
		this.meternumber=meternumber;
		JEditorPane j = new JEditorPane();
		j.setEditable(false);
		try {
			j.setPage("https://paytm.com/online-payments");
		} catch (Exception e) {
			// TODO: handle exception
			j.setContentType("text/html");
			j.setText("<html>could not load<html>");
		}
		JScrollPane sp = new JScrollPane(j);
		add(sp);
		
		back = new JButton("Back");
		back.setBounds(640, 20, 80, 30);
		back.addActionListener(this);
		j.add(back);
		
		setSize(800, 600);
		setLocation(400, 150);
		setVisible(true);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
		new Paybill(meternumber);
	}
	public static void main(String[] args) {
		new Paytm("");
	}
}
