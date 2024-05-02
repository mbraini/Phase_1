package view.game;

import controller.Application;
import controller.Constants;
import controller.GameState;
import view.PIG;
import view.menu.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends PIG {
    JLabel xp;
    JButton menu;
    public EndGame(){
        this.setLayout(null);
        this.setBounds(0,0,Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        initMenu();
        initXP();
        this.setVisible(false);
    }

    private void initXP() {
        xp = new JLabel();
        xp.setBounds(getWidth() / 3 ,getHeight() / 10 * 6 ,getWidth() / 3 ,getHeight() / 10);
        xp.setFont(new Font(null,Font.BOLD ,15));
        xp.setText("XP: " + (int) GameState.xp);
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);
    }

    private void initMenu() {
        menu = new JButton();
        menu.setBounds(getWidth() / 3 ,getHeight() / 10 * 8 ,getWidth() / 3 ,getHeight() / 10);
        menu.setFont(new Font(null,Font.BOLD ,15));
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

    @Override
    public void start() {
        this.setVisible(true);
        Application.gameFrame.setSize(getSize());
        Application.gameFrame.revalidate();
        Application.gameFrame.setLocationRelativeTo(null);
    }

    @Override
    public void end() {
        this.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.endGameImage ,getWidth() / 3 ,getHeight() / 6 ,getWidth() / 3 ,getHeight() / 3 ,null);
    }
}
