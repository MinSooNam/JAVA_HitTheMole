import java.awt.*;

import javax.swing.*;
import javax.swing.text.html.ImageView;

import java.awt.event.*;

public class GamePanel extends JPanel {

   private TopPanel topPanel;
   private MolePanel molePanel;
   private StatePanel statePanel;

   private JButton btnHome;
   private ButtonListener btnL;

   private Image imgBack;
   private Image imgHome;
   private Image imgCursorRe;
   private Image imgCursorPre;

   private Toolkit toolkit;
   private Cursor cursorhammerRe;
   private Cursor cursorhammerPre;
   private Cursor normalCursor;

   public GamePanel() {
      init();
      setView();
   }// GamePanel()

   private void init() {
      MoleConstants.gamePanel = this;
      imgBack = new ImageIcon("./img/mole_back.jpg").getImage();
      imgHome = new ImageIcon("./img/home.png").getImage();

      /*
       * ���콺 Ŀ�� �̹��� ����
       */
      toolkit = Toolkit.getDefaultToolkit();
      imgCursorRe = toolkit.getImage("./img/hammerRe.png");
      imgCursorRe = imgCursorRe.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      imgCursorPre = toolkit.getImage("./img/hammerPre.png");
      imgCursorPre = imgCursorPre.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      cursorhammerRe = toolkit.createCustomCursor(imgCursorRe,
            new Point(this.getX(), this.getY()), "img");
      cursorhammerPre = toolkit.createCustomCursor(imgCursorPre, new Point(
            this.getX(), this.getY()), "img");
      normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

   }// init() �ʱ�ȭ 

   private void setView() {
      setBackground(Color.green);
      setPreferredSize(new Dimension(800, 600));
      setLayout(null);

      /*
       * ��� �г� ����
       */
      topPanel = new TopPanel();
      MoleConstants.topPanel = topPanel;
      this.add(topPanel);

      /*
       * �������� �г� ����
       */
      statePanel = new StatePanel();
      MoleConstants.statePanel = statePanel;
      statePanel.setBounds(630, 90, 170, 430);
      this.add(statePanel);

      /*
       * �δ��� ���� �г� ����
       */
      molePanel = new MolePanel();
      MoleConstants.molePanel = molePanel;
      molePanel.setBounds(0, 90, 630, 510);
      this.add(molePanel);
      // ���콺 Ŀ�� �и�ġ�� �ٲٱ�.

      /*
       * Ȩ��ư ����
       */
      btnL = new ButtonListener();

      ImageIcon homeIcon = new ImageIcon("./img/home.png");
      // Ȩ��ư.
      btnHome = new JButton();
      btnHome.setBounds(720, 520, 80, 80);
      btnHome.setIcon(homeIcon);
      btnHome.setOpaque(false);
      btnHome.setBackground(new Color(255, 0, 0, 0));
      btnHome.setMargin(new Insets(3, 3, 3, 3));
      btnHome.addMouseListener(btnL);
      add(btnHome);

      MoleConstants.topPanel = topPanel;
      MoleConstants.molePanel = molePanel;
      MoleConstants.statePanel = statePanel;
      MoleConstants.gamePanel = this;

      this.addMouseListener(btnL);
   }// setView() �гο� �� ����

   public void paintComponent(Graphics page) {
      super.paintComponent(page);

      page.drawImage(imgBack, 0, 0, null);
   }//paintComponent() ���׸���.

   public void goHome() {
	   SoundPlay.stopBgm();
	   
      MoleConstants.init();
      
      MoleConstants.statePanel.timerStop();
      
      MoleApp.frame.getContentPane().removeAll();
      MoleApp.frame.getContentPane().add(new InitPanel());
      MoleApp.frame.pack();
      MoleApp.frame.setVisible(true);

   }//goHome() ���� Ȩ ȭ������ �̵�.

   private class ButtonListener implements MouseListener {

      @Override
      public void mouseClicked(MouseEvent event) {

         Object obj = event.getSource();

         if (obj == btnHome) {
            goHome();
         }

      }

      @Override
      public void mousePressed(MouseEvent event) {
         MoleConstants.gamePanel.setCursor(cursorhammerPre);
      }

      @Override
      public void mouseReleased(MouseEvent event) {
         MoleConstants.gamePanel.setCursor(cursorhammerRe);
      }

      @Override
      public void mouseEntered(MouseEvent event) {
         MoleConstants.gamePanel.setCursor(cursorhammerRe);

      }

      @Override
      public void mouseExited(MouseEvent event) {

         MoleConstants.gamePanel.setCursor(normalCursor);
      }
   }//inner class ButtonListener
}// ButtonListener


/*
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

   private Button btnHome;
   private ButtonListener btnL;
   
   public GamePanel(){
      setBackground(Color.green);
      setPreferredSize(new Dimension(800, 600));
      setLayout(null);
      
      btnL = new ButtonListener();
      
      Font font = new Font("Verdana", Font.BOLD, 15);
      
      // Ȩ��ư.
      btnHome = new Button("ó������");
      btnHome.setBounds(600, 400, 100, 50);
      btnHome.setFont(font);
      btnHome.addMouseListener(btnL);
      add(btnHome);
      
      JTextArea textArea = new JTextArea("sdsafs");
      textArea.setBounds(100,100,200,200);
      textArea.setEditable(false);
      textArea.setLineWrap(true);
      textArea.setOpaque(false);
      textArea.setBorder(BorderFactory.createEmptyBorder());
      add(textArea, BorderLayout.CENTER);
      
   }
   
   private class ButtonListener implements MouseListener {

      @Override
      public void mouseClicked(MouseEvent event) {

         Object obj = event.getSource();
         
         if(obj == btnHome){
            MoleApp.frame.getContentPane().removeAll();
            MoleApp.frame.getContentPane().add(new InitPanel());
            MoleApp.frame.pack();
            MoleApp.frame.setVisible(true);   
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
*/