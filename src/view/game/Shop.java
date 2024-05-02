package view.game;

import controller.*;
import controller.Threads.FrameResizeThread;
import controller.Threads.GameLoop;
import view.Abilities.RegularAbility;
import view.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends PIG {
    ShopFrame shopFrame;
    JButton next;
    JButton previous;
    JButton back;
    JPanel banish;
    JPanel empower;
    JPanel heal;
    JLabel hp;
    JLabel xp;
    JLabel wave;
    JLabel time;

    public Shop(ShopFrame shopFrame){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLUE);
        this.shopFrame = shopFrame;
        initContainers();
        initBanish();
        initEmpower();
        initHeal();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && GameState.isPause){
                    end();
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
        banish = new JPanel();
        empower = new JPanel();
        heal = new JPanel();
        banish.setOpaque(false);
        empower.setOpaque(false);
        heal.setOpaque(false);
        this.add(banish);
        this.add(empower);
        this.add(heal);
    }

    private void initHeal() {
        banish.setBounds(getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        banish.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 50) {
                    RegularAbility.sendRequest(RegularAbilitiesEnum.heal);
                    GameState.xp -= 50;
                }
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
        empower.setBounds(getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        empower.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 75) {
                    RegularAbility.sendRequest(RegularAbilitiesEnum.empower);
                    GameState.xp -= 75;
                }
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
        heal.setBounds(getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        heal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 100) {
                    RegularAbility.sendRequest(RegularAbilitiesEnum.banish);
                    GameState.xp -= 100;
                }
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
                end();
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

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        WindowKill.shop.setVisible(false);
        GameFrame.gamePanel.remove(WindowKill.shop);
        GameState.isPause = false;
        GameFrame.windowKill.setVisible(true);
        GameFrame.windowKill.setFocusable(true);
        GameFrame.windowKill.grabFocus();
        WindowKill.gameLoop = new GameLoop(Application.gameFrame);
        WindowKill.frameResizeThread = new FrameResizeThread(Application.gameFrame);
        WindowKill.gameLoop.start();
        WindowKill.frameResizeThread.start();
        shopFrame.dispose();
    }
}
