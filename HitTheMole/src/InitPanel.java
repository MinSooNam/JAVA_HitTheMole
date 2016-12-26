
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InitPanel extends JPanel {

	private Thread th;
	private ImageIcon imgCenter , imgIconTemp, imgTitle;
	//private ImageIcon[] imgMole;
	private ImagePanelThread[] imgMolePanel;
	private JLabel[] lblImgMole;
	private Image imgTemp;
	//private JLabel[] lblImgMole;
	private JLabel lblTitle;
	private JLabel lblImgCenter;
	//private JLabel lbl_btnStart, lbl_btnExit, lbl_btnTutorial, lbl_btnInfo, lbl_btnRanking;
	private JLabel[] lblMenuArray;
	
	private int Count = 1;
	private String[] path;
	
	
	//private GamePanel gamePanel;
	//private VersionInfoPanel infoPanel;
	//private TutorialPanel tutorialPanel;

	private InitListener initL;

	public InitPanel() {

		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.white);
		//init_background.pngsetBackground();
		setLayout(null);
		
		//gamePanel = new GamePanel();
		//infoPanel = new VersionInfoPanel();
		//tutorialPanel = new TutorialPanel();

		initL = new InitListener();

		/*
			ï¿½ï¿½ Serif
	        ï¿½ï¿½ SansSerif
	        ï¿½ï¿½ Dialog
	        ï¿½ï¿½ DialogInput
	        ï¿½ï¿½ Monospaced
		*/
		Font font = new Font("Monospaced", Font.BOLD, 20);
		Font font2 = new Font("Dialog", Font.BOLD, 20);
		
		//imgTitle = new 
		imgTemp = new ImageIcon("./img/imgTitle4.png").getImage();
		imgTemp = imgTemp.getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH);
		imgTitle = new ImageIcon(imgTemp);
		lblTitle = new JLabel(imgTitle, SwingConstants.CENTER);
		lblTitle.setBounds(250, 30, 300, 100);
		//lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 30));
		add(lblTitle);
		
		
		imgMolePanel = new ImagePanelThread[3];
		//lblImgMole = new JLabel[3];
		 
		for(int i = 0; i<3; i++){
			//imgIconTemp = new ImageIcon("src/img/object2.png");
			//imgTemp = new ImageIcon("./img/mole1.png").getImage();
			//imgTemp = imgTemp.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			imgMolePanel[i] = new ImagePanelThread("./img/mole1.png", "./img/mole2.png", "./img/mole3.png", "./img/mole4.png", 80, 80, 200);
			imgMolePanel[i].changeStart(300*i);
			
			//lblImgMole[i] = new JLabel(imgMole[i], SwingConstants.CENTER);
			imgMolePanel[i].setBounds(180+(100*i),430,80,80);
			//imgMolePanel[i].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.red));
			
			add(imgMolePanel[i]);
			
		}
		
		imgTemp = new ImageIcon("./img/mole4.png").getImage();
		imgTemp = imgTemp.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
		imgCenter = new ImageIcon(imgTemp);
		lblImgCenter = new JLabel(imgCenter, SwingConstants.CENTER);
		//lblImgCenter.setBackground(Color.blue);
		//lblImgCenter.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
		lblImgCenter.setBounds(170, 130, 300, 300);
		add(lblImgCenter);
		
		
		
		// ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Æ° ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ê±ï¿½
		lblMenuArray = new JLabel[SettingConstants.MENU_COUNT];
		for(int i = 0; i<SettingConstants.MENU_COUNT; i++){
			lblMenuArray[i] = new JLabel(SettingConstants.MENU[i]);
			lblMenuArray[i].setBounds(580, 190+(i*60), 180, 50);
			lblMenuArray[i].setFont(font);
			lblMenuArray[i].addMouseListener(initL);
			add(lblMenuArray[i]);
		}

		
	}
	
	/*
	public void paintComponent(Graphics g){
		g.drawImage(icon.getImage(), 70, 120, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	*/

	private class InitListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {

			Object obj = event.getSource();
					
			for(int i = 0; i<SettingConstants.MENU_COUNT; i++){
				
				if(obj == lblMenuArray[i]){
				
					switch (i) {
					case SettingConstants.START:
						MoleApp.frame.getContentPane().removeAll();
						MoleApp.frame.getContentPane().add(new GamePanel());
						MoleApp.frame.pack();
						MoleApp.frame.setVisible(true);
						break;
					case SettingConstants.RANKING:
						MoleApp.frame.getContentPane().removeAll();
						MoleApp.frame.getContentPane().add(new RankingPanel());
						MoleApp.frame.pack();
						MoleApp.frame.setVisible(true);				
						break;
					case SettingConstants.TUTORIAL:
						MoleApp.frame.getContentPane().removeAll();
						MoleApp.frame.getContentPane().add(new TutorialPanel());
						MoleApp.frame.pack();
						MoleApp.frame.setVisible(true);
						break;
					case SettingConstants.INFORMATION:
						MoleApp.frame.getContentPane().removeAll();
						MoleApp.frame.getContentPane().add(new VersionInfoPanel());
						MoleApp.frame.pack();
						MoleApp.frame.setVisible(true);
						break;
					case SettingConstants.EXIT:
						int result = JOptionPane.showConfirmDialog(null, "°ÔÀÓÀ» Á¾·áÇÏ½Ã°Ú½À´Ï?");
						if(result == JOptionPane.YES_OPTION){
							System.exit(0);
						}
						break;
					default:
						break;
						
					}	// switch	
					
				}	// if
				
			}	// for

		}	// Clicked

		@Override
		public void mousePressed(MouseEvent event) {}

		@Override
		public void mouseReleased(MouseEvent event) {}

		@Override
		public void mouseEntered(MouseEvent event) {
			
			Object obj = event.getSource();
			//Font font2 = new Font("Dialog", Font.BOLD, 20);
			
			for(int i = 0; i<SettingConstants.MENU_COUNT; i++){
				
				if(obj == lblMenuArray[i]){
				
					switch (i) {
					case SettingConstants.START:
						lblMenuArray[i].setForeground(SettingConstants.ENTER_FORE);
						//lblMenuArray[i].setFont(font2);
						break;
					case SettingConstants.RANKING:
						lblMenuArray[i].setForeground(SettingConstants.ENTER_FORE);
						break;
					case SettingConstants.TUTORIAL:
						lblMenuArray[i].setForeground(SettingConstants.ENTER_FORE);
						break;
					case SettingConstants.INFORMATION:
						lblMenuArray[i].setForeground(SettingConstants.ENTER_FORE);
						break;
					case SettingConstants.EXIT:
						lblMenuArray[i].setForeground(SettingConstants.ENTER_FORE);
						break;
					default:
						break;
						
					}	// switch	
					
				}	// if
				
			}	// for
			
		}	// Entered

		@Override
		public void mouseExited(MouseEvent event) {
			
			Object obj = event.getSource();
			
			for(int i = 0; i<SettingConstants.MENU_COUNT; i++){
				
				if(obj == lblMenuArray[i]){
				
					switch (i) {
					case SettingConstants.START:
						lblMenuArray[i].setForeground(SettingConstants.EXIT_FORE);
						break;
					case SettingConstants.RANKING:
						lblMenuArray[i].setForeground(SettingConstants.EXIT_FORE);
						break;
					case SettingConstants.TUTORIAL:
						lblMenuArray[i].setForeground(SettingConstants.EXIT_FORE);
						break;
					case SettingConstants.INFORMATION:
						lblMenuArray[i].setForeground(SettingConstants.EXIT_FORE);
						break;
					case SettingConstants.EXIT:
						lblMenuArray[i].setForeground(SettingConstants.EXIT_FORE);
						break;
					default:
						break;
						
					}	// switch	
					
				}	// if
				
			}	// for
			
		}	// Exited

	}	//InitListener

}
