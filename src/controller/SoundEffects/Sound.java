package controller.SoundEffects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    private static float previousVolume = -11.2f;
    private static float currentVolume = -11.2f;
    private static FloatControl fc;
    private static boolean mute = false;

    public Sound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path));
        clip = AudioSystem.getClip();
        clip.open(sound);
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    public void play(URL url){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(String url){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(URL url){
        clip.stop();
    }
    public static void volumeUp(){
        currentVolume += 3.44f;
        if (currentVolume > 6.0f){
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public static void volumeDown(){
        currentVolume -= 3.44f;
        if (currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public static void volumeMute(){
        if (!mute){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }
}
