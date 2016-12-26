import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MolePanel extends JPanel {

	private MolePanelUnit molePanelUnit[];
	private MoleThread moleThread;

	private PanelListener panelUnitL;

	private Image imgMole1;
	private Image imgMole2;
	private Image imgMole3;
	private Image imgMole4;
	private Image imgMole5;
	private Image imgMole6;
	private Image imgMole7;

	private Image imgCursorRe;
	private Image imgCursorPre;

	private Toolkit toolkit;
	private Cursor cursorhammerRe;
	private Cursor cursorhammerPre;
	private Cursor normalCursor;
	private Clip bgmclip;

	public MolePanel() {
		init();
		setView();
	}// MolePanel()

	private void init() {
		
		/*
		 * 이미지 셋팅
		 * */
		imgMole1 = new ImageIcon("./img/mole1.png").getImage();
		imgMole2 = new ImageIcon("./img/mole2.png").getImage();
		imgMole3 = new ImageIcon("./img/mole3.png").getImage();
		imgMole4 = new ImageIcon("./img/mole4.png").getImage();
		imgMole5 = new ImageIcon("./img/mole5.png").getImage();
		imgMole6 = new ImageIcon("./img/mole6.png").getImage();
		imgMole7 = new ImageIcon("./img/mole7.png").getImage();

		/*
		 * 마우스 커서 이미지 설정
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

	}// init() 초기화

	private void setView() {
		// this.setBackground(Color.ORANGE);
		// this.setBackground(new Color(255, 0, 0, 0));
		this.setOpaque(false);
		this.setLayout(new GridLayout(3, 3));

		panelUnitL = new PanelListener();

		molePanelUnit = new MolePanelUnit[SettingConstants.MOLE_NUMBER];

		for (int i = 0; i < SettingConstants.MOLE_NUMBER; i++) {
			molePanelUnit[i] = new MolePanelUnit();
			molePanelUnit[i].addMouseListener(panelUnitL);
			this.add(molePanelUnit[i]);
		}

		gameStart();
	}// setView() 뷰 설정

	public void gameStart() {
		SoundPlay.playBgm(new File("./sound/bgm.wav"), 1.0f, false);
		
		MoleConstants.bGameNext = false;

		moleThread = new MoleThread();
		moleThread.start();
		if (MoleConstants.nStage == 4) {
			moleThread = new MoleThread();
			moleThread.start();
		}

		MoleConstants.statePanel.timerStart();
		MoleConstants.topPanel.setHeart(MoleConstants.nHeart);
		MoleConstants.topPanel.setStageLabel(MoleConstants.nStage);

	}//gameStart()

	public void gameStop() {
		SoundPlay.stopBgm();
		MoleConstants.statePanel.timerStop();
		for (int i = 0; i < SettingConstants.MOLE_NUMBER; i++) {
			molePanelUnit[i].drawMoleUnit(1);
		}
	}//gameStop()

	private class MoleThread extends Thread {

		@Override
		public synchronized void start() {
			// TODO Auto-generated method stub
			super.start();
		}//start()

		public void run() {
			int nRandom;
			int moleIndex, lastIndex = 0;
			int stage = MoleConstants.nStage;

			while (!MoleConstants.bGameNext && !MoleConstants.bTimeOver
					&& !MoleConstants.bHeartLack) {
				try {
					nRandom = (int) (Math.random() * 9);
					moleIndex = (nRandom == lastIndex) ? (int) (Math.random() * 9)
							: nRandom;
					lastIndex = moleIndex;
					sleep(100 + (int) (Math.random() * 7 - stage) * 100);

					molePanelUnit[moleIndex].drawMoleUnit(2);
					sleep(100);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					molePanelUnit[moleIndex].drawMoleUnit(3);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					sleep(200);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					sleep(200);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					sleep(200);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					sleep(200);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					molePanelUnit[moleIndex].drawMoleUnit(2);
					sleep(100);
					if (molePanelUnit[moleIndex].nState == 4) {
						continue;
					}
					molePanelUnit[moleIndex].drawMoleUnit(1);
				} catch (Exception e) {
					System.out.println("MoleThread catch");
				}

			}// while
		}// run()
	}// inner class MoleThread

	private class MolePanelUnit extends JPanel {

		private int nState = 1;

		MolePanelUnit() {
			this.setPreferredSize(new Dimension(170, 170));
			this.setOpaque(false);
		}// MolePanelUnit

		public void paintComponent(Graphics page) {
			super.paintComponent(page);

			switch (nState) {
			case 1:
				page.drawImage(imgMole1, 20, 0, this);
				nState = 1;
				break;
			case 2:
				page.drawImage(imgMole2, 20, 0, this);
				nState = 2;
				break;
			case 3:
				page.drawImage(imgMole3, 20, 0, this);
				nState = 3;
				break;
			case 4:
				page.drawImage(imgMole4, 20, 0, this);
				nState = 4;
				break;
			case 5:
				page.drawImage(imgMole5, 20, 0, this);
				nState = 5;
				break;
			case 6:
				page.drawImage(imgMole6, 20, 0, this);
				nState = 6;
				break;
			case 7:
				page.drawImage(imgMole7, 20, 0, this);
				nState = 7;
				break;
			default:
				page.drawImage(imgMole1, 20, 0, this);
				nState = 1;
				break;

			}// switch
		} // paintComponent()

		public void drawMoleUnit(int n) {
			nState = n;
			repaint();
		}// drawMoleUnit(int n)

	}// inner class MolePanelUnit

	private class PanelListener implements MouseListener {

		private TimeThread timeThread;
		private int i;

		@Override
		public void mouseClicked(MouseEvent event) {
		}

		@Override
		public void mousePressed(MouseEvent event) {
			setCursor(cursorhammerPre);
			Object obj = event.getSource();

			for (i = 0; i < SettingConstants.MOLE_NUMBER; i++) {
				if (obj == molePanelUnit[i]) {

					switch (molePanelUnit[i].nState) {
					case 1:
						// 점수마이너스
						MoleConstants.statePanel
								.setAddScore(SettingConstants.SUB_SCORE[MoleConstants.nStage - 1]);
						MoleConstants.topPanel.setHeart(--MoleConstants.nHeart);
						threadStart(1);

						break;
					case 2:
						// 점수 3배
						MoleConstants.statePanel
								.setAddScore(SettingConstants.ADD_SCORE[MoleConstants.nStage - 1] * 3);
						threadStart(2);

						break;
					case 3:
						// 점수
						MoleConstants.statePanel
								.setAddScore(SettingConstants.ADD_SCORE[MoleConstants.nStage - 1]);
						threadStart(3);

						break;
					case 4:

						break;
					case 5:

						break;
					case 6:
						// 시간 추가.
						break;
					case 7:

						break;
					default:

						break;
					}
					break;
				}//if
			}//for
		}//mousePressed()

		@Override
		public void mouseReleased(MouseEvent event) {
			setCursor(cursorhammerRe);
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			setCursor(cursorhammerRe);
		}

		@Override
		public void mouseExited(MouseEvent event) {
			setCursor(normalCursor);

		}

		private void threadStart(int n) {
			timeThread = new TimeThread();
			timeThread.nMode = n;
			timeThread.start();

		}

		private class TimeThread extends Thread {
			private int nMode = 0;
			private int index = i;

			@Override
			public synchronized void start() {
				// TODO Auto-generated method stub
				super.start();
			}

			public void run() {
				try {
					if (nMode == 1) {
						SoundPlay.playSound(new File("./sound/wrong.wav"), 1.0f, false);
						molePanelUnit[index].drawMoleUnit(5);
						sleep(300);
						molePanelUnit[index].drawMoleUnit(1);
					}// 실수로 눌렀을 때.
					else if (nMode == 2 || nMode == 3) {
						SoundPlay.playSound(new File("./sound/hit.wav"), 1.0f, false);
						molePanelUnit[index].drawMoleUnit(4);
						sleep(300);
						molePanelUnit[index].drawMoleUnit(1);
					}// 잡았을 때.

				} catch (Exception e) {
					System.out.println("TimeThread catch");
				}
			}
		}//PanelListener inner class TimeThread
	}// inner class PanelListener
	
	/*
	 public void playSound(File file, float vol, boolean repeat){
	         try{
	                 final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	                 clip.open(AudioSystem.getAudioInputStream(file));
	                 clip.addLineListener(new LineListener() {
	                         @Override
	                         public void update(LineEvent event) {
	                                 // TODO Auto-generated method stub
	                                 if(event.getType()==LineEvent.Type.STOP){
	                                         //이 부분이 없으면 효과음이 메모리에 점점 쌓여서 언젠가 크래시된다
	                                         clip.close();
	                                 }
	                         }
	                 });
	                 FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	                 volume.setValue(vol);
	                 clip.start();
	                 if(repeat)
	                         clip.loop(Clip.LOOP_CONTINUOUSLY);
	         }catch(Exception e){
	                 e.printStackTrace();
	         }
	 }
	 
	 public void playBgm(File file, float vol, boolean repeat){
		 try{
                 //BGM은 임의의 시점에서 정지시킬 수 있어야 하므로 전역으로 전용 Clip을 사용한다
                 bgmclip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
                 bgmclip.open(AudioSystem.getAudioInputStream(file));
                 bgmclip.addLineListener(new LineListener() {
                         @Override
                         public void update(LineEvent event) {
                                 // TODO Auto-generated method stub
                                 System.out.println("" + event.getType());
                                 if(event.getType()==LineEvent.Type.STOP){
                                         bgmclip.close();
                                 }
                         }
                 });
                 FloatControl volume = (FloatControl)bgmclip.getControl(FloatControl.Type.MASTER_GAIN);
                 volume.setValue(vol);
                 bgmclip.start();
                 if(repeat)
                         bgmclip.loop(bgmclip.LOOP_CONTINUOUSLY);
         }catch(Exception e){
                 e.printStackTrace();
         }
	 }
	 public void stopBgm(){
	        
	         if(bgmclip!=null && bgmclip.isRunning()){
	                 bgmclip.stop();
	                 bgmclip.close();
	                 bgmclip = null;
	         }
	 }		
	*/
	
}// class MolePanel
