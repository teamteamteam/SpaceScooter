package de.teamteamteam.spacescooter.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.teamteamteam.spacescooter.utility.Loader;

/**
 * Provides a static interface for convenient playing of sounds.
 */
public class SoundSystem {

	/**
	 * Internal Mixer instance.
	 * Provides Controls of all sorts for the audio device.
	 */
	private static Mixer mixer = null;
	
	/**
	 * Internal Line instance.
	 * Represents an audio channel of our audio device.
	 */
	private static Line line = null;
	
	/**
	 * Internal CompoundControl containing things like
	 * volume control and more for our line.
	 */
	private static CompoundControl volCtrl = null;

	/**
	 * Internal SourceDataLine - used to pump out audio data which will
	 * be played then.
	 */
	private static SourceDataLine sourceDataLine = null;
	
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private SoundSystem() {}
	
	
	/**
	 * Get mixer info and return available sound cards.
	 */
	public static Mixer.Info[] getAvailableDevices() {
		Mixer.Info[] devices = AudioSystem.getMixerInfo();
		//Print information about available sound devices.
		System.out.println("[Available Devices]");
		for(int i=0; i < devices.length; i++) {
			System.out.println("["+i+"] " + devices[i]);
		}
		System.out.println();
		return devices;
	}
	
	/**
	 * Create mixer by chosen sound card, return available lines.
	 */
	public static Line.Info[] initializeMixer(Mixer.Info mixerInfo) {
		SoundSystem.mixer = AudioSystem.getMixer(mixerInfo);
		try {
			SoundSystem.mixer.open();
		} catch (LineUnavailableException e) {
			System.err.println("Could not open mixer!");
			e.printStackTrace();
		}
		return SoundSystem.mixer.getTargetLineInfo();
	}
	
	/**
	 * Set up line based on chosen line.
	 */
	public static void setUpLine(Line.Info lineInfo) {
		try {
			SoundSystem.line = SoundSystem.mixer.getLine(lineInfo);
		} catch (LineUnavailableException e) {
			System.err.println("Could not get port by lineInfo");
			e.printStackTrace();
		}
		try {
			SoundSystem.line.open();
		} catch (LineUnavailableException e) {
			System.out.println("Could not open port.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a SourceDataLine and play the BufferedInputStream into it.
	 * Uses the internal audioPlayerRunnable since this operation is blocking.
	 */
	public static void playFromAudioInputStream(URL soundURL) {
		final URL fSoundURL = soundURL;
		Thread soundThread = new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream sound = SoundSystem.getAudioInputStreamByURL(fSoundURL);
					sound.reset();
					SoundSystem.sourceDataLine = AudioSystem.getSourceDataLine(sound.getFormat());
					SoundSystem.sourceDataLine.open(sound.getFormat());
					SoundSystem.sourceDataLine.start();
					sound.reset();
					byte[] b = new byte[512];
					while (sound.available() > 0) {
						sound.read(b, 0, 512);
						SoundSystem.sourceDataLine.write(b, 0, 512);
					}
					SoundSystem.sourceDataLine.drain();
					SoundSystem.sourceDataLine.close();
					sound.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		soundThread.setName("Sound: " + soundURL.toString());
		soundThread.start();
	}
	
	/**
	 * This method allows to set the output volume using a float parameter.
	 * TODO: This is not finished yet.
	 */
	public static void setVolume(float volume) {
		Control[] c = SoundSystem.line.getControls();
		SoundSystem.volCtrl = (CompoundControl) SoundSystem.line.getControl(c[0].getType());
		//System.out.println(SoundSystem.volCtrl.getClass());
		
		Control[] subC = SoundSystem.volCtrl.getMemberControls();
		/*System.out.println("[CompoundControl]");
		for(int g=0; g<subC.length; g++) {
			System.out.println("["+g+"] " + subC[g]);
		}
		System.out.println();*/
		FloatControl fc = (FloatControl) subC[0];
		fc.setValue(volume);
		BooleanControl bc = (BooleanControl) subC[1];
		bc.setValue(false);
	}

	/**
	 * Play a sound by passing a relative path to this method.
	 */
	public static void playSound(String filename) {
		SoundSystem.playFromAudioInputStream(Loader.getSoundURLByFilename(filename));
	}

	/**
	 * Open a given sound URL and return an AudioInputStream.
	 * Can be used externally to make sure an audio format can be played by the AudioSystem.
	 */
	public static AudioInputStream getAudioInputStreamByURL(URL soundURL) throws UnsupportedAudioFileException, IOException {
		return AudioSystem.getAudioInputStream(new BufferedInputStream(soundURL.openStream()));
	}
}
