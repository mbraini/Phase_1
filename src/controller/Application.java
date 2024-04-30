package controller;

import controller.SoundEffects.Sound;
import view.menu.MainFrame;
import view.game.GameFrame;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Application implements Runnable {
    public static MainFrame mainFrame;
    public static GameFrame gameFrame;

    @Override
    public void run() {
        getImages();
        try {
            getAudios();
        }
        catch (Exception e){
            System.out.println("OH NO");
        }
        mainFrame = new MainFrame();
    }

    private void getAudios() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Constants.backGroundSound = "src/controller/SoundEffects/Song.wav";
        Sound sound = new Sound(Constants.backGroundSound);
        sound.loop(Constants.backGroundSound);
    }

    void getImages(){
        try {
            Constants.epsilonImage = ImageIO.read(new File("src/view/objectsView/epsilonImage.png"));
            Constants.trigorathImage = ImageIO.read(new File("src/view/objectsView/trigorathImage.png"));
            Constants.squarantineImage = ImageIO.read(new File("src/view/objectsView/squarantineImage.png"));
            Constants.banish = ImageIO.read(new File("src/view/game/Banish.png"));
            Constants.empower = ImageIO.read(new File("src/view/game/Empower.png"));
            Constants.heal = ImageIO.read(new File("src/view/game/Heal.png"));
        }
        catch (Exception e){
            System.out.println("File Not Found!");
        }
    }
}
