package view.game;

import controller.Application;
import controller.Constants;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.MoveAble;
import model.objectsModel.EpsilonModel;
import model.objectsModel.OIGModel;

import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements MoveAble {

    public Vector upDownA;
    public Vector leftRightA;
    public Vector upDownV;
    public Vector leftRightV;
    public Vector upDownP;
    public Vector leftRightP;
    private final Vector positionInit;
    private final Vector dimentionInit;

    public static GamePanel gamePanel;
    public static WindowKill windowKill;
    public static EndGame endGame;
    private boolean isResizing;


    public GameFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Constants.GAME_WIDTH + Constants.barD.width ,Constants.GAME_HEIGHT + Constants.barD.height);
        this.setLocationRelativeTo(null);

        positionInit = new Vector(getLocation().getX() ,getLocation().y);
        dimentionInit = new Vector(getSize().width ,getSize().height);
        isResizing = false;

        upDownA = new Vector(0 ,0);
        leftRightA = new Vector(0 ,0);
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

    public Vector getUpDownA() {
        return upDownA;
    }

    public void setUpDownA(Vector upDownA) {
        this.upDownA = upDownA;
    }

    public Vector getLeftRightA() {
        return leftRightA;
    }

    public void setLeftRightA(Vector leftRightA) {
        this.leftRightA = leftRightA;
    }
    public void setLeftRightA(double x ,double y){
        leftRightA = new Vector(x ,y);
    }
    public void setUpDownA(double x ,double y){
        upDownA = new Vector(x ,y);
    }

    public boolean isResizing() {
        return isResizing;
    }

    public void setResizing(boolean resizing) {
        isResizing = resizing;
    }

    @Override
    public void move() {
        setUpDownV(getUpDownV().x + upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,getUpDownV().y + upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE);
        setLeftRightV(getLeftRightV().x + leftRightA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,getLeftRightV().y + leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE);

        Vector upDownMoved = new Vector((2 * getUpDownV().x - upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2 ,(2 * getUpDownV().y - upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
        Vector leftRightMoved = new Vector((2 * getLeftRightV().x - leftRightA.x * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2 ,(2 * getLeftRightV().y - leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);

        if (windowKill.getHeight() > Constants.MINIMUM_FRAME_DIMENSION.height)
            setUpDownP(Utils.VectorAdd(upDownMoved ,getUpDownP()));
        if (windowKill.getWidth() > Constants.MINIMUM_FRAME_DIMENSION.width)
            setLeftRightP(Utils.VectorAdd(leftRightMoved ,getLeftRightP()));
    }

    public void Update(){
        setLocation((int)(positionInit.x - leftRightP.getY()) ,(int)(positionInit.y - upDownP.x));
        setSize((int)(dimentionInit.x + leftRightP.x + leftRightP.y) ,(int) (dimentionInit.y + upDownP.x + upDownP.y));
        UpdateWindowKill();
        revalidate();
    }

    private void UpdateWindowKill() {
        GameFrame.windowKill.setSize(getWidth() - Constants.barD.width ,getHeight() - Constants.barD.height);
    }
}
