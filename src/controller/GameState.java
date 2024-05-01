package controller;

import controller.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameState extends JPanel{
    Timer timer;
    public static double time;
    public static double xp;
    public static double hp;
    public static double wave;
    JLabel timeL;
    JLabel hpL;
    JLabel xpL;
    JLabel waveL;
    public static boolean isPause = false;
    public static boolean isOver = false;
    public GameState(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT / 10);
        this.setBackground(Color.BLACK);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPause)
                    return;
                if (isOver){
                    timer.stop();
                    System.out.println("OVER");
                }
                time += 0.1;
            }
        });
        time = 0;
        hp = 100;
        wave = 1;
        xp = Configs.XP;
        InitTime();
        InitXP();
        InitWave();
        InitHP();
        timer.start();
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        GameState.time = time;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        GameState.xp = xp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        GameState.hp = hp;
    }

    public double getWave() {
        return wave;
    }

    public void setWave(double wave) {
        GameState.wave = wave;
    }

    void InitTime(){
        timeL = new JLabel();
        timeL.setBounds(10 ,20 ,50 ,50);
        timeL.setText((int)time + "");
        timeL.setBackground(Color.WHITE);
        timeL.setOpaque(true);
        timeL.setHorizontalAlignment(JLabel.CENTER);
        timeL.setVerticalAlignment(JLabel.CENTER);
        this.add(timeL);
    }

    void InitHP(){
        hpL = new JLabel();
        hpL.setBounds(10 ,20 ,50 ,50);
        hpL.setText((int)hp + "");
        hpL.setBackground(Color.WHITE);
        hpL.setOpaque(true);
        hpL.setHorizontalAlignment(JLabel.CENTER);
        hpL.setVerticalAlignment(JLabel.CENTER);
        this.add(hpL);
    }

    void InitXP(){
        xpL = new JLabel();
        xpL.setBounds(10 ,20 ,50 ,50);
        xpL.setText((int)xp + "");
        xpL.setBackground(Color.WHITE);
        xpL.setOpaque(true);
        xpL.setHorizontalAlignment(JLabel.CENTER);
        xpL.setVerticalAlignment(JLabel.CENTER);
        this.add(xpL);
    }

    void InitWave(){
        waveL = new JLabel();
        waveL.setBounds(10 ,20 ,50 ,50);
        waveL.setText((int)wave + "");
        waveL.setBackground(Color.WHITE);
        waveL.setOpaque(true);
        waveL.setHorizontalAlignment(JLabel.CENTER);
        waveL.setVerticalAlignment(JLabel.CENTER);
        this.add(waveL);
    }
}
