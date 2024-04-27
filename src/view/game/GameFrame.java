package view.game;

import controller.Constants;
import controller.helper.Vector;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;

public class GameFrame extends JFrame {

    public Vector upDownV;
    public Vector leftRightV;
    public Vector upDownP;
    public Vector leftRightP;

    public static GamePanel gamePanel;
    public static WindowKill windowKill;
    public static EndGame endGame;


    public GameFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Constants.GAME_WIDTH + Constants.barD.width ,Constants.GAME_HEIGHT + Constants.barD.height);
        this.setLocationRelativeTo(null);

        upDownV = new Vector(0 ,0);
        leftRightV = new Vector(0 ,0);
        upDownP = new Vector(0 ,0);
        leftRightP = new Vector(0 ,0);

        gamePanel = new GamePanel();

        this.setContentPane(gamePanel);

        windowKill = new WindowKill();
        endGame = new EndGame();

        gamePanel.add(windowKill);
        gamePanel.add(endGame);

        this.setVisible(true);
    }

    public void UpAddSize(int size){
        this.setBounds(this.getX() ,this.getY() - size ,this.getWidth() ,this.getHeight() + size);
        windowKill.AddSize(0 ,size);
        setOIGs(0 ,size);
        this.revalidate();
        this.repaint();
    }

    public void RightAddSize(int size){
        this.setBounds(this.getX() ,this.getY() ,this.getWidth() + size ,this.getHeight());
        windowKill.AddSize(size ,0);
        setOIGs(size ,0);
        this.revalidate();
        this.repaint();
    }

    public void DownAddSize(int size){
        this.setBounds(this.getX() ,this.getY() ,this.getWidth() ,this.getHeight() + size);
        windowKill.AddSize(0 ,size);
        this.revalidate();
        this.repaint();
    }

    public void LeftAddSize(int size){
        this.setBounds(this.getX() - size ,this.getY() ,this.getWidth() + size ,this.getHeight());
        windowKill.AddSize(size ,0);
        this.revalidate();
        this.repaint();
    }

    public Vector getUpDownV() {
        return upDownV;
    }

    public void setUpDownV(Vector upDownV) {
        this.upDownV = upDownV;
    }

    public Vector getLeftRightV() {
        return leftRightV;
    }

    public void setLeftRightV(Vector leftRightV) {
        this.leftRightV = leftRightV;
    }

    public void setUpDownV(double x , double y) {
        this.upDownV = new Vector(x ,y);
    }

    public void setLeftRightV(double x , double y) {
        this.leftRightV = new Vector(x ,y);
    }

    public Vector getUpDownP() {
        return upDownP;
    }

    public void setUpDownP(Vector upDownP) {
        this.upDownP = upDownP;
    }

    public Vector getLeftRightP() {
        return leftRightP;
    }

    public void setLeftRightP(Vector leftRightP) {
        this.leftRightP = leftRightP;
    }

    public void setUpDownP(double x ,double y) {
        this.upDownP = new Vector(x ,y);
    }

    public void setLeftRightP(double x ,double y) {
        this.leftRightP = new Vector(x ,y);
    }
    public void setOIGs(int x ,int y){
        for (int i = 0 ;i < OIGModel.OIGs.size() ;i++){
            OIGModel.OIGs.get(i).setPosition(OIGModel.OIGs.get(i).getPosition().x + x ,OIGModel.OIGs.get(i).getPosition().y + y);
            if (OIGModel.OIGs.get(i) instanceof EpsilonModel)
                ((EpsilonModel) OIGModel.OIGs.get(i)).UpdateVertices(x ,y ,0);
        }
    }
}
