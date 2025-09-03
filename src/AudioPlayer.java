import javax.sound.sampled.*;
import java.io.*;

public class AudioPlayer {
    private Clip clip;
    private FloatControl volumeControl;
    private float volume = 1.0f;

    public AudioPlayer() {
        clip = null;
        volumeControl = null;
    }

    public void playAudioLoop(String filePath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/" + filePath);
            if (audioSrc == null) {
                // Fallback to file system if not found in resources
                audioSrc = new FileInputStream("resources/" + filePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Get the volume control
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(volume);
            }

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playAudio(String filePath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/" + filePath);
            if (audioSrc == null) {
                // Fallback to file system if not found in resources
                audioSrc = new FileInputStream("resources/" + filePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Set up volume control
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // Apply the stored volume to the new clip
                setVolume(volume);
            }

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float volume) {
        // Store the volume for future use
        this.volume = Math.max(0.0f, Math.min(1.0f, volume));

        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float range = volumeControl.getMaximum() - min;
            volumeControl.setValue((range * this.volume) + min);
        }
    }

    public float getVolume() {
        return volume;
    }

    public void stopAudio() {
        clip.stop();
        clip.close();
        volumeControl = null;
    }
}
