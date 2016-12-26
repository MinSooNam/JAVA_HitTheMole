import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class VersionInfoPanel extends JPanel {
	
	private JButton btnHome;
	private ButtonListener btnL;
	private JLabel programInfo;
	
	public VersionInfoPanel(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		
		btnL = new ButtonListener();
		
		Font font = new Font("Verdana", Font.BOLD, 15);
		Font font2 = new Font("Verdana",Font.BOLD,24);
		
		// Ȩ��ư.
		ImageIcon homeIcon = new ImageIcon("./img/home.png");
		btnHome = new JButton();
		btnHome.setBounds(720, 520, 80, 80);
		btnHome.setIcon(homeIcon);
		btnHome.setOpaque(false);
		btnHome.setBackground(new Color(255, 0, 0, 0));
		btnHome.setMargin(new Insets(3, 3, 3, 3));
		btnHome.addMouseListener(btnL);
		add(btnHome);
		
		programInfo = new JLabel("<html><tr align=right><font size = 4> v1.00 </font></tr><br>"
				+ "<center><font size = 15> Programmed by <br><br> "
				+ " ��״� </font> <br><br> "
				+ "<font size = 6> ���ȣ<br>�����<br>������<br>���μ�</font></center></html>");
		programInfo.setBounds(0, 0, 800,500);
		programInfo.setVerticalAlignment(SwingConstants.CENTER);
		programInfo.setHorizontalAlignment(SwingConstants.CENTER);
		programInfo.setForeground(Color.black);
		
		add(programInfo);
	}
	
	public void goHome() {
	  	  
		MoleApp.frame.getContentPane().removeAll();
		MoleApp.frame.getContentPane().add(new InitPanel());
		MoleApp.frame.pack();
		MoleApp.frame.setVisible(true);

   }//goHome() ���� Ȩ ȭ������ �̵�.
	
	private class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {

			Object obj = event.getSource();
			
			if(obj == btnHome){
				goHome();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent event) {
			System.out.println("Pressed");

		}

		@Override
		public void mouseReleased(MouseEvent event) {
			System.out.println("Released");

		}

		@Override
		public void mouseEntered(MouseEvent event) {

		}

		@Override
		public void mouseExited(MouseEvent event) {

		}

	}

}
