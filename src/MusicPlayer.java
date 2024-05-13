
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;

/**
 * Plays the pacman music. Needed to make it a thread so that game can also run. (new SOUNDMusicPlayer()).start(); will play 
 * the music for your SOUND (Intro, Chomp, Death, Cutscene, Extra, Ghost)

 */
public abstract class MusicPlayer extends Thread{
	
	
	
	public void playMusic(String path) {
		
		try {
			File homeMusic = new File(path);
	        Clip clip;
	        AudioInputStream stream;
			
			stream = AudioSystem.getAudioInputStream(homeMusic);
			

	        
	        AudioFormat format = stream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(stream);
	       // clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
			Thread.sleep(10000); 
	 
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	

	public void playIntro() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	public void playChomp() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	public void playCutscene() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	public void playDeath() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	public void playExtra() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	public void playGhost() {
		playMusic("SoundEffects/Intro.wav");
	}
	
	
	
	
	
	

}
