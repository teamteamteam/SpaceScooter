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

import de.teamteamteam.spacescooter.brain.GameConfig;
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
	public static Thread playFromAudioInputStream(URL soundURL) {
		final URL fSoundURL = soundURL;
		Thread soundThread = new Thread(new Runnable() {
			public void run() {
				AudioInputStream sound = null;
				SourceDataLine sourceDataLine = null;
				try {
					sound = SoundSystem.getAudioInputStreamByURL(fSoundURL);
					sound.reset();
					sourceDataLine = AudioSystem.getSourceDataLine(sound.getFormat());
					sourceDataLine.open(sound.getFormat());
					sourceDataLine.start();
					sound.reset();
					int maxChunkSize = 4*4096;
					byte[] b = new byte[maxChunkSize];
					int soundCapacity;
					while ((soundCapacity = sound.available()) > 0) {
						if(Thread.interrupted()) throw new InterruptedException();
						//Make sure we're only writing that many bytes, so we do NOT cause blocking!
						//Otherwise, we are unable to interrupt this Thread properly and stop long sounds from playing!
						int writeCapacity = sourceDataLine.available();
						if(writeCapacity > soundCapacity) writeCapacity = soundCapacity;
						if(writeCapacity > maxChunkSize) writeCapacity = maxChunkSize;
						sound.read(b, 0, writeCapacity);
						sourceDataLine.write(b, 0, writeCapacity);
					}
					sourceDataLine.drain();
				} catch (javax.sound.sampled.LineUnavailableException lue) {
					if(GameConfig.DEBUG) System.err.println("Could not play sound: " + fSoundURL);
				} catch (InterruptedException ie) {
					//Nothing to do here, just falling into finally block, so we can
					//close the sourceDataLine without draining it.
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					//Clean everything up properly.
					if(sourceDataLine != null) sourceDataLine.close();
					if(sound != null) {
						try {
							sound.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		soundThread.setName("Sound: " + soundURL.toString());
		soundThread.start();
		return soundThread;
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
	public static Thread playSound(String filename) {
		return SoundSystem.playFromAudioInputStream(Loader.getSoundURLByFilename(filename));
	}

	/**
	 * Open a given sound URL and return an AudioInputStream.
	 * Can be used externally to make sure an audio format can be played by the AudioSystem.
	 */
	public static AudioInputStream getAudioInputStreamByURL(URL soundURL) throws UnsupportedAudioFileException, IOException {
		return AudioSystem.getAudioInputStream(new BufferedInputStream(soundURL.openStream()));
	}
}
