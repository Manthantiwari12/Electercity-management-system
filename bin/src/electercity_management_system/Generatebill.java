package electercity_management_system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Generatebill extends JFrame implements ActionListener{
	JTextArea area;
	Choice cmonth;
	JButton bill,back,print;
	String meternumber;
	JPanel panel;
	public Generatebill(String meternumber) {
		this.meternumber=meternumber;
		setSize(500,800);
		setLocation(550, 15);
		
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		
		JLabel heading = new JLabel("Generate Bill");
		
		JLabel meterno = new JLabel(meternumber);
		
		cmonth = new Choice();
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
		
		
		area = new JTextArea(50,15);
		
		area.setText("\n\n\t-----------Click on the------------\n\t Generate Bill Button to get\n\t the bill of the Selected Month");
		area.setFont(new Font("Senserif",Font.ITALIC,18));
		
		JScrollPane sp = new JScrollPane(area);
		
		bill = new JButton("Generate Bill");
		bill.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(30,5,80,20);
		add(back);
		back.addActionListener(this);
		
		print = new JButton("Print Bill");
		print.setBounds(380,5,100,20);
		print.addActionListener(this);
		add(print);
		
		panel.add(heading);
		panel.add(meterno);
		panel.add(cmonth);
		add(panel,"North");
		
		add(sp,"Center");
		add(bill,"South");
		
		setVisible(true);
	}
	
	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==bill) {
			
			try {
				Connect con = new Connect();
				
				String month = cmonth.getSelectedItem();
				
				area.setText("\tReliance Power Limited\nELECTERCITY BILL GENERATED FOR THE MONTH\n\t  OF "+month+",2023\n\n\n ");
				
				ResultSet rs = con.stmt.executeQuery("select * from customer where meter_no = '"+meternumber+"'");
				if (rs.next()) {
					area.append("\n    Customer Name\t:"+rs.getString("name"));
					area.append("\n    Meter Number\t:"+rs.getString("meter_no"));
					area.append("\n    Address\t\t:"+rs.getString("address"));
					area.append("\n    City\t\t:"+rs.getString("city"));
					area.append("\n    State\t\t:"+rs.getString("state"));
					area.append("\n    Email\t\t:"+rs.getString("email"));
					area.append("\n    Phone\t\t:"+rs.getString("phone"));
					area.append("\n");
					area.append("-----------------------------------------------------------------------");
					area.append("\n");
				}
				
				rs = con.stmt.executeQuery("select * from meter_info where meter_no = '"+meternumber+"'");
				if (rs.next()) {
					area.append("\n    Meter Location\t:"+rs.getString("meter_location"));
					area.append("\n    Meter Type\t\t:"+rs.getString("meter_type"));
					area.append("\n    Phase Code\t\t:"+rs.getString("phase_code"));
					area.append("\n    Bill Type\t\t:"+rs.getString("bill_type"));
					area.append("\n    Days\t\t:"+rs.getString("days"));
					area.append("\n");
					area.append("-----------------------------------------------------------------------");
					area.append("\n");
				}
				
				rs = con.stmt.executeQuery("select * from tax");
				if (rs.next()) {
					area.append("\n    Cost per Unit\t\t:"+rs.getString("cost_per_unit"));
					area.append("\n    Meter Rent\t\t:"+rs.getString("meter_rent"));
					area.append("\n    Service Charge\t:"+rs.getString("service_charge"));
					area.append("\n    Service tax\t\t:"+rs.getString("service_tax"));
					area.append("\n    Swacch Bharat Cess\t:"+rs.getString("swacch_bharat_cess"));
					area.append("\n    Fixed Tax\t\t:"+rs.getString("fixed_tax"));
					area.append("\n");
					area.append("-----------------------------------------------------------------------");
					area.append("\n");
				} 
				rs = con.stmt.executeQuery("select * from bill where meter_no='"+meternumber+"' and month = '"+month+"'");
				if (rs.next()) {
					area.append("\n    Current Month\t:"+rs.getString("month"));
					area.append("\n    Units COnsumed\t:"+rs.getString("units"));
					area.append("\n    Total Charge\t\t:"+rs.getString("totalbill"));
					area.append("\n");
					area.append("-----------------------------------------------------------------------");
					area.append("\n");
					area.append("\n    Total Payable\t:"+rs.getString("totalbill"));
					
					area.append("\n");
				}
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}else if (e.getSource()==print) {
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
					area.paint(g2);
					
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
		new Generatebill("");
	}
}
