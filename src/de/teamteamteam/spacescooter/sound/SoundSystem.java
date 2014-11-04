import java.io.BufferedInputStream;
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


public class SoundSystem {

	private static Mixer mixer = null;
	private static Line line = null;
	private static CompoundControl volCtrl = null;
	private static SourceDataLine sourceDataLine = null;


	/**
	 * Get mixer info and return available sound cards.
	 */
	public static Mixer.Info[] getAvailableDevices() {
		Mixer.Info[] devices = AudioSystem.getMixerInfo();
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
	 */
	public static void playFromInputStream(BufferedInputStream inputStream) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
			SoundSystem.sourceDataLine = AudioSystem.getSourceDataLine(audioInputStream.getFormat());
			SoundSystem.sourceDataLine.open(audioInputStream.getFormat());
			SoundSystem.sourceDataLine.start();
			inputStream.reset();
			byte[] b = new byte[512];
			while (inputStream.available() > 0) {
				inputStream.read(b, 0, 512);
				SoundSystem.sourceDataLine.write(b, 0, 512);
			}
			SoundSystem.sourceDataLine.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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

	}
}
