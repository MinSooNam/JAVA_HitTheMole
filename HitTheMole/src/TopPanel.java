import java.awt.*;

import javax.swing.*;

public class TopPanel extends JPanel {

	private JLabel stageLabel;
	private JLabel heartLabel[];

	public TopPanel() {
		this.setBounds(0, 0, 800, 90);
		this.setLayout(null);
		this.setOpaque(false);

		Font font = new Font("Malgun Gothic", Font.BOLD, 32);

		stageLabel = new JLabel(SettingConstants.ROUND[0]);
		stageLabel.setBounds(30, 0, 500, 90);
		stageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		stageLabel.setVerticalAlignment(SwingConstants.CENTER);
		stageLabel.setFont(font);
		this.add(stageLabel);

		ImageIcon heartIcon = new ImageIcon("./img/heart.png");
		heartLabel = new JLabel[SettingConstants.MAX_HEART];
		for (int i = 0; i < SettingConstants.MAX_HEART; i++) {
			heartLabel[i] = new JLabel("", heartIcon, SwingConstants.CENTER);
			heartLabel[i].setBounds(400 + (i * 80), 13, 64, 64);
			heartLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			heartLabel[i].setVerticalAlignment(SwingConstants.CENTER);
			this.add(heartLabel[i]);
		}
	}// TopPanel()

	public void setStageLabel(int s) {
		stageLabel.setText(SettingConstants.ROUND[s - 1]);
	}

	public void setHeart(int n) {
		// n은 최대개수를 넘으면 안된다.
		if (n > 0 && n <= SettingConstants.MAX_HEART) {
			for (int i = 0; i < SettingConstants.MAX_HEART; i++) {
				heartLabel[i].setVisible(false);
			}
			for (int i = 0; i < n; i++) {
				heartLabel[i].setVisible(true);
			}
		} else if (n <= 0) {
			for (int i = 0; i < SettingConstants.MAX_HEART; i++) {
				heartLabel[i].setVisible(false);
			}
			MoleConstants.bHeartLack = true;
			MoleConstants.molePanel.gameStop();
			JOptionPane.showMessageDialog(null, "No Haert... \nGo Home!");
			MoleConstants.gamePanel.goHome();

			System.out.println("하트부족");
		}//else if n<=0
	}//setHeart()
}//class TopPanel
