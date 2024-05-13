
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;

/**
 * Plays the pacman music. Needed to make it a thread so that game can also run. (new SOUNDMusicPlayer()).start(); will play 
 * the music for your SOUND (Intro, Chomp, Death, Cutscene, Extra, Ghost)

 */
public abstract class MusicPlayer extends Thread{
	public boolean isPlaying;
	private Clip clip;
	
	public abstract void run();
	
	public void playMusic(String path) {
		
	
		try {
				File homeMusic = new File(path);
		        
		        AudioInputStream stream;
				
				stream = AudioSystem.getAudioInputStream(homeMusic);
				
	
		        
		        AudioFormat format = stream.getFormat();
		        DataLine.Info info = new DataLine.Info(Clip.class, format);
		        clip = (Clip) AudioSystem.getLine(info);
		        clip.open(stream);
		        
	
		        clip.start();
		        
		        isPlaying = true;
		        
		        LineListener listener = new LineListener() {
		            
					public void update(LineEvent event) {
						if (event.getType() == Type.STOP) {
							isPlaying = false;
						}
					}
					
		        };
		        clip.addLineListener(listener);
		        Thread.sleep(1000); 
		 
			} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	
	public void stopPlaying() {
		clip.stop();
	}
	
	public void startAgain() {
		clip.start();
	}


		
	

}

