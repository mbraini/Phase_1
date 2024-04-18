package view.game;

import controller.Application;
import controller.Constants;
import controller.GameLoop;
import controller.GameState;

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

    public Shop() {
        this.setLayout(null);
        this.setBounds(0, 0, Application.gameFrame.getWidth() - Constants.barD.width, Application.gameFrame.getHeight() - Constants.barD.height);
        this.setBackground(Color.BLUE);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && GameState.isPause) {
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
    }
}