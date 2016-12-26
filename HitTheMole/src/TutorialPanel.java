import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TutorialPanel extends JPanel {
	
	private JButton btnHome;
	private ButtonListener btnL;
	private Image icon;
	private JLabel lblTitle;

	public TutorialPanel(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(800,600));
		setLayout(null);
		
		btnL = new ButtonListener();
		
		//Font font = new Font("Dialog", Font.BOLD, 30);
		
		// 홈버튼.
		ImageIcon homeIcon = new ImageIcon("./img/home.png");
		btnHome = new JButton();
		btnHome.setBounds(720, 520, 80, 80);
		btnHome.setIcon(homeIcon);
		btnHome.setOpaque(false);
		btnHome.setBackground(new Color(255, 0, 0, 0));
		btnHome.setMargin(new Insets(3, 3, 3, 3));
		btnHome.addMouseListener(btnL);
		add(btnHome);
		
		lblTitle = new JLabel("How To Play?");
		lblTitle.setBounds(250,20,300,100);
		//lblTitle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		add(lblTitle);
		
		icon = new ImageIcon("./img/imgTutorial.jpg").getImage();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(icon, 150, 120, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	public void goHome() {
  	  
		MoleApp.frame.getContentPane().removeAll();
		MoleApp.frame.getContentPane().add(new InitPanel());
		MoleApp.frame.pack();
		MoleApp.frame.setVisible(true);

   }//goHome() 메인 홈 화면으로 이동.
	
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
