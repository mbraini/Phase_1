package view.game;

import controller.*;
import view.Abilities.RegularAbility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends JPanel {
    JButton next;
    JButton previous;
    JButton back;
    JPanel first;
    JPanel second;
    JPanel third;
    JLabel hp;
    JLabel xp;
    JLabel wave;
    JLabel time;

    public Shop(){
        this.setLayout(null);
        this.setBounds(0,0, Application.gameFrame.getWidth() - Constants.barD.width, Application.gameFrame.getHeight() - Constants.barD.height);
        this.setBackground(Color.BLUE);
        initContainers();
        initBanish();
        initEmpower();
        initHeal();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && GameState.isPause){
                    WindowKill.gameLoop = new GameLoop();
                    WindowKill.shop.setVisible(false);
                    GameFrame.gamePanel.remove(WindowKill.shop);
                    GameState.isPause = false;
                    GameFrame.windowKill.setVisible(true);
                    GameFrame.windowKill.setFocusable(true);
                    GameFrame.windowKill.grabFocus();
                    WindowKill.gameLoop.start();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.grabFocus();
        this.setFocusable(true);
        initHP();
        initTime();
        initWave();
        initXP();
        initBack();
        this.setVisible(true);
    }

    private void initContainers() {
        first = new JPanel();
        second = new JPanel();
        third = new JPanel();
        first.setOpaque(false);
        second.setOpaque(false);
        third.setOpaque(false);
        this.add(first);
        this.add(second);
        this.add(third);
    }

    private void initHeal() {
        first.setBounds(getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        first.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("HEAL");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initEmpower() {
        second.setBounds(getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        second.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegularAbility.sendRequest(RegularAbilitiesEnum.empower);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initBanish() {
        third.setBounds(getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        third.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegularAbility.sendRequest(RegularAbilitiesEnum.banish);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    void initHP(){
        hp = new JLabel();
        hp.setBounds(getWidth() / 9 ,getHeight() / 10 ,getWidth() / 9 ,getHeight() / 10);
        hp.setText("" + (int)GameState.hp);
        hp.setBackground(Color.WHITE);
        hp.setOpaque(true);
        hp.setHorizontalAlignment(JLabel.CENTER);
        hp.setVerticalAlignment(JLabel.CENTER);
        this.add(hp);
    }

    void initXP(){
        xp = new JLabel();
        xp.setBounds(getWidth() / 9 * 3 ,getHeight() / 10 ,getWidth() / 9 ,getHeight() / 10);
        xp.setText("" + (int)GameState.xp);
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);
    }

    void initWave(){
        wave = new JLabel();
        wave.setBounds(getWidth() / 9 * 5,getHeight() / 10 ,getWidth() / 9 ,getHeight() / 10);
        wave.setText("" + (int)GameState.wave);
        wave.setBackground(Color.WHITE);
        wave.setOpaque(true);
        wave.setHorizontalAlignment(JLabel.CENTER);
        wave.setVerticalAlignment(JLabel.CENTER);
        this.add(wave);
    }

    void initTime(){
        time = new JLabel();
        time.setBounds(getWidth() / 9 * 7 ,getHeight() / 10 ,getWidth() / 9 ,getHeight() / 10);
        time.setText("" + (int)GameState.time);
        time.setBackground(Color.WHITE);
        time.setOpaque(true);
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setVerticalAlignment(JLabel.CENTER);
        this.add(time);
    }

    void initBack(){
        back = new JButton();
        back.setBounds(getWidth() / 3 ,getHeight() / 10 * 8 ,getWidth() / 3 ,getHeight() / 10);
        back.setText("Back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        back.setHorizontalTextPosition(JLabel.RIGHT);
        back.setVerticalTextPosition(JLabel.TOP);
        back.setFocusable(false);
        this.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowKill.gameLoop = new GameLoop();
                WindowKill.shop.setVisible(false);
                GameFrame.gamePanel.remove(WindowKill.shop);
                GameState.isPause = false;
                GameFrame.windowKill.setVisible(true);
                GameFrame.windowKill.setFocusable(true);
                GameFrame.windowKill.grabFocus();
                WindowKill.gameLoop.start();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.heal ,getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.empower ,getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.banish ,getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
    }
}
