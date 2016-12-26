
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class RankingPanel extends JPanel {
	
	private JButton btnHome;
	private ButtonListener btnL;
	private JLabel lblRank, lblCount, lblName, lblNameShow, lblScore, lblScoreShow;
	private JLabel topLblRank;
	//private String strInput;
	private ArrayList<String> nameList, scoreList;
	private String nameStr;
	private String scoreStr;
	private HashMap<String, Integer> hm;
	//private ImagePanelThread[] imgMolePanel;
	
	
	public RankingPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(800,600));
		setLayout(null);
		
		
		nameList = new ArrayList<String>();
		scoreList = new ArrayList<String>();
		hm = new HashMap<String, Integer>();
		
		btnL = new ButtonListener();
		Font font = new Font("Verdana", Font.BOLD, 15);
		Font font2 = new Font("Verdana", Font.BOLD, 18);
		
		//Rank �ؽ�Ʈ�� �о�ͼ� hashmap�� �ֱ����� ����
		try{
			BufferedReader in = new BufferedReader(new FileReader("src/Rank.txt"));
			
			String s;
			
			while((s = in.readLine()) != null){
				String[] split = s.split(" ");
				hm.put(split[0], Integer.valueOf(split[1]));
			}
			in.close();
		}catch(IOException e){
			System.err.println(e);
			System.exit(1);
		}
		
		//Rank2�ؽ�Ʈ�� �����Ͽ� ������������ �����ϱ� ���� ����
		String outFile2 = "Rank2.txt"; 
		File outputFile2 = new File(outFile2);
		try {
			PrintWriter output2 = new PrintWriter(outputFile2);
			
			//TreeMap<String, String> tm = new TreeMap<String, String>(hm);
			
			String temp = "";
			Iterator<String> it0 = sortByValue(hm).iterator();
			while(it0.hasNext()){
				temp = (String) it0.next();
				output2.println(temp + "\t" + hm.get(temp));
			}
			output2.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try{//Rank2 ���Ͽ��� ���� �о�� �����ֱ� ���� try catch
			BufferedReader in2 = new BufferedReader(new FileReader("Rank2.txt"));
			
			String s2;
			
			while((s2 = in2.readLine()) != null){
				String[] split2 = s2.split("\t");	//Rank2���Ͽ��� ���پ� �о�鿩 \t�� �������� split2[0]��
													//�̸��� �־��ְ� split[1]�� ������ �־��ش�.
				nameStr = split2[0];
				scoreStr = split2[1];
				
				nameList.add(nameStr);				//�׸��� �� �̸��� ������ ���� nameList�� scoreList�� �־��ش�.
				scoreList.add(scoreStr);
			}
			in2.close();
		}catch(IOException e){
			System.err.println(e);
			System.exit(1);
		}
		
		//Ȩ���� ���� ��ư�� ������ �� ��ܿ� �δ��� ���� ������� �������� ��
		topLblRank = new JLabel("GAME RANKING");
		topLblRank.setBounds(200, 30, 400, 50);
		topLblRank.setVerticalAlignment(SwingConstants.CENTER);
		topLblRank.setHorizontalAlignment(SwingConstants.CENTER);
		topLblRank.setFont(new Font("Verdana", Font.BOLD, 30));
		add(topLblRank);
		
		
		//������ ��Ÿ���� ���� ��
		lblRank = new JLabel("RANK");
		lblRank.setBounds(250, 120, 100, 50);
		lblRank.setVerticalAlignment(SwingConstants.CENTER);
		lblRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblRank.setFont(font);
		lblRank.setForeground(Color.black);
		add(lblRank);
		
		//��ŷ��ȣ�� 1-10���� �����ֱ����� �ݺ�
		for(int i = 1; i <= 10; i++){
			lblCount = new JLabel();
			lblCount.setBounds(250, 150+(30*i), 100, 30);
			if(i<=3){
				lblCount.setFont(font2);
				lblCount.setForeground(Color.blue);
			}
			else{
				lblCount.setFont(font);
			}
			lblCount.setText("" + i);
			lblCount.setVerticalAlignment(SwingConstants.CENTER);
			lblCount.setHorizontalAlignment(SwingConstants.CENTER);
			
			add(lblCount);
		}
		
		//�̸��� ��Ÿ���� ���� ��
		lblName = new JLabel("Name");
		lblName.setBounds(350, 120, 100, 50);
		lblName.setVerticalAlignment(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(font);
		add(lblName);
		
		//���� ������ Rank2.txt���� �̸��� ������ �����ֱ� ���� ��
		for(int i = 0; i < nameList.size() && i < 10; i++){
			lblNameShow = new JLabel();
			lblNameShow.setBounds(350, 150+(30*(i+1)), 100, 30);
			if(i<3){
				lblNameShow.setFont(font2);
				lblNameShow.setForeground(Color.blue);
			}
			else{
				lblNameShow.setFont(font);
			}
			lblNameShow.setText(nameList.get(i));
			lblNameShow.setVerticalAlignment(SwingConstants.CENTER);
			lblNameShow.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNameShow);
		}
		
		//������ ��Ÿ���� ���� ��
		lblScore = new JLabel("Score");
		lblScore.setBounds(450, 120, 100, 50);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(font);
		add(lblScore);
		
		//������ ������� ��Ÿ���� ���� for��
		for(int i = 0; i < scoreList.size() && i < 10; i++){
			lblScoreShow = new JLabel();
			lblScoreShow.setBounds(450, 150+(30*(i+1)), 100, 30);
			if(i<3){
				lblScoreShow.setFont(font2);
				lblScoreShow.setForeground(Color.blue);
			}
			else{
				lblScoreShow.setFont(font);
			}
			lblScoreShow.setText(scoreList.get(i));
			lblScoreShow.setVerticalAlignment(SwingConstants.CENTER);
			lblScoreShow.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblScoreShow);
		}
		
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
		
		/*
		imgMolePanel = new ImagePanelThread[3];
		for(int i = 0; i<3; i++){
			//imgIconTemp = new ImageIcon("src/img/object2.png");
			//imgTemp = new ImageIcon("./img/mole1.png").getImage();
			//imgTemp = imgTemp.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			imgMolePanel[i] = new ImagePanelThread("./img/mole1.png", "./img/mole2.png", "./img/mole3.png", "./img/mole4.png", 80, 80, 200);
			imgMolePanel[i].changeStart(300*i);
			
			//lblImgMole[i] = new JLabel(imgMole[i], SwingConstants.CENTER);
			imgMolePanel[i].setBounds(400+(100*i),300,80,80);
			//imgMolePanel[i].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.red));
			
			add(imgMolePanel[i]);	
		}
		*/
	}
	
	//Rank�ؽ�Ʈ�� ���������� �����ϱ� ���� ����Ʈ
	public static List<String> sortByValue(final HashMap<String, Integer> hm) {
		List<String> list = new ArrayList();
		list.addAll(hm.keySet());
 
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = hm.get(o1);
				Object v2 = hm.get(o2);
 
                return ((Comparable) v1).compareTo(v2);
            }
        });
        
        Collections.reverse(list);     
        return list;
	}
	

	public void goHome() {
  	  
		MoleApp.frame.getContentPane().removeAll();
		MoleApp.frame.getContentPane().add(new InitPanel());
		MoleApp.frame.pack();
		MoleApp.frame.setVisible(true);

   }//goHome() ���� Ȩ ȭ������ �̵�.
	
	///////////////////////////////////////////////////////
	
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

