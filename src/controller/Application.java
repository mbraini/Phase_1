package controller;

import view.menu.MainFrame;
import view.game.GameFrame;

import javax.imageio.ImageIO;
import java.io.File;

public class Application implements Runnable {
    public static MainFrame mainFrame;
    public static GameFrame gameFrame;

    @Override
    public void run() {
        getImages();
        mainFrame = new MainFrame();
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
