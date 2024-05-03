package controller;

import controller.Config.Configs;
import controller.SoundEffects.Sound;
import view.Abilities.Aceso;
import view.Abilities.Ares;
import view.Abilities.Proteus;
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
            Ares.isAvailable = Integer.valueOf(scanner.nextLine().substring(6));
            Aceso.isAvailable = Integer.valueOf(scanner.nextLine().substring(7));
            Proteus.isAvailable = Integer.valueOf(scanner.nextLine().substring(9));
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAudios() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Constants.backGroundSound = "src/controller/SoundEffects/Song.wav";
        Constants.BulletFiredSound = "src/controller/SoundEffects/Bullet Fired.wav";
        Constants.waveSpawnSound = "src/controller/SoundEffects/Wave Spawn.wav";
        Constants.enemyOnDeathSound = "src/controller/SoundEffects/EnemyOnDeath.wav";
        Constants.impactSound = "src/controller/SoundEffects/ImpactSound.wav";
        Constants.endSound = "src/controller/SoundEffects/endSound.wav";
        Constants.winSound = "src/controller/SoundEffects/winSound.wav";
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
            Constants.ares = ImageIO.read(new File("src/view/game/Ares.png"));
            Constants.aceso = ImageIO.read(new File("src/view/game/Aceso.png"));
            Constants.proteus = ImageIO.read(new File("src/view/game/Proteus.png"));
            Constants.endGameImage = ImageIO.read(new File("src/view/game/GameOver.png"));
        }
        catch (Exception e){
            System.out.println("File Not Found!");
        }
    }
}
