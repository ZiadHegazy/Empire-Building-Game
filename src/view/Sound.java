package view;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	Clip clip;
	 long playSound(String path) throws IOException {
		 		    try {
		 		      AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
		 		      clip = AudioSystem.getClip();
		 		      clip.open(audio);
		 		      clip.start();
		 		      return clip.getMicrosecondLength();
		 		    } catch (UnsupportedAudioFileException | LineUnavailableException e) {
		 		      e.printStackTrace();
		 		    }
		 		    return 0;
		 		  }


	public void stopSound(){
		clip.stop();
	}
	}

