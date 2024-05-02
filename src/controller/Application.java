package controller;

import controller.Config.Configs;
import controller.SoundEffects.Sound;
import view.menu.MainFrame;
import view.game.GameFrame;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Application implements Runnable {
    public static MainFrame mainFrame;
    public static GameFrame gameFrame;

    @Override
    public void run() {
        getImages();
        setConfigs();
        try {
            getAudios();
        }
        catch (Exception e){
            System.out.println("OH NO");
        }
        mainFrame = new MainFrame();
    }

    private void setConfigs() {
        File file = new File("src/controller/Config/Configs.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            Configs.XP = Integer.valueOf(scanner.nextLine().substring(4));
            GameState.xp = Configs.XP;
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAudios() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Constants.backGroundSound = "src/controller/SoundEffects/Song.wav";
        Constants.BulletFiredSound = "src/controller/SoundEffects/Bullet Fired.wav";
        Constants.waveSpawnSound = "src/controller/SoundEffects/Wave Spawn.wav";
        Sound sound = new Sound(Constants.backGroundSound);
        Sound.volumeUp();
        Sound.volumeDown();
        sound.loop();
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
