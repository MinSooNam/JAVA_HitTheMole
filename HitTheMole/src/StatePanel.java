import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.util.Timer;

public class StatePanel extends JPanel {

	private JPanel scorePanel, timerPanel;
	private JLabel lblScoreTitle, lblScoreNum;
	private JLabel lblTimer;

	private int nScore;
	private int nTime;

	private Timer timer;

	public StatePanel() {
		this.setOpaque(false);
		this.setLayout(null);

		nScore = 0;

		/*
		 * �ð��� Ÿ�̸� �г� ����
		 */
		timerPanel = new JPanel();
		timerPanel.setBounds(0, 0, 170, 215);
		timerPanel.setLayout(null);
		timerPanel.setOpaque(false);
		timerPanel.setBackground(Color.pink);
		this.add(timerPanel);

		Font font = new Font("Verdana", Font.BOLD, 32);

		lblTimer = new JLabel("60");
		lblTimer.setBounds(30, 50, 110, 110);
		lblTimer.setFont(font);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setVerticalAlignment(SwingConstants.CENTER);
		lblTimer.setBorder(BorderFactory.createTitledBorder(null, "TIME",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana",
						Font.BOLD, 18), Color.black));
		timerPanel.add(lblTimer);

		/*
		 * ������ �г� ����
		 */
		scorePanel = new JPanel();
		scorePanel.setBounds(0, 215, 170, 215);
		scorePanel.setLayout(null);
		scorePanel.setOpaque(false);
		this.add(scorePanel);

		lblScoreTitle = new JLabel("����");
		lblScoreTitle.setBounds(0, 0, 170, 105);
		lblScoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreTitle.setVerticalAlignment(SwingConstants.CENTER);

		lblScoreNum = new JLabel(nScore + "");
		lblScoreNum.setBounds(0, 0, 170, 105);
		lblScoreNum.setFont(font);
		lblScoreNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreNum.setVerticalAlignment(SwingConstants.CENTER);
		lblScoreNum.setBorder(BorderFactory.createTitledBorder(null, "SCORE",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana",
						Font.BOLD, 18), Color.black));
		scorePanel.add(lblScoreNum);

	}

	public int getScore() {
		return nScore;
	}

	public void setScore(int s) {
		nScore = s;
		lblScoreNum.setText(nScore + "");
	}

	public void setAddScore(int s) {
		nScore += s;
		nScore = nScore < 0 ? 0 : nScore;
		lblScoreNum.setText(nScore + "");
		if (nScore > SettingConstants.LIMIT_SCORE[MoleConstants.nStage - 1]) {
			MoleConstants.bGameNext = true;
			MoleConstants.molePanel.gameStop();

			JOptionPane.showMessageDialog(null, "Next Stage!");
			MoleConstants.nStage++;
			MoleConstants.molePanel.gameStart();
		}
	}//setAddScore(int s)

	public void timerStart() {
		nTime = SettingConstants.TIMER[MoleConstants.nStage - 1];

		timer = new Timer();
		timer.schedule(new GameTimerTask(), 0, 1000);
	}//timerStart()

	public void timerStop() {
		timer.cancel();
	}//timerStop()

	private class GameTimerTask extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			lblTimer.setForeground(Color.black);
			lblTimer.setText(nTime + "");
			nTime--;

			if (nTime < 5 && nTime >= 0) {
				lblTimer.setForeground(Color.red);
			} else if (nTime < 0) {
				MoleConstants.bTimeOver = true;
				timerStop();
				if (MoleConstants.nStage == SettingConstants.FINAL_STAGE) {

					MoleConstants.molePanel.gameStop();

					String name = JOptionPane.showInputDialog(null, "Score : "
							+ MoleConstants.statePanel.getScore() + "\n"
							+ "Please, input your name.", "register",
							JOptionPane.QUESTION_MESSAGE);
					/*
					 * Ȯ�δ����� ��ŷ��� �Ϸ� �߰Բ�.  �̱���
					 * */
					
					//System.out.println(name);
					if(name != null){
						if(!name.toString().equals("")){
							//System.out.println("yes");
							try {
								RankFile.RankFile(name.toString(), MoleConstants.statePanel.getScore());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
					
					
					
					MoleConstants.gamePanel.goHome();

				}//if ������ ���������� ��� �ð� ������ ��ŷ���
				else {
					MoleConstants.molePanel.gameStop();
					JOptionPane.showMessageDialog(null,
							"Time Over...\nGo Home!");
					MoleConstants.gamePanel.goHome();
				}// else �������� ����ȭ������

				System.out.println("Ÿ�ӿ���");
			}//else if nTime<0
		}// run()
	}// inner class GameTimerTask

}
