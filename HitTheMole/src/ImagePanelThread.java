import java.awt.*;
import javax.swing.*;

public class ImagePanelThread extends JPanel implements Runnable {

	private Thread imgMoleThread;
	private int imgInterval;
	private Image[] mole;
	private int imgOrder;
	private int imgWidth;
	private int imgHeight;
	private String[] path;
	
	
	public ImagePanelThread(String path1, String path2, String path3, String path4,int width, int height, int interval){
		this.setPreferredSize(new Dimension(80,80));
		this.setOpaque(false);
		this.setLayout(null);
		
		imgOrder = 0;
		imgInterval = interval;
		imgWidth = width;
		imgHeight = height;
		path = new String[4];
		path[0] = path1;
		path[1]	= path2;
		path[2] = path3;
		path[3] = path4;
		mole = new Image[4];
		for(int i = 0; i<4; i++){
			mole[i] = new ImageIcon(path[i]).getImage();
			mole[i] = mole[i].getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		}
		
		setVisible(true);
		
	}
	
	public void paintComponent(Graphics p){
		super.paintComponent(p);
		
		p.drawImage(mole[0], 0, 0, this);
		
		switch (imgOrder) {
		
		case 0:
			p.drawImage(mole[0], 0, 0, this);
			break;

		case 1:
			p.drawImage(mole[1], 0, 0, this);
			break;
			
		case 2:
			p.drawImage(mole[2], 0, 0, this);
			break;
			
		case 3:
			p.drawImage(mole[3], 0, 0, this);
			break;
			
		default:
			break;
			
		}
		
	}
	
	public int getImgInterval() {
		return imgInterval;
	}

	public void setImgInterval(int imgInterval) {
		this.imgInterval = imgInterval;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	
	public void changeStart(int delay){
		
		if(imgMoleThread != null){
			imgMoleThread = null;
		}
			
		imgMoleThread = new Thread(this);
		try {
			imgMoleThread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			imgMoleThread.start();
		}
		
	}
	
	
	@Override
	public void run() {
			
		try {
			
			while(true){		
				
				imgOrder = 0;
				repaint();
				imgMoleThread.sleep(imgInterval);
				
				imgOrder = 1;
				repaint();
				imgMoleThread.sleep(imgInterval);
				
				imgOrder = 2;
				repaint();
				imgMoleThread.sleep(imgInterval*2);
				
				//imgOrder = 4;
				//repaint();
				imgMoleThread.sleep(imgInterval*2);
				
				imgOrder = 1;
				repaint();
				imgMoleThread.sleep(imgInterval);
				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	// run()

}
