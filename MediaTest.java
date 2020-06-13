package blackjack;
/* MediaTest.java
 */

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MediaTest {
		File bgmfile;					
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		Clip clip;
		
	   public void a() {				//BGM to run in the login window
	          bgmfile = new File("./sound/bgm.wav"); 
	      try {
	             stream = AudioSystem.getAudioInputStream(bgmfile);
	             format = stream.getFormat();
	             info = new DataLine.Info(Clip.class, format);
	             clip = (Clip)AudioSystem.getLine(info);
	             clip.open(stream);
	             clip.start();
               clip.loop(Clip.LOOP_CONTINUOUSLY);
	      } catch (Exception e) {
	             System.out.println("err : " + e);
	                 }  
	   }
	   
	   public void b() {				//BGM to run in the game window
	       bgmfile = new File("./sound/bgm2.wav");
	   try {
	          stream = AudioSystem.getAudioInputStream(bgmfile);
	          format = stream.getFormat();
	          info = new DataLine.Info(Clip.class, format);
	          clip = (Clip)AudioSystem.getLine(info);
	          clip.open(stream);
	          clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);   
	   } catch (Exception e) {
	          System.out.println("err : " + e);
	              }  
	}
	   
	   public void button() {			//Sound effect when a button is pressed
	       bgmfile = new File("./sound/button-3.wav");
	   try {
	          stream = AudioSystem.getAudioInputStream(bgmfile);
	          format = stream.getFormat();
	          info = new DataLine.Info(Clip.class, format);
	          clip = (Clip)AudioSystem.getLine(info);
	          clip.open(stream);
	          clip.start();
	   } catch (Exception e) {
	          System.out.println("err : " + e);
	              }  
	}
	   
	 public void stop() {
		 clip.close();
	 }
}
