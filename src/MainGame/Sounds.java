package MainGame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds
{
	List<File> soundList;
	
	Sounds()
	{
		soundList = new ArrayList<File>();	
	}
	
	public void addSound(File file)
	{
		soundList.add(file);
	}
	public void playSound(int postion)
	{
		 try {
				playClip(soundList.get(postion));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	private static void playClip(File clipFile) throws IOException, 
	  UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
	  class AudioListener implements LineListener{
	    private boolean done = false;
	    @Override public synchronized void update(LineEvent event) {
	    	javax.sound.sampled.LineEvent.Type eventType = event.getType();
	      if (eventType == javax.sound.sampled.LineEvent.Type.STOP || eventType == javax.sound.sampled.LineEvent.Type.CLOSE) {
	        done = true;
	        notifyAll();
	      }
	    }
	    public synchronized void waitUntilDone() throws InterruptedException {
	      while (!done) 
	      { 
	    	  wait(); 
	      }
	    }
		
	  }
	  
	 
	  AudioListener listener = new AudioListener();
	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
	  
	
	 
	  try {
		Clip clip = AudioSystem.getClip();
	    clip.addLineListener(listener);
	    clip.open(audioInputStream);
	  
	    try {
	      clip.start();
	      listener.waitUntilDone();
	    } finally {
	      clip.close();
	    }
	    
	  } finally {
	    audioInputStream.close();
	  }
	}
}