package view.game;

import controller.Application;
import controller.Constants;
import controller.GameState;
import view.menu.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends JPanel {
    JLabel xp;
    JButton menu;
    public EndGame(){
        this.setLayout(null);
        this.setBounds(0,0,Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.GRAY);
        initMenu();
        initXP();
        this.setVisible(false);
    }

    private void initXP() {
        xp = new JLabel();
        xp.setBounds(getWidth() / 9 * 3 ,getHeight() / 10 ,getWidth() / 9 ,getHeight() / 10);
        xp.setText("" + (int) GameState.xp);
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);
    }

    private void initMenu() {
        menu = new JButton();
        menu.setBounds(getWidth() / 3 ,getHeight() / 10 * 8 ,getWidth() / 3 ,getHeight() / 10);
        menu.setText("menu");
        menu.setBackground(Color.WHITE);
        menu.setOpaque(true);
        menu.setHorizontalTextPosition(JLabel.RIGHT);
        menu.setVerticalTextPosition(JLabel.TOP);
        menu.setFocusable(false);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.gameFrame.dispose();
                Application.mainFrame = new MainFrame();
            }
        });
        this.add(menu);
    }

}
