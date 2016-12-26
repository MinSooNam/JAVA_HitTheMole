import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundPlay {

	public static Clip bgmClip;
	
	public SoundPlay(){
		bgmClip = null;
	}
	

	 public static void playSound(File file, float vol, boolean repeat){
	         try{
	                 final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	                 clip.open(AudioSystem.getAudioInputStream(file));
	                 clip.addLineListener(new LineListener() {
	                         @Override
	                         public void update(LineEvent event) {
	                                 // TODO Auto-generated method stub
	                                 if(event.getType()==LineEvent.Type.STOP){
	                                         //�� �κ��� ������ ȿ������ �޸𸮿� ���� �׿��� ������ ũ���õȴ�
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
	 
	 public static void playBgm(File file, float vol, boolean repeat){
		 try{
                //BGM�� ������ �������� ������ų �� �־�� �ϹǷ� �������� ���� Clip�� ����Ѵ�
			 bgmClip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			 bgmClip.open(AudioSystem.getAudioInputStream(file));
			 bgmClip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                                // TODO Auto-generated method stub
                                System.out.println("" + event.getType());
                                if(event.getType()==LineEvent.Type.STOP){
                                	bgmClip.close();
                                }
                        }
                });
                FloatControl volume = (FloatControl)bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(vol);
                bgmClip.start();
                if(repeat)
                	bgmClip.loop(bgmClip.LOOP_CONTINUOUSLY);
        }catch(Exception e){
                e.printStackTrace();
        }
	 }
	 public static void stopBgm(){
	        
	         if(bgmClip!=null && bgmClip.isRunning()){
	        	 bgmClip.stop();
	        	 bgmClip.close();
	        	 bgmClip = null;
	         }
	 }		

}
